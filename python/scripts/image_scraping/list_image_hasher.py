import os
import imagehash
import requests
import time
from PIL import Image
from lxml import html
from StringIO import StringIO

import image_scraper

_dir = './imgs/'

def hash_images():
    for folder in os.listdir(_dir):
	if os.path.exists(_dir+folder):
	    for file in os.listdir(_dir+folder):
		file_dir = _dir + folder + "/" 

                if file.find('.jpg') < 0:
                    continue

		_hash = imagehash.average_hash(Image.open(file_dir + file))

		splits = file.split(".")
		filename = splits[0]
		extension = splits[1]
		new_name = filename + "_" + str(_hash) + "." + extension

		os.rename(file_dir+file, file_dir+new_name)

def download_thumbnail():
    try:
        for filename in os.listdir(_dir):
            print filename
            query = filename.replace("_", " ")
            url = url="https://www.google.co.in/search?q="+query+"&source=lnms&tbm=isch"

            path = "./thumbnails"
            if not os.path.exists(path):
                os.mkdir(path)

            image_scraper.scrape_images(url, save_dir=path)

            time.sleep(1.5)

    except IOError as e:
        print e


download_thumbnail()
