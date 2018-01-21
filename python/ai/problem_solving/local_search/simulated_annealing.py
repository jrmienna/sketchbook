'''
Temperature parameter controls degree to wich the search procedure
EXPLORES in random directions rather than
EXPLOITING directions that have already been proved to yield good results
'''

from abc import ABCMeta, abstractmethod
from numpy import inf
from math import exp
from random import uniform, randint

class SimulatedAnnealingState(object):

    __metaclass__ = ABCMeta
 
    @abstractmethod
    def evaluate(self):
        """docstring for evaluate"""
        

        
class SimulatedAnnealing(object):

    def __init__(self, start_state, delta_temp, max_temp, target_val):
        """docstring for __init__"""
        self.start_state = start_state
        self.delta_temp = delta_temp
        self.max_temp = max_temp
        self.target_val = target_val

    @abstractmethod
    def generate_neighbors(self, state):
        """docstring for generate_neighbors"""
    
    def sa(self):

        current = self.start_state
        t = self.max_temp

        # Loops until temp is 0
        while t > 0:

            val = current.evaluate()
            # End search if current state has a evaluation that meets the
            # target value
            if val > self.target_val:
                return current

            # Generate list of neighboring states
            neighbors = self.generate_neighbors(current)
            
            # Create list of the evaluation values of neighboring states
            neighbors_val = [neighbor.evaluate() for neighbor in neighbors]
           
            # Find best evaluation
            max_val = max(neighbors_val)

            # Find index of neighbor state with best evaluation, doesn't matter
            # if more than one neighbor has the best value
            index = neighbors_val.index(max(neighbors_val))

            # Select best neighbor state
            best_state = neighbors[index]
            
            # Do some calculations
            q = (max_val - val)/val if val > 0 else inf
            p = min(1, exp(-q/t))

            # Use random rumber to determine if the algorithm should take 
            # advantage of the best neighboring state or just choose a random
            # neighbor state
            if uniform(0,1) > p:
                current = best_state  # Exploit
            else:
                current = neighbors[randint(0, len(neighbors) - 1)]  # Explore

            # Decrease temp
            t -= self.delta_temp

        print "DIDN'T FIND SOLUTION"
        return current
