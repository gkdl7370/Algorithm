import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int dx[] = {1,0,-1,0};
    static int dy[] = {0,1,0,-1};
    static int n,m;
    static int map[][];
    static boolean vit[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        vit = new boolean[n][n];

        List<Integer> list = new ArrayList<>();
        for(int j=0; j<n; j++){
            String str = br.readLine();
            for(int k=0; k<n; k++){
                map[j][k] = str.charAt(k) -'0';
            }
        }
        int count = 0;
        for(int j=0; j<n; j++){
            for(int k=0; k<n; k++){
                if(map[j][k] == 1 && vit[j][k] != true){
                    count++;
                    int val = dfs(j,k); //탐색
                    list.add(val);
                }
            }
        }

        Collections.sort(list);

        System.out.println(count);
        for(int i=0; i<list.size(); i++){
            System.out.println(list.get(i));
        }
    }

    private static int dfs(int a, int b) {

        vit[a][b] = true; //방문체크
        int each = 1;

        for(int i=0; i<4; i++){
            int x = a + dx[i];
            int y = b + dy[i];

            if(x<0||y<0||x>=n||y>=n) continue;
            if(vit[x][y] == true) continue;
            if(map[x][y] == 0) continue;

            each += dfs(x,y);

        }


        return each;
    }


}

