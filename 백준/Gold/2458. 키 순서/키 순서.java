import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static boolean vit[][];
    static ArrayList<Integer> list[];
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        vit = new boolean[n+1][n+1];
        list = new ArrayList[n+1];

        for(int i=1; i<=n; i++){
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
        }

        for(int i=1; i<=n; i++){
            dfs(i,i);
        }

        int value = 0;
        for(int i=1; i<=n; i++){
            boolean check = true;
            for(int j=1; j<=n; j++){
                if(i != j && vit[i][j] == false && vit[j][i] == false) {
                    check = false;
                    break;
                }
            }
            if(check == true) value++;
        }

        System.out.print(value);
    }

    private static void dfs(int a, int next) {
        for (int i = 0; i < list[next].size(); i++) {
            int num = list[next].get(i);
            if (vit[a][num] == false) {
                vit[a][num] = true;
                dfs(a, num);
            }
        }
    }
}
