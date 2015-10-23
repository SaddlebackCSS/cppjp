#!/usr/bin/env python3
import sys
import bisect


N = int(next(sys.stdin))
ladyChimps = list(map(int, next(sys.stdin).split()))

O = int(next(sys.stdin))
heightQueries = map(int, next(sys.stdin).split())


for query in heightQueries:
    shorter = bisect.bisect_left(ladyChimps, query)-1;
    taller = bisect.bisect_right(ladyChimps, query);

    if taller == len(ladyChimps):
        taller = ladyChimps[-1]
    else:
        taller = ladyChimps[taller]

    if shorter == len(ladyChimps):
        shorter = ladyChimps[-1]
    else:
        shorter = ladyChimps[shorter]

    print("{} {}".format(
            "X" if shorter >= query else shorter,
            "X" if taller  <= query else taller))

