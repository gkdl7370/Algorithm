import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n, max;
    static int map[][];
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        map = new int[n+1][n+1];
        max = 1000000000;

        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                if(i == j) map[i][j] = 0;
                else map[i][j] = max;
            }
        }

        while (true){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(a == -1 && b == -1) break;
            map[a][b] = 1;
            map[b][a] = 1;

        }

        for(int k=1; k<=n; k++){ //중간다리
            for(int i=1; i<=n; i++){
                map[i][0] = 0;
                for(int j=1; j<=n; j++){
                    if(i == j) continue;
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                    map[i][0] =  Math.max(map[i][j],map[i][0]);
                }
            }
        }

        int val = Integer.MAX_VALUE;
        int count = 0;
        ArrayList list = new ArrayList();;

        for(int i=1; i<=n; i++){
            val = Math.min(val,map[i][0]);
        }


        for(int i=1; i<=n; i++){
            if(map[i][0] == val){
                list.add(i);
                count++;
            }
        }

        System.out.println(val + " " + count);

        for(int i=0; i<list.size(); i++){
            System.out.print(list.get(i) + " ");
        }

    }
}
