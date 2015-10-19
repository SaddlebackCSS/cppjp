#!/usr/bin/env python3

def run(s):
    stack = []
    for c in s:
        if c == '(' or c == '[':
            stack.append(c)
        elif c == ')' or c == ']':
            if not stack:
                return False
            last = stack.pop()
            if (       (c == ')' and last != '(') 
                    or (c == ']' and last != '[')):
                return False
        else:
            raise ValueError('invalid character in string: {} ({})'.format(
                c, ord(c)))
    return not stack


if __name__ == '__main__':
    import sys
    next(sys.stdin) # ignore first line
    for case in sys.stdin:
        case = case.strip()
        print('Yes' if run(case) else 'No', case, sep="\t")

