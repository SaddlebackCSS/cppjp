//Dragon of Loowater
import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        boolean running = true;
        while(running){
            int numKnights, input, numHeads;
            List<Integer> knights = new ArrayList<>();
            List<Integer> dragonHeads = new ArrayList<>();
            List<Boolean> killed = new ArrayList<>();

            numHeads = in.nextInt();
            numKnights = in.nextInt();
//            System.err.println("Number of Dragons: " + numHeads);
//            System.err.println("Number of Knights: " + numKnights);

            if(numHeads == 0 && numKnights == 0){
                break;
            }

            for(int count = 0; count < numHeads; count++){
                int n = in.nextInt();
                dragonHeads.add(n);
//                System.err.println("Got Dragon Head " + n);
            }

            for(int count = 0; count < numKnights; count++){
                int n = in.nextInt();
                knights.add(n);
//                System.err.println("Got Knight " + n);
            }

            int x = 0;
            int counter = 0;
            int gold = 0;
            boolean doomed = false;

            if(numKnights >= numHeads){

                Collections.sort(dragonHeads);
                Collections.sort(knights);

                for(int count = 0; count < dragonHeads.size(); count++){
                    killed.add(false);
                }

                while(counter < dragonHeads.size() && x < knights.size()){
                    if(dragonHeads.get(counter) <= knights.get(x)){
                        killed.set(counter, true);
                        counter ++;
                        gold += knights.get(x);
                    }
                    x++;
                }

                counter = 0;
                while(counter < killed.size()){
                    if(!killed.get(counter)){
                        doomed = true;
                        break;
                    }
                    counter++;
                }
            }
            else{
                doomed = true;
            }

            if(doomed){
                System.out.println("Loowater is doomed!");
            }
            else{
                System.out.println(gold);
            }
        }
    }
}

