import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int MOD = 1000000000;

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int dp[][] = new int[n+1][k+1];

        // 초기화 0개로 만들수 있는것의 개수는 0개, 1개로 만들수 있는 개수는 N, 1개 뿐이다.
        for(int i=0;i<=n;i++){
            dp[i][0] = 0;
            dp[i][1] = 1;
        }

        // N이 1일 경우 만들수 있는 갯수는 K개
        for(int i=0;i<=k;i++){
            dp[1][i] = i;
        }

        for(int i=2;i<=n;i++){
            for(int j=2;j<=k;j++){
                dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % MOD;
            }
        }

        System.out.println(dp[n][k]);
    }
}
