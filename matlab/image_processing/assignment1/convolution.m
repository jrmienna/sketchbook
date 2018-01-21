function wf = convolution(image, filter)

    f = image;
    w = filter;

    [M,N,X] = size(f);
    [m,n] = size(w);
    
    a = (m-1)/2;
    b = (n-1)/2;
    
    wf = zeros(M,N);
    
    for x = 1:M
        for y = 1:N
        
            l = 0;

            for s = -a:a
                for t = -b:b
                    dx = x+s;
                    dy = y+t;

                    if dx <= 0 || dx >=M
                        dx = x;
                    end

                    if dy <= 0 || dy >= N
                        dy = y;
                    end

                    l = l + w(s+a+1,t+b+1) * f(dx,dy);
                end
            end
            wf(x,y) = l;
        end
    end

end