#include<queue>
#include<string>
#include<iostream>
using namespace std;

int main(){
    string junk;
    int c;
    cin >> c;
    getline(cin, junk);

    for(int i = 0; i < c; i++){
        queue<int> left;
        queue<int> right;

        int l, m;
        cin >> l >> m;
        l *= 100;
        getline(cin, junk);

        for(int j = 0; j < m; j++){
            string line;
            getline(cin, line);
            int length = stoi(line.substr(0, line.find(" ")));
            string bank = line.substr(line.find(" ")+1);

            if(bank == "left"){
                left.push(length);
            }
            else if (bank == "right"){
                right.push(length);
            }
            else {
                cerr << "Invalid bank: " << bank << endl;
                return 1;
            }
        }

        int total_length = 0;
        int trips = 0;
        queue<int> queues[] = {left, right};
        int current = 0;
        while( !queues[0].empty() || !queues[1].empty() ){
            while( !queues[current].empty() 
                    && total_length + queues[current].front() <= l){
                total_length += queues[current].front();
                queues[current].pop();
            }
            trips++;
            total_length = 0;
            current = (current+1) % 2;
        }
        cout << trips << endl;
    }

    return 0;
}

