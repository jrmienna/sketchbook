% TASK 1

% a
% =========================================================================
% Implement a function that takes a grayscale image and calculates a
% threshold using the global thresholding algorithm outlined above. Segment
% an image using the threshold and show the result.

delta_T = 0.0000001;

% Initial estimate
T = 180;
T_old = 0;

I = imread('Fig1051a.tif');
[w, h] = size(I);
g1 = uint8(zeros(w, h));
g2 = uint8(zeros(w, h));

counter = 0;
while abs(T - T_old) > delta_T
    
    % Segment the image data using T
    for r = 1:w
        for c = 1:h
            if I(r,c) > T
                g1(r,c) = I(r,c);
            else
                g2(r,c) = I(r,c);
            end
        end
    end

    % Calculate the mean values
    m1 = mean2(g1);
    m2 = mean2(g2);

    % Compute a new threshold
    T_old = T;
    T = 0.5*(m1 + m2);
    
    counter = counter + 1;
end

figure, imshow(I - g1);
figure, imshow(I - g2);
