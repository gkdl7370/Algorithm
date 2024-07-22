
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static String s;
    static String t;
    static int val;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        s = br.readLine();
        t = br.readLine();


        val = 0;
        makeT(t);

        System.out.println(val);
    }

    private static void makeT(String str) {
        int len = str.length();

        if(len == s.length()){
            if(str.equals(s)){
                val = 1;
                return;
            }
        }


        if(str.endsWith("A")){
            makeT(str.substring(0,len-1));
        }

        String reverse = "";
        for(int i=len-1; i>=0; i--){
            reverse += str.charAt(i);
        }

        if(str.startsWith("B")){
            makeT(reverse.substring(0,reverse.length()-1));
        }

    }

}
