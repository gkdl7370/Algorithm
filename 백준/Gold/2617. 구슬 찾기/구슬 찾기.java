import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n + 1][n + 1];

        // 초기 입력값 저장
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map[a][b] = 1; // a가 b보다 무겁다.
            map[b][a] = 2; // a가 b보다 가볍다.
        }

        //플로이드워샬이란 a -> b b -> c면 a -> c다 명심해라
        //모르겠으면 for문 3개 쓰고 생각해
        for(int k=1; k<=n; k++){ //이놈은 중간
            for(int i=1; i<=n; i++){ //이놈은 시작
                for(int j=1; j<=n; j++){ //이놈은 도착
                    if(map[i][k] == 1 && map[k][j] == 1){
                        map[i][j] = 1;
                    }
                    if(map[i][k] == 2 && map[k][j] == 2){
                        map[i][j] = 2;
                    }
                }
            }
        }

        int val = 0;
        for(int i=1; i<=n; i++){
            int count = 0;
            int count2 = 0;
            for(int j=1; j<=n; j++){
                if(i == j) continue;
                if(map[i][j] == 1 ) count++;
                if(map[i][j] == 2 ) count2++;
            }
            if(count > n/2 || count2 > n/2) val++;
        }

        System.out.println(val);
    }
}