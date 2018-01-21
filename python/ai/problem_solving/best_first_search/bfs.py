# -*- coding: utf-8 -*-
'''
Author: John R. Mienna
'''


class SearchNode(object):
    """
    An abstract helper class that takes care of all the state variables for a
    vertex-object

    :param state an object describing a state of the search process
    """
    def __init__(self, state):
        self.state = state  # an object describingi state of search process
        self.parent = None  # pointer to best parent
        self.children = []
        self.closed = False  # is the node open or closed?
        self.h = 0  # estimated cost to goal from this node
        self.g = 0  # cost of getting to this node
        self.f = 0  # estimated cost of solution path through this node; f=g+h

    def __eq__(self, other):
        return self.state.pk == other.state.pk


class SearchState(object):
    '''
    An abstract state object describing the state of the search process.

    Ovverride generate_id()
    '''
    def __init__(self):
        self.pk = 0

    def generate_id(self):
        raise NotImplementedError("generate_id() not implemented")


class AStar(object):

    '''
    An abstract class.
    Following functions must be overridden to accommodate the specific problem:

    generate_children_states(search_node)
    is_goal_state(search_node)
    compute_heuristic(search_node)
    edge_cost(searc_node, search_node)
    '''

    def __init__(self, initial_state, goal_state, **kwargs):
        self.root = None   # search node object
        self.goal_state = goal_state  # search state object identifying solution
        self.open_nodes = []
        self.closed_nodes = []
        self.states = {}    # dict of states that has been generated
        self.create_root_node(initial_state)
        self.kwargs = {}
        for key, value in kwargs.items():
            self.kwargs[key] = value

    def create_root_node(self, initial_state):
        '''
        '''
        raise NotImplementedError("create_root_node() not implemented")

    def generate_children_states(self, node):
        '''
        Given a node in the search tree (or graph), this applies all-swaps to
        that node’s state, with each application producing a different search
        state, all of which are returned as a collection to the expand-node
        method.
        '''
        raise NotImplementedError("g() has not been implemented")

    def is_goal_state(self, node):
        '''
        This compares the state of the node to the goal state stored in this A*
        object.

        :return True if they match exactly.
        '''
        return node.state.pk == self.goal_state.pk

    def compute_heuristic(self, node):
        '''
        Asseses the distance-to-goal of the node object.

        :param node problem-specific node object
        :return h estimated distance to goal
        '''
        raise NotImplementedError("compute_heuristic() not implemented")

    def edge_cost(self, from_node, to_node):
        '''
        Calculates edge cost between first_node and second_node
        '''
        raise NotImplementedError("edge_cost() has not been implemented")

    def attach_and_eval(self, child, node):
        '''
        Attaches child to node (wich is now considered its best parent -
        so far). The child’s value of g is then computed based on the parent’s
        value plus edge_cost(node, child)
        '''
        child.parent = node
        child.g = node.g + self.edge_cost(node, child)

        f = 0
        if 'dijkstra' in self.kwargs.values():
            f = child.g
        else:
            self.compute_heuristic(child)
            f = child.g + child.h

        child.f = f

    def propagate_path_improvements(self, node):
        '''
        Recurses through the children of node and possibly many other
        descendants. If the updates to node.g do not make node the best
        parent for a given child, the propagation ceases along that graph
        portion.
        '''
        for child in node.children:
            if node.g + self.edge_cost(node, child) < child.g:
                child.parent = node
                child.g = node.g + self.edge_cost(node, child)
                child.f = child.g + child.h
                self.propagate_path_improvements(child)

    def insert_open_node(self, node):
        '''
        insert node to appropriate place
        '''
        self.open_nodes.insert(0, node)

        if 'bfs' not in self.kwargs.values():
            for i in range(2, len(self.open_nodes)):
                x = self.open_nodes[i]
                j = i-1
                while j > 0 and self.open_nodes[j].f < x.f:
                    self.open_nodes[j + 1] = self.open_nodes[j]
                    j = j-1
                self.open_nodes[j + 1] = x

    def expand(self, node):
        '''
        Produces child nodes that may be added to open_nodes
        '''
        successors = self.generate_children_states(node)

        for succ in successors:

            if succ in self.closed_nodes:
                index = self.closed_nodes.index(succ)
                succ = self.closed_nodes[index]
            elif succ in self.open_nodes:
                index = self.open_nodes.index(succ)
                succ = self.open_nodes[index]

            node.children.append(succ)

            if succ not in self.open_nodes and succ not in self.closed_nodes:
                self.attach_and_eval(succ, node)
                self.insert_open_node(succ)
            elif node.g + self.edge_cost(node, succ) < succ.g:
                self.attach_and_eval(succ, node)
                if succ in self.closed_nodes:
                    self.propagate_path_improvements(succ)

    def recontruct_path(self, node):
        path = []
        while node.parent:
            path.insert(0, node)
            node = node.parent
        return path

    def get_solution_path(self):
        '''
        The agenda loop: Pops nodes from open_nodes and expands them

        :param start_node node at which to start the search
        :return node solution node or None if none found
        '''
        self.root.g = 0
        self.open_nodes.append(self.root)

        current = self.root
        while current.state is not self.goal_state:

            if not self.open_nodes:
                break

            current = self.open_nodes.pop()
            self.closed_nodes.append(current)

            if current.state.pk == self.goal_state.pk:
                solution_path = self.recontruct_path(current)
                return solution_path

            self.expand(current)

        return []
