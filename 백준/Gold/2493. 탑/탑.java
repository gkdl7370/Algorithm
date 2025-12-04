import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static int n;
    static int map[];
    static int val[];
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new int[n+1];
        val = new int[n+1]; //순번이니까 n개수만큼만 하면 될 듯

        String str[] = br.readLine().split(" ");
        for(int i=1; i<=str.length; i++){
            map[i] = Integer.parseInt(str[i-1]);
            val[i] = 0; //초기화
        }

        //뒤에서 값을 하나 꺼내서 스택에 저장해 둔다(주소값을 저장)
        //현재 값을 스택에 있는값이랑 비교해본다 for문
        //크면 현재값의 i를 스택에 있는값(주소값)에 넣는다

        Stack<Integer> st = new Stack<>();
        int num = n+1;

        for(int i=1; i<=num; i++){

            while (!st.isEmpty()){ //스택에 값이 있다면?
                if(map[num-i] > map[st.peek()]){ //지금값이랑 스택에 있는 주소값의 값이랑 비교
                    //크다면
                    val[st.pop()] = num-i;
                } else{
                    break; //아니면 빠져나가서 다음 값이랑 비교해야지
                }
            }
            st.push(num-i);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++){
            sb.append(val[i] + " ");
        }

        System.out.print(sb);
    }
}
