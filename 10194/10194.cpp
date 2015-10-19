#include<algorithm>
#include<vector>
#include<map>
#include<string>
#include<iostream>
using namespace std;

struct Team {
    string name;
    int pointsEarned;
    int wins;
    int losses;
    int ties;
    int goalsScored;
    int goalsAgainst;
    int gamesPlayed;
    Team(string name):
        name(name),
        pointsEarned(0),
        wins(0),
        losses(0),
        ties(0),
        goalsScored(0),
        goalsAgainst(0),
        gamesPlayed(0)
    {}
};

bool most_points(Team* t1, Team* t2){
    return t2->pointsEarned < t1->pointsEarned;
}

bool most_wins (Team* t1, Team* t2){
    return t2->wins < t1->wins;
}

bool most_goal_difference (Team* t1, Team* t2){
    return (t2->goalsScored - t2->goalsAgainst)
        < (t1->goalsScored - t1->goalsAgainst);
}

bool most_goals_scored (Team* t1, Team* t2){
    return t2->goalsScored < t1->goalsScored; 
}

bool least_games_played (Team* t1, Team* t2){
    return t1->gamesPlayed < t2->gamesPlayed; 
}

bool by_name (Team* t1, Team* t2){
    return t1->name < t2->name; 
}

int main(){
    string junk;

    int N;
    cin >> N;
    getline(cin, junk); // discard trailing newline

    for(int i = 0; i < N; i++){
        string tournamentName;
        getline(cin, tournamentName);

        //cerr << tournamentName << endl;

        map<string, Team*> teamMap;
        vector<Team*> teams;

        int T;
        cin >> T;
        getline(cin, junk);
        for(int j = 0; j < T; j++){
            string teamName;
            getline(cin, teamName);
            //cerr << '\t' << teamName << endl;

            Team* team = new Team(teamName);
            teams.push_back(team);
            teamMap.insert(make_pair(teamName, team));
        }

        int G;
        cin >> G;
        getline(cin, junk);
        for(int k = 0; k < G; k++){
            string game;
            getline(cin, game);
            int firstNum  = game.find("#");
            int lastNum  = game.rfind("#")+1;
            string team1Name = game.substr(0, firstNum);
            string team2Name = game.substr(lastNum);
            string points = game.substr(
                    firstNum+1, 
                    lastNum-firstNum);
            int at = points.find("@");
            int team1Points = stoi(points.substr(0, at));
            int team2Points = stoi(points.substr(at+1));

            //cerr << '\t' << team1Name << " vs " << team2Name << endl;

            Team* team1 = teamMap[team1Name];
            Team* team2 = teamMap[team2Name];

            (team1->goalsScored) += team1Points;
            (team2->goalsScored) += team2Points;

            (team1->goalsAgainst) += team2Points;
            (team2->goalsAgainst) += team1Points;

            if(team1Points > team2Points){
                (team1->wins)++;
                (team1->pointsEarned) += 3;
                (team2->losses)++;
            }
            else if(team2Points > team1Points){
                (team2->wins)++;
                (team2->pointsEarned) += 3;
                (team1->losses)++;
            }
            else {
                (team1->ties)++;
                (team2->ties)++;
                (team1->pointsEarned)++;
                (team2->pointsEarned)++;
            }

            (team1->gamesPlayed)++;
            (team2->gamesPlayed)++;
        }

        stable_sort(teams.begin(), teams.end(), by_name);
        stable_sort(teams.begin(), teams.end(), least_games_played);
        stable_sort(teams.begin(), teams.end(), most_goals_scored);
        stable_sort(teams.begin(), teams.end(), most_goal_difference);
        stable_sort(teams.begin(), teams.end(), most_wins);
        stable_sort(teams.begin(), teams.end(), most_points);

        cout << tournamentName << endl;
        int rank = 0;
        for(vector<Team*>::iterator it=teams.begin(); it != teams.end(); it++){
            // "%d) %s %dp, %dg, (%d-%d-%d), %dgd (%d-%d)\n"
            cout
                << ++rank << ") "
                << (*it)->name << " "
                << (*it)->pointsEarned << "p, "
                << (*it)->gamesPlayed << "g, "
                << "("
                << (*it)->wins << "-"
                << (*it)->ties << "-"
                << (*it)->losses
                << "), "
                << (*it)->goalsScored - (*it)->goalsAgainst << "gd "
                << "("
                << (*it)->goalsScored << "-"
                << (*it)->goalsAgainst
                << ")"
                << endl;
            delete (*it);
        }
        cout << endl;
    }

    return 0;
}

