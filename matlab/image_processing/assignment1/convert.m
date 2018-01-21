function out = convert(RGB, s)

    [M, N, X] = size(RGB);
    
    out = zeros(M, N);
    
    for i = 1:M
        for j = 1:N
            P = impixel(RGB, i, j);
            R = P(1);
            G = P(2);
            B = P(3);
            
            average = (R + G + B) / 3;
            
            switch s
                case 'average1'
                    out(i,j) = average;
                case 'average2'
                    out(i, j) = 0.2126*R + 0.7152*G + 0.0722*B;
                otherwise
                    out(i, j) = average;
            end
        end
    end
end