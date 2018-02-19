clc;

% P(A | B) = "[B=true, B=false]"

O = [0.9 0.2];             % observation model, P(U_t | R_t)
T = [0.7 0.3];             % transition model, P(R_t | R_{t-1})


fprintf('----------------------------------------\n');
fprintf('----------------------------------------\n');
fprintf('------------- Part B -------------------\n');
fprintf('----------------------------------------\n');
fprintf('----------------------------------------\n');

evidence = [true, true];    % umbrella observations
initial_state = [0.5 0.5];  % assumed initial state distribution

% Calculate the filtered estimate of the state based on evidence.
% "We can think of the estimate as a message that is propagated forward
% along the sequence, modified by each transition and updated by each new
% observation" (AIMA)
hmm_forward(initial_state, evidence, O, T, true);

fprintf('----------------------------------------\n');

% Repeat with new evidence
evidence = [true, true, false, true, true];
hmm_forward(initial_state, evidence, O, T, true);

fprintf('----------------------------------------\n');
fprintf('----------------------------------------\n');
fprintf('------------- Part C -------------------\n');
fprintf('----------------------------------------\n');
fprintf('----------------------------------------\n');

% Calculate the smoothed estimate.
evidence = [true, true];
hmm_forward_backward(initial_state, evidence, O, T);

fprintf('----------------------------------------\n');

% Repeat with new evidence
evidence = [true, true, false, true, true];
hmm_forward_backward(initial_state, evidence, O, T);