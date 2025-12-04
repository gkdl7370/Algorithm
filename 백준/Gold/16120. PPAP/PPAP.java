import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String ppap = "PPAP";

        Stack<Character> st = new Stack<>();
        for(int i=0; i<str.length(); i++){
            st.push(str.charAt(i));
            if(st.size()>=4){
                boolean check = true;

                for(int k=0; k<4; k++){
                    if(st.get(st.size()-4+k) != ppap.charAt(k)){
                        check = false;
                        break;
                    }
                }


                if(check){
                    for(int k=0; k<4; k++){
                        st.pop();
                    }
                    st.push('P');
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int num = st.size();
        for(int i=0; i<num; i++){
            sb.append(st.get(i));
        }

        if(sb.charAt(0) == 'P' && sb.length() == 1) System.out.print("PPAP");
        else System.out.print("NP");
    }
}
