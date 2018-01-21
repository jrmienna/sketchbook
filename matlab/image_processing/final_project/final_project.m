clear;

RGB1 = imread('image1.png');
RGB2 = imread('image2.png');
RGB3 = imread('image3.png');

I1 = rgb2gray(RGB1);
I2 = rgb2gray(RGB2);
I3 = rgb2gray(RGB3);

% Empirical threshold
T = 150;

images = {I1, I2, I3};

tiles_horizontal = 8;
tiles_vertical = 5;

% Three images with five checker pieces in each
pieces = cell(3, 5);

for i = 1:length(images)
    
    current = images{i};
    
    [w, h] = size(current);
    
    tile_size = floor((w/tiles_horizontal + h/tiles_vertical) / 2);
    
    [centers, radii] = findCheckerPieces(current);
    
    for j = 1:length(centers)
        
        piece  = struct('center', [], 'radii', [], 'pos', [], 'type', []);
        
        piece.center = centers(j, :);
        piece.radii = radii(j);
        piece.pos = centers(j, :) / tile_size;
        
        center_value = current(floor(centers(j, 2)), floor(centers(j, 1)));
        
        if center_value > T
            piece.type = 'white';
        else
            piece.type = 'red';
        end
        
        pieces{i, j} = piece;
        
    end
end


figure, imshow(RGB1);
viscircles(pieces{1,1}.center, pieces{1,1}.radii,'EdgeColor','y');
viscircles(pieces{1,2}.center, pieces{1,2}.radii,'EdgeColor','y');
viscircles(pieces{1,3}.center, pieces{1,3}.radii,'EdgeColor','y');
viscircles(pieces{1,4}.center, pieces{1,4}.radii,'EdgeColor','y');
viscircles(pieces{1,5}.center, pieces{1,5}.radii,'EdgeColor','y');

figure, imshow(RGB3);
viscircles(pieces{3,1}.center, pieces{3,1}.radii,'EdgeColor','y');
viscircles(pieces{3,2}.center, pieces{3,2}.radii,'EdgeColor','y');
viscircles(pieces{3,3}.center, pieces{3,3}.radii,'EdgeColor','y');
viscircles(pieces{3,4}.center, pieces{3,4}.radii,'EdgeColor','y');
viscircles(pieces{3,5}.center, pieces{3,5}.radii,'EdgeColor','y');

figure, imshow(RGB2);
viscircles(pieces{2,1}.center, pieces{2,1}.radii,'EdgeColor','y');
viscircles(pieces{2,2}.center, pieces{2,2}.radii,'EdgeColor','y');
viscircles(pieces{2,3}.center, pieces{2,3}.radii,'EdgeColor','y');
viscircles(pieces{2,4}.center, pieces{2,4}.radii,'EdgeColor','y');
viscircles(pieces{2,5}.center, pieces{2,5}.radii,'EdgeColor','y');
