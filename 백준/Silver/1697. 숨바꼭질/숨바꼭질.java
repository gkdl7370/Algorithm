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
        map = new int[100001];

        Queue<Integer> q = new LinkedList<>();
        q.add(n);
        map[n] = 0;

        while (!q.isEmpty()){
            int now = q.poll();

            if(now == m) break;

            for(int i=0; i<3; i++){
                int next = now;
                if(i==0){
                    next = now-1;
                } else if(i==1){
                    next = now+1;
                } else {
                    next = now*2;
                }
                if(next == n) continue;
                if(next<0 || next>100000) continue;
                if(map[next] != 0) continue;
                map[next] = map[now] + 1;
                q.add(next);
            }
        }

        System.out.print(map[m]);
    }
}

