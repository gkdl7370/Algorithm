
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int map[] = new int[n+1];
        int count[] = new int[1000001];
        int val[] = new int[n+1];
        
        String[] str = br.readLine().split(" ");
        for (int i = 1; i <= n; i++) {
            int a = Integer.parseInt(str[i-1]);            
            map[i] = a;
            count[a]++;
            val[i] = -1;
        }
        Stack<Integer> stack = new Stack<>();
        for(int i=1; i<=n; i++){
            while (!stack.isEmpty()){
                int next = map[i];
                int now = map[stack.peek()];
                if(count[next] > count[now]){
                    val[stack.peek()] = next;
                    stack.pop();

                } else{
                    break;
                }
            }
            stack.push(i); //맨처음에는 0[배열의 주소값 처음부터 넣을거니까]이 들어가겠지
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++){
            sb.append(val[i]).append(" ");
        }

        System.out.print(sb);
    }
}