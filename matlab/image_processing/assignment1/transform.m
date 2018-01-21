function out = transform(I, s)

    [M, N] = size(I);
    
    out = zeros(M, N);
    
    for i = 1:M
        for j = 1:N
            
            P = I(i, j);

            switch s
                case 'negative'
                    out(i, j) = 255 - P;
                case 'gamma'
                    out(i, j) = power(P, 2);
                otherwise
                    out(i, j) = P;
            end
        end
    end
end