import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static double arr[];
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new double[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        double max = 0;
        double val = 0;

        //제일 큰수를 찾아서 max값으로 만든다
        for(int i=0; i<n; i++){
            max = Math.max(max,arr[i]);
        }

        val = max;

        for(int i=0; i<n; i++){
            if(arr[i] != max){
                val += arr[i]/2;
            }
        }

        System.out.println(val);
    }
}
