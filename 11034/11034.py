#!/usr/bin/env python3

import sys
from collections import deque

c = int(next(sys.stdin))

for _ in range(c):
    left = deque()
    right = deque()

    l, m = next(sys.stdin).strip().split()
    l, m = map(int, (l, m))
    l *= 100

    for _ in range(m):
        length, bank = next(sys.stdin).strip().split()
        length = int(length)
        eval(bank).append(length)

    total_length = 0
    trips = 0
    current_queue, last_queue = left, right
    while current_queue or last_queue:
        while current_queue and total_length + current_queue[0] <= l:
            total_length += current_queue.popleft()
        trips += 1
        total_length = 0
        current_queue, last_queue = last_queue, current_queue
    print(trips)


