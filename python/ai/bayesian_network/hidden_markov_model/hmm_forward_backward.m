function [ P ] = hmm_forward_backward( Pi, e, O, T )
%UNTITLED Summary of this function goes here
%   e   = a vector of evidence values for steps 1...t
%
%   [P] = hmm_forward_backward(...) return a vector of probabilitiy
%   distributions

    n = length(Pi);
    t = length(e);
    
    fv = ones(n, n);        % a vector of forward messages
    fv(1, :) = Pi;
    fprintf(sprintf('forward_message = <%.3f,%.3f>\n', fv(1,:)));
    
    b = ones(1, n);         % a represenation of the backward message, initially 1s
    sv = ones(t, n);        % a vector of smoothed estimates for steps 1...t
    
    for i = 2:t
        fv(i, :) = hmm_forward(fv(i-1, :), e(1:i), O, T, false);
        fprintf(sprintf('forward_message = <%.3f,%.3f>\n', fv(end,:)));
    end
    
    for i = t:-1:1
        sv(i,:) = hmm_normalize(fv(i,:) .* b);
        b = hmm_backward(b, e(i), O, T, false);
        fprintf(sprintf('backward_message = <%.3f,%.3f>\n', b));
    end
    
    fprintf(sprintf('smoothed = <%.3f,%.3f>\n', sv(end,:)));
    
    P = sv;
end

