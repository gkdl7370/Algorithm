import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] damage = {{9,3,1},{9,1,3},{3,9,1},{3,1,9},{1,9,3},{1,3,9}};
    static int[][][] dp;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[] arr = new int[3];
        int n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[arr[0]+1][arr[1]+1][arr[2]+1];

        bfs(arr);

        System.out.println(dp[0][0][0]);
    }

    private static void bfs(int[] arr) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{arr[0], arr[1], arr[2]});

        while (!q.isEmpty()){
            int[] poll = q.poll();
            if(poll[0] == 0 && poll[1] == 0 && poll[2] == 0) return;

            for(int i=0; i<6; i++){
                int a,b,c;
                int attack[] = damage[i];
                if(poll[0] - attack[0] > 0){
                    a = poll[0] - attack[0];
                } else {
                    a = 0;
                }

                if(poll[1] - attack[1] > 0){
                    b = poll[1] - attack[1];
                } else {
                    b = 0;
                }

                if(poll[2] - attack[2] > 0){
                    c = poll[2] - attack[2];
                } else {
                    c = 0;
                }

                if(dp[a][b][c] == 0){
                    dp[a][b][c] = dp[poll[0]][poll[1]][poll[2]] + 1;
                    q.add(new int[]{a,b,c});
                }
            }
        }

    }
}