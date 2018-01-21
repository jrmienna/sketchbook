def bellman_ford(G, s): D, P = {s:0}, {}
    for rnd in G:
        changed = False
        for u in G:
            for v in G[u]:
                if relax(G, u, v, D, P): 
                    changed = True
        if not changed:
            break 
        else:
            raise ValueError('negative cycle') return D, P
