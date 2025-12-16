import java.util.*;
import java.io.*;

public class Main {
    static int e,s,m;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        e = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int first = 15;
        int second = 28;
        int third = 19;

        int val1 = 0;
        int val2 = 0;
        int val3 = 0;
        int start = 1;

        while(true){

            val1 = (start-1) % first + 1;
            val2 = (start-1) % second + 1;
            val3 = (start-1) % third + 1;

            if(val1 == e && val2 == s && val3 == m){
                break;
            }

            start++;
        }

        System.out.print(start);

    }
}
