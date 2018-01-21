#!usr/bin/env python
#-*- coding: utf-8 -*-

import bfs
import time
from hashlib import md5

grid = []

EMPTY_CELL = '.'
OBSTACLE_CELL = '#'
START_CELL = 'A'
GOAL_CELL = 'B'

SOLUTION_CELL = 'o'
OPEN_CELL = '*'
CLOSED_CELL = 'x' 

WATER_CELL = 'w'
WATER_COST = 100
MOUNTAIN_CELL = 'm'
MOUNTAIN_COST = 50
FOREST_CELL = 'f'
FOREST_COST = 10
GRASS_CELL = 'g'
GRASS_COST = 5
ROAD_CELL = 'r'
ROAD_COST = 1

class GridCell(bfs.SearchNode):
    
    def __init__(self, state):
        super(GridCell, self).__init__(state)
        
    def __str__(self):
        return self.cell

    def is_empty(self):
        return True if self.cell == EMPTY_CELL else False

class GridState(bfs.SearchState):
    '''
    The state of the search is defined by the position in the grid
    at wich we're currently at.
    
    '''
    def __init__(self, x, y, cell):
        super(GridState, self).__init__()
        self.x = x
        self.y = y
        self.cell = cell  # a str representing the cell
        self.generate_id()

    def generate_id(self):
        self.pk = str(self.x) + str(self.y) + self.cell
        

    def __str__(self):
        return '(%s, %s)' %  (str(self.x), str(self.y))

class GridAStar(bfs.AStar):
    
    def __init__(self, initial_state, goal_state, **kwargs):
        super(GridAStar, self).__init__(initial_state, goal_state, **kwargs)

    def create_root_node(self, initial_state):
        '''
        :return node object with initail_state
        '''
        self.root = GridCell(initial_state)

    def generate_children_states(self, node):
        '''
        Check adjacent cells, if not empty cell:
        Create state and assign it to cell
        '''
        
        y_boundary = len(grid)
        x_boundary = len(grid[0])
        
        successors = []

        increment = {'dx': [-1, 1], 'dy': [-1, 1]}  # traverse through adjacent

        new_x = 0
        new_y = 0
        for key, value in increment.items():
            for incr in value:
                if key == 'dy' and (0 <= node.state.y + incr < y_boundary):
                    new_x = node.state.x
                    new_y = node.state.y + incr
                       
                if key == 'dx' and (0 <= node.state.x + incr < x_boundary):
                    new_x = node.state.x + incr
                    new_y = node.state.y

                cell = grid[new_y][new_x]
                if cell is not OBSTACLE_CELL:
                    child_state = GridState(new_x, new_y, cell)
                    child = GridCell(child_state)
                    successors.append(child)
        
        return successors


    def is_goal_state(self, node):
        super(GridAStar, self).is_goal_state(node)

    def compute_heuristic(self, node):
        '''
        Use manhatten-distance to estimate distance to goal
        '''
        node.h = abs(node.state.x - self.goal_state.x) 
        + abs(node.state.y - self.goal_state.y)

    def edge_cost(self, from_, to_):
        '''
        In this problem the egde cost is 1 for all edges
        :return cost edge cost between from_node and to_node
        '''
        if to_.state.cell == WATER_CELL: return WATER_COST
        if to_.state.cell == MOUNTAIN_CELL: return MOUNTAIN_COST
        if to_.state.cell == FOREST_CELL: return FOREST_COST
        if to_.state.cell == GRASS_CELL: return GRASS_COST
        if to_.state.cell == ROAD_CELL: return ROAD_COST
        return 1 
        

import sys, getopt
import traceback

def print_board(bfs=None):
    if bfs:
        solution_path = bfs.get_solution_path()
        for node in bfs.open_nodes:
           if node != bfs.root and node not in solution_path:
               grid[node.state.y][node.state.x] == OPEN_CELL

        for node in bfs.closed_nodes:
            if node != bfs.root and node not in solution_path:
                grid[node.state.y][node.state.x] = CLOSED_CELL

        k = len(solution_path)
        for node in solution_path[:k-1]:
            grid[node.state.y][node.state.x] = SOLUTION_CELL

    board_string = ''
    for row in grid:
        out = ''
        for cell in row:
            out += cell + ' '
        board_string += out + '\n'

    print board_string

def main(argv):
    inputfile = None
    algorithm = None

    try:
        opts, args = getopt.getopt(argv, "hi:algorithm:",["ifile=", "algorithm="]) 
    except getopt.GetoptError:
        print 'grid_program.py -i <inputfile> -a <algorithm>'
        sys.exit(2)

    for opt, arg in opts:
        if opt == '-h':
            print 'grid_program.py -i <inputfile> -a <algorithm>\n'
            print '<algorithm>:'
            print '-    a_star (=default)'
            print '-    bfs'
            print '-    dijkstra'
            sys.exit()
        elif opt in ('-i', '--ifile'):
            inputfile = arg
        elif opt in ('-a', '--algorithm'):
            algorithm = arg

    print inputfile
    print algorithm

    if inputfile and algorithm in ['a_star', 'bfs', 'dijkstra']:
        print 'Inputfile is %s' % inputfile
        print 'Algorithm is %s' % algorithm

        initial_state = None
        goal_state = None

        try:
            f = open(inputfile, 'r')
            line = f.readline()

            x = 0
            y = 0
            while line:
                row = []
                for cell in line:
                    if cell == EMPTY_CELL:
                        row.append(EMPTY_CELL)        
                    if cell == OBSTACLE_CELL:
                        row.append(OBSTACLE_CELL)

                    if cell == WATER_CELL:
                        row.append(WATER_CELL)
                    if cell == MOUNTAIN_CELL:
                        row.append(MOUNTAIN_CELL)
                    if cell == FOREST_CELL:
                        row.append(FOREST_CELL)
                    if cell == GRASS_CELL:
                        row.append(GRASS_CELL)
                    if cell == ROAD_CELL:
                        row.append(ROAD_CELL)

                    if cell == START_CELL:
                        row.append(START_CELL)
                        initial_state = GridState(x, y, START_CELL)
                    if cell == GOAL_CELL:
                        row.append(GOAL_CELL)
                        goal_state = GridState(x, y, GOAL_CELL)
                    x += 1
                grid.append(row)
                line = f.readline()
                x = 0
                y += 1

            a = GridAStar(initial_state, goal_state, alg = algorithm)

            print '*=open, x=closed, o=soulition'
            print_board(a)
        except:
            traceback.print_exc(file=sys.stderr)
        finally:
            f.close()

    else:
        print 'fail'
        print 'grid_program.py -i <inputfile> -a <algorithm>'
        sys.exit()



if __name__ == '__main__':
    main(sys.argv[1:])
