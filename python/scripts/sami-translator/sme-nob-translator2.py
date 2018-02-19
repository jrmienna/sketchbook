# -*- coding: utf-8 -*-

# API docs: http://divvun.no/doc/apps/satni/RESTEndPoints.html

# ENDPOINTS:
# http://sanit.oahpa.no/paradigm/sme/nob/<query>
# http://sanit.oahpa.no/lookup/sme/nob/?lookup=<lemma>
# http://satni.uit.no:8080/exist/restxq/satni/article/<query>
# http://satni.uit.no:8080/exist/restxq/satni/search?query=<query>&dict=smenob
# http://satni.uit.no:8080/exist/restxq/satni/search?query=<query>&dict=all
# http://satni.uit.no:8080/exist/restxq/satni/dictionaries

import os
import ast
import json
import demjson
import requests
from pick import pick

def get_article(lemma):
    url = 'http://satni.uit.no:8080/exist/restxq/satni/article/' + lemma
    print url
    json_string = None
    try:
        r = requests.get(url)
        json_string = r.text
        return r.json()
    except Exception as e:
        print e
        json_string = json_string[1:-1]
        return [json.loads(json_string)]
    return None

def get_paradigm(lemma):
    url = 'http://sanit.oahpa.no/paradigm/sme/nob/' + lemma
    print url
    json_string = None
    try:
        r = requests.get(url)
        json_string = r.text
        return r.json()
    except Exception as e:
        print e
        json_string = json_string[1:-1]
        return [json.loads(json_string)]
    return None

def lookup(term):
    url = 'http://sanit.oahpa.no/lookup/sme/nob/?lookup=' + term
    print url
    response = requests.get(url).json()
    if not response['success']:
        return None
    return response['result'][0]


# print row
def parse_pos(pos):
    pos_map = {
        'A': 'adj.',
        'Adv': 'adv.',
        'V': 'verb',
        'CC': 'conj.',
        'CS':'subj.',
        'Interj':'interj.',
        'N':'subst.',
        'Num': 'num.',
        'Pcle': 'part.',
        'Po': 'postpos.',
        'Pr': 'prepos.',
        'Pron': 'pron.',
    }
    if pos not in pos_map:
        return pos
    return pos_map[pos]

def format_input(row):
    return row['input']

def format_sme(row):
    return row['lemma']

def format_pos(row):
    return parse_pos(row['article'][0]['pos'])

def format_nob(row):
    def format_translation(ex):
        return '(' + parse_pos(trans['pos']) + ') ' + trans['#text']
    def format_example(ex):
        return '<blockquote>' + ex['x'] + '<br/><i>' + ex['xt'] + '</i></blockquote>'

    string = ''
    for res in row['article']:
        if res['dict'] == 'smenob' and 'tg' in res:
            if 't' in res['tg']:
                trans = res['tg']['t']
                if type(trans) is dict:
                    string += '' + format_translation(trans) + '<br/>'
                else:
                    string += '' + ', '.join(format_translation(t) for t in trans) + '<br/>'
            if 'xg' in res['tg']:
                xg = res['tg']['xg']
                if type(xg) is dict:
                    string += format_example(xg) + '<br/>'
                else:
                    for example in xg:
                        string += format_example(example) + '<br/>'
            string += '<br/>'
    return string

def format_related(row):
    def format_lemma(l, pos):
        return '(' + parse_pos(pos) + ') ' + l
    def format_translation(t):
        return t
    string = ''
    for lookup in row['lookup']['lookups'][1:]:
        string += '<i>' + format_lemma(lookup['left'], lookup['pos']) + '</i> - '
        string += ', '.join(format_translation(t) for t in lookup['right']) + '<br/>'
    string += '<br/>'
    return string

def format_paradigm(row):
    string = ''
    paradigm = row['paradigm']['paradigms'][0]
    entries = []
    for item in paradigm:
        entry = {}
        entry['text'] = item[0]
        entry['row'] = item[1][-1]
        entry['col'] = item[1][-2]
        entries.append(entry)
        #string += entry[0] + '<br/>'
    prev_row = None
    prev_col = None
    for item in sorted(entries, key=lambda x: (x['row'], x['col'])):
        if prev_row != item['row']:
            string += '<br/>'
            string += item['row'] + '&emsp;'
        elif prev_row == item['row'] and prev_col == item['col']:
            string += '/'
        else:
            string += '&emsp;'
        string += item['text']
        prev_row = item['row']
        prev_col = item['col']
    return string

# input
# result_1
# examples_1
# paradigm_1
#...
# result_5
# examples_5
# paradigm_5

data = []

with open('./words_full.txt') as f:
    for line in f.readlines():
        try:
            row = {}
            word = line.decode('utf-8').strip().lower()
            print word
            result = lookup(word)
            if not result:
                continue
            lemma = result['lookups'][0]['left']
            row['input'] = word
            row['lemma'] = lemma
            row['lookup'] = result
            row['article'] = get_article(lemma)
            #row['paradigm'] = get_paradigm(lemma)
            data.append(row)
        except Exception as e:
            print e
            continue
    f.close()

f = open('./results.txt', 'w')
for row in data:
    try:
        row_string = (format_input(row)
            + '\t' + format_sme(row)
            + '\t' + format_pos(row)
            + '\t' + format_nob(row)
            + '\t' + format_related(row)
            #+ '\t' + format_paradigm(row)
            + '\n')
        f.write(row_string.encode('utf-8'))
    except Exception as e:
        print e
        continue
f.close()

