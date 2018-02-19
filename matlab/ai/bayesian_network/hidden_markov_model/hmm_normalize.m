function [ n ] = hmm_normalize( v )
%UNTITLED3 Summary of this function goes here
%   Detailed explanation goes here

    a = 1 / sum(v);
    n = a * v;
end

