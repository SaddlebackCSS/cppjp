#!/usr/bin/env python3
import sys

while True:
    knights = []
    dragonHeads = []

    numHeads, numKnights = map(int, next(sys.stdin).split())

    if numHeads == 0 and numKnights == 0:
        break;

    for _ in range(numHeads):
        dragonHeads.append(int(next(sys.stdin)));

    for _ in range(numKnights):
        knights.append(int(next(sys.stdin)));

    x = 0;
    counter = 0;
    gold = 0;
    doomed = False;

    if numKnights >= numHeads:

        dragonHeads.sort();
        knights.sort();

        killed = [False for _ in range(numHeads)];

        dragonCounter = iter(dragonHeads)
        dragon = next(dragonCounter)
        for knight in knights:
            if dragon <= knight:
                killed[counter] = True;
                counter += 1
                gold += knight;
                try:
                    dragon = next(dragonCounter)
                except StopIteration:
                    break

        doomed = not all(killed)
    else:
        doomed = True;

    if doomed:
        print("Loowater is doomed!");
    else:
        print(gold);


