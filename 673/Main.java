import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        
        for(int i = 0; i < n; i++){
            String line = in.nextLine();

            System.out.print(process(line)? "Yes": "No");
            System.out.print("\t" + line);
            System.out.println();
        }
    }

    private static boolean process(String line){
        Queue<Character> stack 
            = Collections.asLifoQueue(new ArrayDeque<Character>());
        for(char c : line.toCharArray()){
            switch(c){
                case '(':
                case '[':
                    stack.add(c);
                    break;
                case ')':
                case ']':
                    if(stack.isEmpty())
                        return false;
                    char last = stack.remove();
                    if (       (c == ')' && last != '(') 
                            || (c == ']' && last != '['))
                        return false;
                    break;
                default:
                    throw new IllegalArgumentException( "Invalid character: "
                            + c + " (" + (int)c + ")");
            }
        }
        return stack.isEmpty();
    }
}

