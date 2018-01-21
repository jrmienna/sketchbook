function [ P ] = hmm_forward( Pi, e, O, T, print)
%FORWARD Filtering in the hidden Markov model using the forward method
%       Pi = initial probability distribution.
%       e = array of all evidence up to date.
%       O = observation/sensor model.
%       T = a matrix representing the transition model.
%
%       [P] = hm_forward(...) returns the probability of the given sequence
%       in the given model
    
    p = Pi;   
    
    for i = 1:length(e)
        
        if (e(i));  a = T;       else  a = (1-T);  end;
        if (e(i));  b = (1-T);   else  b = T;      end;
        
        % Calculate prediction
        p = a*p(1) + b.*p(2);  % WHY 1-T???
        
        % Update probability
        p = O.*p;
        
        % Normalize
        p = hmm_normalize(p);
        
        if (print)
            fprintf('evidence = %d\t', e(i));
            fprintf(sprintf('forward_message = <%.3f, %.3f>\n', p));
        end
        
    end
    
    P = p;
end

