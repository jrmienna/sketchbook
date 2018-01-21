% TASK 2

% a
% =========================================================================
% Use what you know about erosion, dilation, opening, and closing to remove
% the noisy elements from the image on the left-hand side of Figure 3.

I = imread('noisy.tiff');

T = 249;  % chose this value on empirical basis

Ib = double(I > T);

% start with closing, to patch up the holes
closed = imclose(Ib, strel('diamond', 6));

% then erode to get rid of the noise elements
noisefree = imerode(closed, strel('square', 12));


% b
% =========================================================================
% Implement the distance transform using the erosion method explained
% above. You can use a 3x3 structuring element of all ones to get
% chessboard distance. Test the funciton on the noise free binary image you
% got from (a) and show the result.

se = strel('square', 3);

current = noisefree;
[w, h] = size(current);

% a map to the pixels that have been set
changed = zeros(w, h);

% the result of the distance transform
result = zeros(w, h);

for i = 0:255
    for r = 1:w-1
        for c = 1:h-1
            if current(r,c) == 0 && changed(r,c) == 0
                result(r,c) = i;
                changed(r,c) = 1;
            end
        end
    end
    current = imerode(current, se);
end

% cranck up the intensity and make greyscale
result = uint8(power(result, 1.5));

imshow(noisefree);
figure, imshow(result);

% c
% =========================================================================
% Implement a function that extracts the boundary from a binary image as
% defined in Equation 1. Show the boundary of the noise free binary image
% from (a).

boundary = noisefree - imerode(noisefree, se);

figure, imshow(boundary);

