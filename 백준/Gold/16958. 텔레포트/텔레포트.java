import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, t;
    static int[][] dist;  // 최단 거리 저장
    static int[] isSuper; // 슈퍼 도시 여부

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        int[][] cities = new int[n + 1][2]; // 도시 좌표 저장 (x, y)
        isSuper = new int[n + 1];
        dist = new int[n + 1][n + 1];

        // 도시 정보 입력
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            isSuper[i] = Integer.parseInt(st.nextToken()); // 슈퍼 도시 여부
            cities[i][0] = Integer.parseInt(st.nextToken()); // x 좌표
            cities[i][1] = Integer.parseInt(st.nextToken()); // y 좌표
        }


        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) dist[i][j] = 0;
                else dist[i][j] = Math.abs(cities[i][0] - cities[j][0]) + Math.abs(cities[i][1] - cities[j][1]);
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (isSuper[i] == 1 && isSuper[j] == 1){
                    dist[i][j] = Math.min(dist[i][j], t);
                }
            }
        }


        for(int k=1; k<=n; k++){
            for(int i=1; i<=n; i++) {
                for (int j = 1; j <= n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k]+dist[k][j]);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());
        for(int i=0; i<p; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            System.out.println(dist[a][b]);
        }


    }
}
