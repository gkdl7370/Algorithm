import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str = br.readLine();

        Stack<Character> stack = new Stack<>();

        int val = 1;
        int result = 0;
        for(int i=0; i<str.length(); i++){
            if(str.charAt(i) == '('){
                stack.push(str.charAt(i));
                val *= 2;
            } else if (str.charAt(i) == '['){
                stack.push(str.charAt(i));
                val *= 3;
            } else if (str.charAt(i) == ')'){
                if(stack.empty() || stack.peek() != '('){
                    result = 0;
                    break;
                } else if(str.charAt(i-1) == '('){
                   result = result + val;
                }
                stack.pop();
                val = val / 2;
            } else if (str.charAt(i) ==']'){
                if(stack.empty() || stack.peek() != '['){
                    result = 0;
                    break;
                } else if(str.charAt(i-1) == '['){
                    result = result + val;
                }
                stack.pop();
                val = val / 3;
            }
        }

        if(!stack.isEmpty()) sb.append(0).append("\n");
        else sb.append(result).append("\n");
        System.out.println(sb);
    }

}