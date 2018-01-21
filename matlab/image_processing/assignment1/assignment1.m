RGB = imread('color_image200.png');
imshow(RGB);

% grayscale conversion 1
s1 = convert(RGB, 'average1');
figure, imshow(s1, []);

% grayscale conversion 2
s2 = convert(RGB, 'average2');
figure, imshow(s2, []);

f = rgb2gray(RGB);

% invert
s3 = transform(f, 'negative');
figure, imshow(s3, []);

% gamma
s4 = transform(f, 'gamma');
figure, imshow(s4, []);

% arbitrary filter
w = [1 3 2; 3 2 1; 3 4 2];
wf = convolution(f, w);
figure, imshow(wf, []);

% gaussian filter
w_g = (1/9) * [1 1 1; 1 1 1; 1 1 1];
wf_g = convolution(f, w_g);
figure, imshow(wf_g, []);

% averaging filter
w_a = (1/16) * [1 2 1; 2 4 2; 1 2 1];
wf_a = convolution(f, w_a);
figure, imshow(wf_a, []);

% sobel operator
sx = [-1 0 1; -2 0 2; -1 0 1];
gx = (sx(1,3) + 2*sx(2,3) + sx(3,3)) - (sx(1,1) + 2*sx(2,1) + sx(3,1));

sy = [1 2 1; 0 0 0; -1 -2 -1];
gy = (sy(3,1) + 2*sy(3,2) + sy(3,3)) - (sy(1,1) + 2*sy(1,2) + sy(1,3));

M = sqrt(gx^2 + gy^2);
