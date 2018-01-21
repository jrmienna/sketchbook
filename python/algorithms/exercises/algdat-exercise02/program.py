from sys import stdin
from collections import deque

class Node:
    children = None 
    ratatosk = None
    child_counter = None 

    def __init__(self):
        self.children = []
        self.ratatosk = False
        self.child_counter = 0

def dfs(root):
    stack = []
    stack.append(root)
    while stack:
        vertex = stack[len(stack)-1]
        if vertex.ratatosk:
            return len(stack) - 1
        if vertex.child_counter == len(vertex.children):
            stack.pop()
        else:
            stack.append(vertex.children[vertex.child_counter])
            vertex.child_counter += 1


def bfs(root):
    q = deque()
    q.append((0, root))
    while q:
        depth, vertex = q.popleft()
        if vertex.ratatosk:
            return depth
        for child in vertex.children:
            q.append((depth+1, child))
    return 0


func = stdin.readline().strip()
numb_nodes = int(stdin.readline())
nodes = []
for i in range(numb_nodes):
    nodes.append(Node())
start_node = nodes[int(stdin.readline())]
ratatosk_node = nodes[int(stdin.readline())]
ratatosk_node.ratatosk = True
for line in stdin:
    numb = line.split()
    temp_node = nodes[int(numb.pop(0))]
    for children_nr in numb:
        temp_node.children.append(nodes[int(children_nr)])

if func == 'dfs':
    print dfs(start_node)
elif func == 'bfs':
    print bfs(start_node)
elif func == 'choose':
    print 'choose'
