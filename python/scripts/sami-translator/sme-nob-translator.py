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

#def format_example(ex):
#    return '' + ex['x'].replace('.','') + '\t' + ex['xt'].replace('.','')

#def format_translations(term, translations):
#    return '' + term + '\t' + ', '.join(tr['#text'] for tr in translations)

# first_lang = 'sme'
# second_lang = 'nob'
# dict = 'smenob'

# dict_path = 'dict'
# translations_path = 'tg.t'
# translation_text_key = '#text'
# examples_path = 'tg.xg'
# example_first_lang_key = 'x'
# example_second_lang_key = 'xt'

def parse_response(term, response):
    if not response:
        return []
    translations = []
    examples = []
    for res in response:
        if res['dict'] == 'smenob' and 'tg' in res:
            if 't' in res['tg']:
                t = res['tg']['t']
                if type(t) is dict:
                    #if t['pos'] != 'A': continue
                    translations.append(t)
                else:
                    for trans in t:
                        #if trans['pos'] != 'A': continue
                        translations.append(trans)
            if 'xg' in res['tg']:
                xg = res['tg']['xg']
                if type(xg) is dict:
                    examples.append(xg)
                else:
                    for example in xg:
                        examples.append(example)

    translation = format_translations(term, translations)
    print translation
    return [translation] + examples

def translate(path, result_path):
    parsed = []
    base_url = 'http://satni.uit.no:8080/exist/restxq/satni/article/'

    with open(path) as f:
        lines = f.readlines()
        json_string = None
        for line in lines:
            try:
                term = line.decode('utf-8').strip().lower()
                url = base_url + term
                print url
                r = requests.get(url)
                json_string = r.text
                # TODO: do search when response is None
                parsed += parse_response(term, r.json())
            except Exception as e:
                print e
                json_string = json_string[1:-1]
                parsed += parse_response(term, [json.loads(json_string)])
                continue
        f.close()

    if len(parsed):
        f = open(result_path, 'w')
        for line in parsed:
            f.write(line.encode('utf-8').strip() + '\n')
        f.close()
    else:
        print 'No results'


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

def get_examples(article):
    dict_path = 'dict'
    translations_path = 'tg.t'
    translation_text_key = '#text'
    examples_path = 'tg.xg'
    example_first_lang_key = 'x'
    example_second_lang_key = 'xt'

    articles = []
    for res in article:
        if res['dict'] == 'smenob' and 'tg' in res:
            article = {}
            article['examples'] = []
            if 't' in res['tg']:
                trans = res['tg']['t']
                if type(trans) is dict:
                    article['translation'] = trans['pos'] + ' ' + trans['#text']
                else:
                    article['translation'] = ', '.join(t['pos'] + ' ' + t['#text'] for t in trans)
            if 'xg' in res['tg']:
                xg = res['tg']['xg']
                if type(xg) is dict:
                    article['examples'].append(xg)
                else:
                    for example in xg:
                        article['examples'].append(example)
            articles.append(article)
    return articles

def lookup(term):
    url = 'http://sanit.oahpa.no/lookup/sme/nob/?lookup=' + term
    print url
    response = requests.get(url).json()
    if not response['success']:
        return None
    return response['result'][0]

def main(path, result_path):
    translate(path, result_path)

# row = ''
# results = lookup('aniiga')
# lemma = None
# for result in results:
    # for term in result['lookups']:
        # print term['left'] + '\t' + ', '.join(term['right'])
        # row += term['left'] + '\t' + ', '.join(term['right'])
        # lemma = term['left']

#if lemma:
#    article = get_article(lemma)
#    examples = get_examples(article)
#    row += ';' + '\t'.join(examples)

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
# result 1
# result 2
# result 3
# result 4
# result 5

# sme
# pos
# nob
# related
# personal note
# paradigm

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

#rows_short.append(term['left'] + '\t' + ', '.join(term['right']) + '\n')

#f = open('./results_full.txt', 'w')
#for row in rows:
    #f.write(row.encode('utf-8').strip() + '\n')
#f.close()

#if __name__ == "__main__":
#    options = ['Sami \t-->\t Norwegian']
#    option, index = pick(options, 'Select translator')
#
#    valid_path = False
#    while not valid_path:
#        try:
#            path = raw_input("Enter path to .txt file: \n")
#            dirname = os.path.dirname(path)
#            filename, extension = os.path.basename(path).split('.')
#            assert os.path.exists(path), "Couldn't find file at: " + str(path)
#            assert os.path.isfile(path), "Couldn't find file at: " + str(path)
#            assert extension == 'txt', "File must have .txt extension"
#            valid_path = True
#        except Exception as e:
#            print e
#
#    should_save = raw_input("Do you want to save results? (Y/n)") or 'Y'
#    if should_save.lower() == 'y':
#        default_result_path = dirname + '/' + filename + '.csv'
#        result_path = raw_input("Save as (" + default_result_path + "): ") or default_result_path
#        print result_path
#
#    path = './test.txt'
#    result_path = './test.csv'
#    main(path, result_path)
