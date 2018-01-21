function [ P ] = hmm_backward( Pi, e, O, T, print)
%UNTITLED2 Summary of this function goes here
%   Detailed explanation goes here


    p = Pi;
    
    for i = 1:length(e)
        if (e);  a = T;       else  a = (1-T);  end;
        if (e);  b = (1-T);   else  b = T;      end;
        
        p = O(1) * Pi(1) .* a + O(2) * Pi(2) .* b; 
        %p = hmm_normalize(p);
        
        if (print)
            fprintf('evidence = %d\t', e);
            fprintf(sprintf('backward_message = <%.3f, %.3f>\n', p));
        end
    end
  
    P = p;

end

