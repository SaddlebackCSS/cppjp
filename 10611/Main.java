//Playboy Chimp
import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();
        int[] ladyChimps = new int[N];

        for(int n = 0; n < N; n++){
            ladyChimps[n] = in.nextInt();
        }

        int O = in.nextInt();
        int[] heightQueries = new int[O];

        for(int n = 0; n < N; n++){
            heightQueries[n] = in.nextInt();
        }


        for(int query : heightQueries){
            int result = Arrays.binarySearch(ladyChimps, query);
//            System.err.println("raw result: " + result);
            if(result < 0){
                result += 1;
                result = -result;
            }
//            System.err.println("adj result: " + result);
            int taller, shorter;
            taller = shorter = -2;
            if(result >= ladyChimps.length){
                taller = -1;
                shorter = ladyChimps[ladyChimps.length-1];
            }
            else{
                int tmpr = result;
                while(tmpr < ladyChimps.length && ladyChimps[tmpr] <= query){
                    tmpr++;
                }
                if(tmpr < ladyChimps.length){
                    taller = ladyChimps[tmpr];
                }
                else{
                    taller = -1;
                }

                while(result > 0 && ladyChimps[result] >= query){
                    result--;
                }
                if(result < 0){
                    shorter = -1;
                }
                else{
                    shorter = ladyChimps[result];
                }
            }

            System.out.printf("%s %s\n",
                    shorter < 0? "X": ""+shorter,
                    taller < 0? "X": ""+taller
                    );
        }

    }
}


