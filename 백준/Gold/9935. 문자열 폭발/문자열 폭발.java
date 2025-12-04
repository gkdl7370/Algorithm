import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String bomb = br.readLine();

        int bLen = bomb.length();
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
           st.push(str.charAt(i)); //스택에 값을 하나씩 넣고

            if(st.size()>=bLen){ //폭탄 수만큼 스택이 쌓이면
                boolean check = true;

                for(int k=0; k<bLen; k++){
                    if(st.get(st.size() - bLen + k) != bomb.charAt(k)){
                        check = false;
                        break;
                    }
                }

                if(check == true){
                    for(int k=0; k<bLen; k++){
                        st.pop();
                    }
                }
            }
        }

        
        int num = st.size();
        // 남은 문자 출력
        if (st.isEmpty()) {
            System.out.println("FRULA");
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < num; i++) {
                sb.append(st.get(i));
            }
            System.out.println(sb);
        }
    }
}
