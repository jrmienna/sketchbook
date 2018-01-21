import os

_dir = "./imgs"

for filename in os.listdir(_dir):
    splits = filename.split("_")
    i = 0
    for split in splits:
        if split[0].isupper():
            break
        i+=1
    if i is 0:
        new_name = filename
    else:
        new_name = "_".join(str(s) for s in splits[:i]) + '.jpg'
    print _dir + filename
    os.rename(_dir + "/" + filename, _dir + "/" + new_name)
