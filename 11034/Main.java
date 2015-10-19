import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int c = in.nextInt();
        in.nextLine();

        for(int i = 0; i < c; i++){
            Queue<Integer> left = new ArrayDeque<>();
            Queue<Integer> right = new ArrayDeque<>();

            int l = in.nextInt() * 100;
            int m = in.nextInt();
            in.nextLine();

            for(int j = 0; j < m; j++){
                String line = in.nextLine();
                String[] tokens = line.split(" ");
                int length = Integer.parseInt(tokens[0]);
                String bank = tokens[1];

                switch(bank){
                    case "left":
                        left.add(length);
                        break;
                    case "right":
                        right.add(length);
                        break;
                    default:
                        throw new IllegalStateException(
                                "Invalid bank: " + bank);
                }
            }

            int total_length = 0;
            int trips = 0;
            Queue<Integer> current = left;
            while(!left.isEmpty() || !right.isEmpty()){
                while(!current.isEmpty() 
                        && total_length + current.element() <= l){
                    total_length += current.remove();
                }
                trips++;
                total_length = 0;
                if(current == left) current = right; else current = left;
            }
            System.out.println(trips);
        }
    }
}

