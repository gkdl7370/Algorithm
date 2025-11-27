import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int dx[] = {1,0,-1,0};
    static int dy[] = {0,1,0,-1};
    static int n,m;
    static int map[];
    static boolean vit[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[m];
        vit = new boolean[n+1];

        dfs(0);//0부터 돌리는건 무조건 1번은 돌아야하니까

    }

    private static void dfs(int dep) {

        //뎁스가 m이되면 빠져나가야된다
        if(dep == m){ //이때 출력까지 한번에
            for(int i=0; i<m; i++){
                System.out.print(map[i] + " ");
            }
            System.out.println();
            return;
        }
        // dfs + 백트래킹
        for(int i=1; i<=n; i++){
            if(vit[i] == false){//방문체크
                vit[i] = true;
                map[dep] = i;
                dfs(dep+1);
                vit[i] = false; //이게 백트래킹 방문 기록을 지워서 다음에 다시 방문 가능하도록
            }
        }
    }
}

