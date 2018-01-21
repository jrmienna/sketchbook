# Skrevet av Eirik Reksten

from sys import stdin

def main():

    wordlist = stdin.readline().split()
    search = stdin.read()
            
    wordbook = {}
    pos = 0
    
    if '?' in search:
        word_tree = ({}, [])
        for t in wordlist:
            if t not in wordbook:
                n = word_tree
                for c in t:
                    if c not in n[0]:
                        n[0][c] = ({},[])
                    n = n[0][c]
                wordbook[t] = n[1]
            wordbook[t].append(pos)
            pos += len(t) + 1
    else:
        for t in wordlist:
            if t not in wordbook:
                wordbook[t] = []
            wordbook[t].append(pos)
            pos += len(t) + 1

    for word in search.split():
        if word in wordbook:
            posi = wordbook[word]
        elif '?' in word:
            nodes = [word_tree]
            for c in word:
                nye = []
                for n in nodes:
                    if c == '?':
                        nye.extend(n[0].values())
                    elif c in n[0]:
                        nye.append(n[0][c])
                nodes = nye
            posi = []
            for n in nodes:
                posi.extend(n[1])
            wordbook[word] = posi
        else:
            posi = []
            wordbook[word] = posi
        posi.sort()
        print word + ":",
        for p in posi:
            print p,
        print
main()
