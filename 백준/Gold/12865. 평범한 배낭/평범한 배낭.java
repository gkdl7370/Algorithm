import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 물건의 수
        int m = Integer.parseInt(st.nextToken()); // 배낭의 최대 무게

        int[] weight = new int[n];
        int[] value = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            weight[i] = Integer.parseInt(st.nextToken()); // 각 물건의 무게
            value[i] = Integer.parseInt(st.nextToken());  // 각 물건의 가치
        }

        // 동적 계획법 배열 (배낭 무게가 최대 m일 때 최대 가치를 저장) - 뒤에서부터 각 무게가 가질수있는 최대 가치를 저장한다
        int[] dp = new int[m + 1];

        for(int i=0; i<weight.length; i++){
            for(int j=m; j>=weight[i]; j--){
                dp[j] = Math.max(dp[j], dp[j-weight[i]] + value[i]);
            }
        }

        // 배낭에 넣을 수 있는 최대 가치를 출력
        System.out.println(dp[m]);
    }
}