import os
import requests

from lxml import html

_dir = "./imgs/"

def download_images():
    try:
        for filename in os.listdir(_dir):
            print filename
            splits = filename.split(".")
            name = splits[0]
            query = name.replace("_", " ")
            url = url="https://www.google.co.in/search?q="+query+"&source=lnms&tbm=isch"
            page = requests.get(url)
            tree = html.fromstring(page.content)
            imgs = tree.xpath('//img')

            new_dir = _dir+name+"/"

            if not os.path.exists(new_dir):
                os.mkdir(new_dir)
                i = 10
                for img in imgs:
                    src = img.attrib['src']
                    r = requests.get(src, stream=True)
                    with open(new_dir+name+str(i)+'.jpg', 'wb') as f:
                        for chunk in r.iter_content():
                            f.write(chunk)
                    i += 1

    except Error as e:
        print e

download_images()
