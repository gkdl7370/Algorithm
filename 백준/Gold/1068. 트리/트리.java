import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int arr[];
    static boolean val[];
    static int m;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new int[n];
        val = new boolean[n];
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());

        dfs(m);

        int count = 0;
        for(int i=0; i<n; i++){
            if(arr[i] == -9) continue; //지워진 노드
            boolean check = true;
            for(int j=0; j<n; j++){
                if(arr[j] == i){
                    check = false;
                    break;
                }
            }
            if(check == true) count++;
        }
        System.out.print(count);
    }

    private static void dfs(int a) {
        arr[a] = -9;
        for(int i=0; i<arr.length; i++){
            if(arr[i] == a){
                dfs(i);
            }
        }
    }
}
