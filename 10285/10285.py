#!/usr/bin/env python3
import sys

def solve(x, y):
    if results[x][y] < 0:
        n = 0;
        for dx, dy in ((x+1, y), (x-1, y), (x, y+1), (x, y-1)):
            if(         dx >= 0 and dx < len(mountain)
                    and dy >= 0 and dy < len(mountain[dx])
                    and mountain[dx][dy] < mountain[x][y]):
                n = max(n, solve(dx, dy));
        results[x][y] = n + 1;
    return results[x][y];

N = int(next(sys.stdin))

for _ in range(N):
    name, rows, cols = next(sys.stdin).split();
    rows, cols = map(int, (rows, cols))

    mountain = [];
    results = [];

    for _ in range(rows):
        line = list(map(int, next(sys.stdin).split()))
        mountain.append(line)
        results.append([-1 for _ in line]);

    for r in range(len(mountain)):
        for c in range(len(mountain[r])):
            solve(r, c);

    mx = max(map(lambda row: max(row), results))
    print("{}: {}".format(name, mx));


