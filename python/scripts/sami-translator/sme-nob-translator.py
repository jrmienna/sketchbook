# -*- coding: utf-8 -*-

# API docs: http://divvun.no/doc/apps/satni/RESTEndPoints.html

# ENDPOINTS:
# http://gtweb.uit.no:8080/exist/restxq/satni/article/<query>
# http://gtweb.uit.no:8080/exist/restxq/satni/search?query=<query>&dict=smenob
# http://gtweb.uit.no:8080/exist/restxq/satni/search?query=<query>&dict=all
# http://gtweb.uit.no:8080/exist/restxq/satni/dictionaries

import os
import ast
import json
import demjson
import requests
from pick import pick

base_url = 'http://gtweb.uit.no:8080/exist/restxq/satni/article/'

def format_example(ex):
    return '' + ex['x'].replace('.','') + '\t' + ex['xt'].replace('.','')

def format_translation(term, trs):
    return '' + term + '\t' + ', '.join([tr['#text'] for tr in trs])

#translations_path = 'tg.t'
#examples_path = 'tg.xg'

def parse_response(term, response):
    if not response:
        return []
    translations = []
    examples = []
    for res in response:
        print res
        print type(res)
        if res['dict'] == 'smenob' and 'tg' in res:
            if 't' in res['tg']:
                t = res['tg']['t']
                if type(t) is dict:
                    if t['pos'] != 'A': continue
                    translations.append(t)
                else:
                    for translation in t:
                        if translation['pos'] != 'A': continue
                        translations.append(translation)
            if 'xg' in res['tg']:
                xg = res['tg']['xg']
                if type(xg) is dict:
                    examples.append(format_example(xg))
                else:
                    for example in xg:
                        examples.append(format_example(example))

    translation = format_translation(term, translations)
    return [translation] + examples

def translate(path, result_path):
    parsed = []

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

    print parsed

    if len(parsed):
        f = open(result_path, 'w')
        for line in parsed:
            f.write(line.encode('utf-8').strip() + '\n')
        f.close()
    else:
        print 'No results'


def translate_word(word):
    # lookup
    # if not match:
        # prompt user: suggestions
    # print translation and meta data
    # return translation and meta data
    pass

def main(path, result_path):
    translate(path, result_path)

if __name__ == "__main__":
    options = ['Sami \t-->\t Norwegian']
    option, index = pick(options, 'Select translator')

    valid_path = False
    while not valid_path:
        try:
            path = raw_input("Enter path to .txt file: \n")
            dirname = os.path.dirname(path)
            filename, extension = os.path.basename(path).split('.')
            assert os.path.exists(path), "Couldn't find file at: " + str(path)
            assert os.path.isfile(path), "Couldn't find file at: " + str(path)
            assert extension == 'txt', "File must have .txt extension"
            valid_path = True
        except Exception as e:
            print e

    should_save = raw_input("Do you want to save results? (Y/n)") or 'Y'
    if should_save.lower() == 'y':
        default_result_path = dirname + '/' + filename + '.csv'
        result_path = raw_input("Save as (" + default_result_path + "): ") or default_result_path
        print result_path

    main(path, result_path)