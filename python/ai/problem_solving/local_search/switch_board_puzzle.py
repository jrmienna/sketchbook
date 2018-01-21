# -*- coding: utf-8 -*-
from numpy import inf
from math import exp
from random import uniform, randint

WIRE_VERT = ' | '
WIRE_HORI = ' - '
TURN = ' x '
PEG = ' o '
EMPTY = '   '      

DELTA_T = 0.1
T_MAX = 1000

class SwitchBoardState(object):
    '''
    Helper-class that keeps track of k, m, n and the positions of all
    the eggs currently on the board.
    
    turs is a list of tuples of the form (row,col), where row and col is
    the position of a turn in the wire.

    example:
    turns = [start, (0,3), (0,2), (2,2), (2,3), (3,3), (3,1), (0,1), (0,0)]
    '''
    def __init__(self, m, n, w, d, end, turns):
        self.m = m
        self.n = n
        self.w = w
        self.d = d
        self.end = end
        self.turns = turns
        self.wires = self.get_wires_list()

    def __str__(self):
        width = 2*self.n
        height = 2*self.m
        board = [[EMPTY for c in xrange(width)] for r in xrange(height)]

        for r in xrange(height - 1):
            for c in xrange(width - 1):
                if r % 2 == 0 and c % 2 == 0:
                    if (r/2, c/2) in self.turns and (r/2, c/2):
                        board[r][c] = TURN
                    else:
                        board[r][c] = PEG
                elif r % 2 == 0 and c % 2 != 0: # horizontal wires
                    if (r, c) in self.wires:
                        board[r][c] = WIRE_HORI
                elif r % 2 != 0 and c % 2 == 0: # vertical wires
                    if (r, c) in self.wires:
                        board[r][c] = WIRE_VERT
                else:
                    board[r][c] = EMPTY

        out = ''
        for r in xrange(width - 1):
            for c in xrange(height - 1):
                out += board[r][c]
            out += '\n'
        return out + '\n'

    def get_wires_list(self):
        wires = []
        n = len(self.turns)
        for i in xrange(n):
            from_turn = (self.turns + [self.end])[i]
            to_turn = (self.turns + [self.end])[i+1]

            a_row, a_col = from_turn[0], from_turn [1]
            b_row, b_col = to_turn[0], to_turn[1]

            if a_row - b_row == 0: # at same row --> horizontal wire
                if a_col > b_col: a_col, b_col = b_col, a_col
                for j in xrange(a_col, b_col):
                    wires.append((a_row*2, j*2 + 1)) # x2 to scale up to board

            elif a_col - b_col == 0: # at same column --> vertical wire
                if a_row > b_row: a_row, b_row = b_row, a_row
                for j in xrange(a_row, b_row):
                    wires.append((j*2 + 1, a_col*2))
        return wires

    def evaluate(self):
        '''
        '''
        n = len(self.turns)
        for i in xrange(n):
            from_turn = (self.turns + [self.end])[i]
            to_turn = (self.turns + [self.end])[i+1]

            a_row, a_col = from_turn[0], from_turn [1]
            b_row, b_col = to_turn[0], to_turn[1]
            
            if not a_row - b_row == 0 and not a_col - b_col == 0:
                return 0

        if len(self.wires) > self.m * self.n - 1: return 0
        turn_length = self.w * len(self.turns)
        connection_length = self.d * len(self.wires)
        return connection_length + turn_length

def evaluate(state):
    return state.evaluate()

def generate_neighbors(state):
    '''
    (1) Generates all the states that differ from the current state with the
    position of only one element
    (2) Generates all the states that contain one turn more that the current 
    state
    (3) Generates all the states that contain one less turn that the current
    state
    '''

    turns_list = []
    # all neighbors with one turn moved
    index = len(state.turns) - 1 
    added = []
    while index > -1:
        temp = list(state.turns)
        turn = temp.pop(index)
        r = turn[0]
        c = turn[1]
        if r > 0:
            tup = (r-1,c)
            up = list(temp + [tup])
            if tup not in state.turns and up not in added:
                turns_list.append(up)
                added.append(up)
        if c < state.n-1:
            tup = (r, c+1)
            right = list(temp + [tup])
            if tup not in state.turns and right not in added:
                turns_list.append(right)
                added.append(right)
        if r < state.m-1:
            tup = (r+1,c)
            down = list(temp + [tup])
            if tup not in state.turns and down not in added:
                turns_list.append(down)
                added.append(down)
        if c > 0:
            tup = (r, c-1)
            left = list(temp + [tup])
            if tup not in state.turns and left not in added:
                turns_list.append(left)
                added.append(left)
        index -= 1

    # alle neighbors with one more turn
    empty = []
    for r in xrange(state.m):
        for c in xrange(state.n):
            if (r,c) not in state.turns:
                empty.append((r,c))

    while empty:
        temp = list(state.turns)
        temp.append(empty.pop())
        if temp not in turns_list:
            turns_list.append(temp)
    # START (3)
    index = len(state.turns) - 1
    while index > -1:
        temp = list(state.turns)
        temp.pop(index)
        if temp not in turns_list:
            turns_list.append(temp)
        index -= 1
    # END (3)
    return_states = []
    for turns in turns_list:
        return_states.append(SwitchBoardState(
            state.m,
            state.n,
            state.w,
            state.d,
            state.end,
            turns
        ))
    return return_states
    pass

def sa(start_state, dT, T_max, F_target):
    P = start_state
    T = T_max
    while T > 0:
        F = evaluate(P)

        if F > F_target:
            print 'FOUND SOLUTION'
            return P

        neighbors = generate_neighbors(P)

        F_neighbors = [evaluate(neighbor) for neighbor in neighbors]
        F_max = max(F_neighbors)
        P_max = neighbors[F_neighbors.index(F_max)]
        
        q = (F_max - F)/F if F > 0 else inf
        p = min(1, exp(-q/T))
        
        if uniform(0,1) > p:
            P = P_max
        else:
            P = neighbors[randint(0, len(neighbors) - 1)]
        T -= dT

    print "DIDN'T FIND SOLUTION!"
    return P

def main():
    m = 4
    n = 4
    d = 3
    w = 2
    start = (1,3)
    end = (3,0)

    turns = [start]

    min_turns = 9
    min_connection_length = d * (m*n - 1)
    min_turn_length = w * min_turns
    F_target = min_connection_length + min_turn_length
    start_state = SwitchBoardState(m, n, w, d, end, turns)

    print 'thinking........'
    end_state = sa(start_state, DELTA_T, T_MAX, F_target)
    print end_state
    print end_state.evaluate()
    print end_state.turns

if __name__ == '__main__':
    main()
