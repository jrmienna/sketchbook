function [ centers, radii ] = findCheckerPieces( I )
%FINDCIRCLES Finds checker pieces in the given images
    
    % Create filter to use for blurring
    H = fspecial('disk', 5);

    % Blure the image before edge detection for improved result
    blured = imfilter(I, H, 'replicate');

    % Detect edges using the Canny method
    BW = edge(blured, 'Canny');

    % Dilate & Erode to connect edges
    closed = imclose(BW, strel('disk', 3));

    % Fill the holes
    filled = imfill(closed, 'holes');

    % Erode & dilate to get rid of noise
    opened = imopen(filled, strel('disk', 8));

    % Detect circles in the image using circular Hough transform
    [centers, radii] = imfindcircles(opened, [30, 40]);

end

