import os

_dir = './imgs/'

for filename in os.listdir(_dir):
    if filename.find('.jpg') > 0:
        name = filename.split(".")[0]
        old_name = _dir+filename
        new_name = _dir+name+"/"+filename
        print old_name, new_name
        os.rename(old_name, new_name)
