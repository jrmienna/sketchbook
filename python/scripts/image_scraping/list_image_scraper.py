from lxml import html
import urllib
import requests
from PIL import Image
from StringIO import StringIO

# http://nhm2.uio.no/botanisk/nbf/plantefoto/<name>

def download_image(url, name):
    try:
        print url+name
        page = requests.get(url+name)
        tree = html.fromstring(page.content)
        imgs = tree.xpath('//img')
        if imgs:
            for img in imgs:
                src = img.attrib['src']
                if src.find('.jpg') > 0:
                    r = requests.get(url+src)
                    f = open(src, 'w')
                    try:
                        Image.open(StringIO(r.content)).save(f, 'JPEG')
                    except IOError as e:
                        print e
                        continue
                    finally:
                        f.close()
    except KeyError as e:
        print e
    except IOError as e:
        print e


url = 'http://nhm2.uio.no/botanisk/nbf/plantefoto/'
page = requests.get(url)
tree = html.fromstring(page.content)
links = tree.iterlinks()

for link in links:
    href = link[2]
    if href.find('#') < 0:
        download_image(url, link[2])
