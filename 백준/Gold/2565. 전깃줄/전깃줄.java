import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int lines [][] = new int[n][2];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            lines[i][0] = Integer.parseInt(st.nextToken());
            lines[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(lines, new Comparator<int[]>() { //다차원 배열 오름차순으로 정렬
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });

        int dp[] = new int[n];

        Arrays.fill(dp,1); //선은 무조건 하나는 그을수있으니 1로 초기화


        for(int i=1; i<n; i++){
            for(int j=0; j<i; j++){
                if(lines[j][1] < lines[i][1]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }

        int lis = 1;
        for(int i=0; i<n; i++){
            lis = Math.max(lis,dp[i]);
        }

        System.out.print(n-lis);
    }
}