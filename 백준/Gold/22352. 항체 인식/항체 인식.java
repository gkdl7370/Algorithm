import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int arr1[]={0,1,-1,0};
    static int arr2[]={1,0,0,-1};
    static int n,m;
    static int map[][];
    static int map2[][];
    static boolean vit[][];
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        map2 = new int[n][m];
        vit = new boolean[n][m];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                map2[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if(Arrays.deepEquals(map,map2)){
            System.out.println("YES");
            return;
        }

        boolean found = false;
        boolean cntchk = false;
        int count = 0;
        for(int i=0; i<n; i++){
            if(found == true) break;
            if(cntchk == true) break;
            for(int j=0; j<m; j++){
                if(map[i][j] == map2[i][j]) continue;
                if(vit[i][j] == true) continue;

                if(count>0){
                    cntchk = true;
                    break;
                }

                int a = map2[i][j];
                int b = map[i][j];
                dfs(i,j,a,b);
                count++;

                if(Arrays.deepEquals(map,map2)){
                    System.out.println("YES");
                    found = true;
                    break;
                }
            }
        }

        if(!found) System.out.println("NO");
    }

    private static void dfs(int x, int y, int a, int b) {
        vit[x][y] = true;
        map[x][y] = a;

        for(int i=0; i<4; i++){
            int nx = x + arr1[i];
            int ny = y + arr2[i];

            if(nx<0 || ny<0 || nx>=n || ny>=m) continue;
            if(vit[nx][ny] == true) continue;

            if(map[nx][ny] != b) continue;

            dfs(nx, ny, a, b);
        }
    }
}
