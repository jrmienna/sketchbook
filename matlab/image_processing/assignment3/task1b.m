% TASK 1

% b
% =========================================================================
% Implement a function that segments a grayscale image useing the
% region growing method outlined above. When growing the region around each
% seed point you may expand your set of candidate pixels using either a von
% Neumann neightbourhood (4-connectedness) or a Moore neighbourhood
% (8-connectedness). Apply it on an approriate image and show the result.
% Which seed points did you select?

I = imread('Fig1051a.tif');

[w, h] = size(I);

coord = [295 253; 140 255; 444 243];
regions = uint8(zeros(w,h));

T = 163;

for r = 1:size(coord, 1)
    x = coord(r, 1);
    y = coord(r, 2);

    region = uint8(zeros(w,h));

    expanded = [];
    queue = [];
    queue = [queue; I(x, y) x y];

    while ~isempty(queue)
        % pop the first row of queue
        current = queue(1,:);
        expanded = [expanded; current];
        queue(1,:) = [];

        % extract values
        intensity = current(1);
        x = current(2);
        y = current(3);

        % set values for region
        region_a(x,y) = intensity;

        % expand neighbours
        if y < h
            if I(x, y+1) > T   % beneath
                a = [I(x, y+1) x y+1];
                if sum(ismember(expanded, a, 'rows')) == 0 && sum(ismember(queue, a, 'rows')) == 0
                    queue = [queue; a];
                end
            end
        end

        if y > 1
            if I(x, y-1) > T  % over
                a = [I(x, y-1) x y-1];
                if sum(ismember(expanded, a, 'rows')) == 0 && sum(ismember(queue, a, 'rows')) == 0
                    queue = [queue; a];
                end
            end
        end

        if x > 1
            if I(x-1, y) > T  % left
                a = [I(x-1, y) x-1 y];
                if sum(ismember(expanded, a, 'rows')) == 0 && sum(ismember(queue, a, 'rows')) == 0
                    queue = [queue; a];
                end
            end
        end

        if x < w
            if I(x+1, y) > T  % right
                a = [I(x+1, y) x+1 y];
                if sum(ismember(expanded, a, 'rows')) == 0 && sum(ismember(queue, a, 'rows')) == 0
                    queue = [queue; a];
                end
            end
        end
    end
    
    regions = regions + region;
end

imshow(regions);