import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int map[][] = new int[n][m];
        int dp[][] = new int[n][m];

        boolean check = false;

        for(int i=0; i<n; i++){
            String str = br.readLine();
            for(int j=0; j<str.length(); j++){
                map[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
                if(map[i][j] == 1){
                    check = true;
                }
            }
        }

        if(n == 1 || m == 1){
            if(check == true) System.out.println(1);
            else System.out.println(0);
            return;
        }


        int max = 0;

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(map[i][j] == 1){
                    if(i == 0 || j == 0){
                        dp[i][j] = 1;
                    } else{
                        dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                    }
                    max = Math.max(max, dp[i][j]);
                }
            }
        }

        System.out.print(max*max);

    }
}
