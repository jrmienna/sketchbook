import numpy as np
from PIL import Image


def average1(r, g, b):
    return (r + g + b)/3


def average2(r, g, b):
    return int(0.2126*r + 0.7152*g + 0.0722*b)


def plot_histogram(img):
    pass


def main():

    img = Image.open('image.png')

    width, height = img.size

    for i in range(0, width-1):
        for j in range(0, height-1):
            pixel = img.getpixel((i, j))

            red = pixel[1]
            green = pixel[2]
            blue = pixel[3]

            average = average1(red, green, blue)
            img.putpixel((i, j), average)

    pixel = img.getpixel((0,0))

    #plot_histogram(img)

    img.show()
    img.close()


if __name__ == '__main__':
    main()
