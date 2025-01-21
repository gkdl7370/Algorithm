import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m, r;
    static int area[];
    static int map[][];

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        area = new int[n+1];
        map = new int[n+1][n+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++){
            area[i] = Integer.parseInt(st.nextToken());
        }

        int max = 1000000000;
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++) {
                map[i][j] = max;
            }
        }

        for(int i=1; i<=r; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(c > m) continue;
            map[a][b] = c;
            map[b][a] = c;

        }

        for(int k=1; k<=n; k++){
            for(int i=1; i<=n; i++){
                for(int j=1; j<=n; j++){
                    if(i == j) continue;
                    if(map[i][k] != max && map[k][j] != max){
                        if(map[i][k] + map[k][j] <= m){
                            map[i][j] = Math.min(map[i][j], map[i][k]+map[k][j]);
                        }
                    }
                }
            }
        }

        int count = 0;
        for(int i=1; i<=n; i++){
            int val = 0;
            for(int j=1; j<=n; j++) {
                if(map[i][j] != max){
                    val = val + area[j];
                }
            }
            val += area[i];
            count = Math.max(count,val);
        }

        System.out.print(count);
    }
}
