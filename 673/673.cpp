#include<iostream>
#include<stack>
#include<string>
using namespace std;

bool process(string line){
    stack<char> stk;

    for(string::iterator it = line.begin(); it != line.end(); it++){
        char c = *it;
        switch(c){
            case '(':
            case '[':
                stk.push(c);
                break;
            case ')':
            case ']':
                if ( stk.empty())
                    return false;
                {
                char last = stk.top();
                if (       (c == ')' and last != '(') 
                        or (c == ']' and last != '['))
                    return false;
                }
                stk.pop();
                break;
            default:
                cerr << "Invalid Character: " 
                    << c << " (" << int(c) << ")" <<endl;
        }
    }

    return stk.empty();
}

int main(){
    int n;
    cin >> n;
    cin.ignore(2, '\n');

    for(int i = 0; i < n; i++){
        string line;
        getline(cin, line);

        cout << (process(line)? "Yes": "No")
            << '\t' << line
            << endl;
    }

    return 0;
}

