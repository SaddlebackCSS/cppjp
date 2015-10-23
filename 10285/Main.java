//Snowboarding
import java.util.*;
public class Main{
    private static int[][] mountain;
    private static int[][] results;

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        in.nextLine();

        for(int n = 0; n < N; n++){
            String name = in.next();
            int rows = in.nextInt();
            int cols = in.nextInt();
            in.nextLine();

            mountain = new int[rows][cols];
            results = new int[rows][cols];

            for(int r = 0; r < rows; r++){
                for(int c = 0; c < cols; c++){
                    mountain[r][c] = in.nextInt();
                    results[r][c] = -1;
                }
            }
            in.nextLine();

            for(int r = 0; r < mountain.length; r++){
                for(int c = 0; c < mountain[r].length; c++){
                    solve(r, c);
                }
            }

            int max = -1;
            for(int r = 0; r < results.length; r++){
                for(int c = 0; c < results[r].length; c++){
                    max = Math.max(max, results[r][c]);
                }
            }
            System.out.printf("%s: %d\n", name, max);
        }
    }

    private static int solve(int x, int y){
        if(results[x][y] < 0){
            int n = 0;
            for(int dx = -1; dx <= 1; dx+=2){
                if(        x+dx >= 0 && x+dx < mountain.length
                        && mountain[x+dx][y] < mountain[x][y]){
                    n = Math.max(n, solve(x+dx, y));
                }
            }
            for(int dy = -1; dy <= 1; dy+=2){
                if(        y+dy >= 0 && y+dy < mountain[x].length
                        && mountain[x][y+dy] < mountain[x][y]){
                    n = Math.max(n, solve(x, y+dy));
                }
            }
            results[x][y] = n + 1;
        }
        return results[x][y];
    }
}


