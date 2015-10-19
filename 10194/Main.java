import java.util.*;
public class Main{
    static class Team{
        String name;
        int pointsEarned;
        int wins;
        int losses;
        int ties;
        int goalsScored;
        int goalsAgainst;
        int gamesPlayed;
        public Team(String name){
            this.name = name;
            pointsEarned = 0;
            wins = 0;
            losses = 0;
            ties = 0;
            goalsScored = 0;
            goalsAgainst = 0;
            gamesPlayed = 0;
        }
    }

    static class MostPoints implements Comparator<Team>{
        public int compare(Team t1, Team t2){
            return t2.pointsEarned - t1.pointsEarned;
        }
    }

    static class MostWins implements Comparator<Team>{
        public int compare(Team t1, Team t2){
            return t2.wins - t1.wins;
        }
    }

    static class MostGoalDifference implements Comparator<Team>{
        public int compare(Team t1, Team t2){
            return (t2.goalsScored - t2.goalsAgainst)
                - (t1.goalsScored - t1.goalsAgainst);
        }
    }

    static class MostGoalsScored implements Comparator<Team>{
        public int compare(Team t1, Team t2){
            return t2.goalsScored - t1.goalsScored; 
        }
    }

    static class LeastGamesPlayed implements Comparator<Team>{
        public int compare(Team t1, Team t2){
            return t1.gamesPlayed - t2.gamesPlayed; 
        }
    }

    static class ByName implements Comparator<Team>{
        public int compare(Team t1, Team t2){
            return t2.name.toLowerCase().compareTo(t1.name.toLowerCase()); 
        }
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        in.nextLine();
        //System.err.println("N: " + N);

        for(int i = 0; i < N; i++){
            String tournamentName = in.nextLine();
            //System.err.println("tournamentName: " + tournamentName);
            Map<String, Team> teamMap = new HashMap<>();
            List<Team> teams = new ArrayList<>();

            int T = in.nextInt();
            in.nextLine();
            for(int j = 0; j < T; j++){
                String teamName = in.nextLine();
                //System.err.println("inserting: " + teamName);
                Team team = new Team(teamName);
                teamMap.put(teamName, team);
                teams.add(team);
            }

            int G = in.nextInt();
            in.nextLine();
            for(int k = 0; k < G; k++){
                String game = in.nextLine();
                String[] teamInfos = game.split("@");
                String[][] teamInfoses = new String[2][2];
                teamInfoses[0] = teamInfos[0].split("#");
                teamInfoses[1] = teamInfos[1].split("#");

                String team1Name = teamInfoses[0][0];
                String team2Name = teamInfoses[1][1];
                int team1Points = Integer.parseInt(teamInfoses[0][1]);
                int team2Points = Integer.parseInt(teamInfoses[1][0]);

                //System.err.println("accessing: " + team1Name);
                //System.err.println("accessing: " + team2Name);

                Team team1 = teamMap.get(team1Name);
                Team team2 = teamMap.get(team2Name);

                team1.goalsScored += team1Points;
                team2.goalsScored += team2Points;

                team1.goalsAgainst += team2Points;
                team2.goalsAgainst += team1Points;

                if(team1Points > team2Points){
                    team1.wins++;
                    team1.pointsEarned += 3;
                    team2.losses++;
                }
                else if(team2Points > team1Points){
                    team2.wins++;
                    team2.pointsEarned += 3;
                    team1.losses++;
                }
                else {
                    team1.ties++;
                    team2.ties++;
                    team1.pointsEarned++;
                    team2.pointsEarned++;
                }

                team1.gamesPlayed++;
                team2.gamesPlayed++;
            }

            Collections.sort(teams, new ByName());
            Collections.sort(teams, new LeastGamesPlayed());
            Collections.sort(teams, new MostGoalsScored());
            Collections.sort(teams, new MostGoalDifference());
            Collections.sort(teams, new MostWins());
            Collections.sort(teams, new MostPoints());

            System.out.println(tournamentName);

            int rank = 0;
            for(Team t : teams){
                System.out.printf("%d) %s %dp, %dg, (%d-%d-%d), %dgd (%d-%d)\n",
                        ++rank,
                        t.name,
                        t.pointsEarned,
                        t.gamesPlayed,
                        t.wins,
                        t.ties,
                        t.losses,
                        t.goalsScored - t.goalsAgainst,
                        t.goalsScored,
                        t.goalsAgainst
                        );
            }
            System.out.println();
        }
    }
}

