#!/usr/bin/env python3
import sys

class Team:
    def __init__(self, name):
        self.name = name
        self.pointsEarned = 0
        self.wins = 0
        self.losses = 0
        self.ties = 0
        self.goalsScored = 0
        self.goalsAgainst = 0
        self.goalDifference = 0
        self.gamesPlayed = 0

    def __str__(self):
        return ('{0.name} '
                '{0.pointsEarned}p, '
                '{0.gamesPlayed}g, '
                '({0.wins}-{0.ties}-{0.losses}), '
                '{0.goalDifference}gd '
                '({0.goalsScored}-{0.goalsAgainst})').format(self)

N = int(next(sys.stdin))

for i in range(N):
    tournamentName = next(sys.stdin).strip()
    teamMap = {}
    teams = []

    T = int(next(sys.stdin))
    for j in range(T):
        teamName = next(sys.stdin).strip()
        team = Team(teamName)
        teamMap[teamName] = team
        teams.append(team)

    G = int(next(sys.stdin))
    for k in range(G):
        game = next(sys.stdin).strip()
        team1Info, team2Info = game.split("@")

        team1Name, team1Points = team1Info.split("#")
        team2Points, team2Name = team2Info.split("#")

        team1Points, team2Points = map(int, [team1Points, team2Points])

        team1 = teamMap[team1Name]
        team2 = teamMap[team2Name]

        team1.goalsScored += team1Points
        team2.goalsScored += team2Points

        team1.goalsAgainst += team2Points
        team2.goalsAgainst += team1Points

        team1.goalDifference = team1.goalsScored - team1.goalsAgainst
        team2.goalDifference = team2.goalsScored - team2.goalsAgainst

        if(team1Points > team2Points):
            team1.wins += 1
            team1.pointsEarned += 3
            team2.losses += 1
        elif(team2Points > team1Points):
            team2.wins += 1
            team2.pointsEarned += 3
            team1.losses += 1
        else :
            team1.ties += 1
            team2.ties += 1
            team1.pointsEarned += 1
            team2.pointsEarned += 1

        team1.gamesPlayed += 1
        team2.gamesPlayed += 1

    teams.sort(key=lambda t: t.name.lower())
    teams.sort(key=lambda t: t.gamesPlayed)
    teams.sort(key=lambda t: t.goalsScored, reverse=True)
    teams.sort(key=lambda t: t.goalDifference, reverse=True)
    teams.sort(key=lambda t: t.wins, reverse=True)
    teams.sort(key=lambda t: t.pointsEarned, reverse=True)

    print(tournamentName)
    for rank, t in enumerate(teams):
        print("{}) {}".format( rank+1, t ))
    print()

