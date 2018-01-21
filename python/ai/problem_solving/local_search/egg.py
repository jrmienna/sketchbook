from simulated_annealing import SimulatedAnnealingState, SimulatedAnnealing 
from sys import stdin

class EggCartonState(SimulatedAnnealingState):
    '''
    Helper-class that keeps track of k, m, n and the positions of all
    the eggs currently on the board.
    
    eggs is a list of touples of the form (row, col), where row and col is
    the egg's position in the egg carton.
    '''
    def __init__(self, k, m, n, eggs):
        self.k = k
        self.m = m
        self.n = n
        self.eggs = eggs
    
    def __str__(self):
        out = ''
        for r in xrange(self.m):
            for c in xrange(self.n):
                if (r, c) in self.eggs:
                    out += 'o'
                else:
                    out += '-'
                out += ' '
            out += '\n'
        return out

    def evaluate(self):
        '''
        The evaluation function loops through self.eggs and stores the number
        of eggs in each row and column, and compares all the counts with k

        :return 0 if no eggs or number of eggs > k else (number of eggs/k)
        '''
        if not self.eggs:
            return 0

        counts = []
        counter = 0
        
        for r in xrange(self.m):
            for egg in self.eggs:
                if egg[0] == r:
                    counter += 1
            counts.append(counter)
            counter = 0

        for c in xrange(self.n):
            for egg in self.eggs:
                if egg[1] == c:
                    counter += 1
            counts.append(counter)
            counter = 0
        
        return_value = 1
        for count in counts:
            if count > self.k: return 0
            return_value += count/self.k
        return return_value


class EggCartonSolver(SimulatedAnnealing):

    def generate_neighbors(self, state):
        '''
        (1) Generates all the states that differ from the current state with the
        position of only one element
        (2) Generates all the states that contain one egg more that the current 
        state
        (3) Generates all the states that contain one less egg that the current
        state
        '''

        eggs_list = []

        # START (1) 
        index = len(state.eggs) - 1
        added = []
        while index > -1:
            temp = list(state.eggs)
            egg = temp.pop(index)

            r = egg[0]
            c = egg[1]
            if r > 0:
                tup = (r-1,c)
                up = list(temp + [tup])
                if tup not in state.eggs and up not in added:
                    eggs_list.append( up)
                    added.append(up)
            if c < state.n-1:
                tup = (r, c+1)
                right = list(temp + [tup])
                if tup not in state.eggs and right not in added:
                    eggs_list.append(right)
                    added.append(right)
            if r < state.m-1:
                tup = (r+1,c)
                down = list(temp + [tup])
                if tup not in state.eggs and down not in added:
                    eggs_list.append(down)
                    added.append(down)
            if c > 0:
                tup = (r, c-1)
                left = list(temp + [tup])
                if tup not in state.eggs and left not in added:
                    eggs_list.append(left)
                    added.append(left)
            index -= 1
        # END (1)

        # START (2)
        empty = []
        for r in xrange(state.m):
            for c in xrange(state.n):
                if (r,c) not in state.eggs:
                    empty.append((r,c))

        while empty:
            temp = list(state.eggs)
            temp.append(empty.pop())
            if temp not in eggs_list:
                eggs_list.append(temp)

        # END (2)

        # START (3) 
        index = len(state.eggs) - 1
        while index > -1:
            temp = list(state.eggs)
            temp.pop(index)
            if temp not in eggs_list:
                eggs_list.append(temp)
            index -= 1
        # END (3)

        return_states = []
        for eggs in eggs_list:
            return_states.append(EggCartonState(state.k, state.m, state.n, eggs))

        return return_states

def main():
    for line in stdin:
        m, n, k = (int(x) for x in line.split(' '))
        print 'm=%s, n=%s, k=%s' % (m,n,k)
        start_state = EggCartonState(k, m, n, [])   #start with no eggs

        delta_t = 0.1
        t_max = 1000
        solver = EggCartonSolver(start_state, delta_t, t_max, m+n) 

        print 'thinking...'
        print solver.sa()

if __name__ == '__main__':
    main()
