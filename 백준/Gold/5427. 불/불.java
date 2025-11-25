import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int dx[] = {1,0,-1,0};
    static int dy[] = {0,1,0,-1};
    static int n;
    static int x,y;
    static char arr[][];
    static boolean vit[][];
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        for (int num = 0; num < n; num++) {
            st = new StringTokenizer(br.readLine());
            y = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            int count = 0;

            Queue<int[]> person = new LinkedList<>();
            Queue<int[]> fire = new LinkedList<>();

            arr = new char[x][y];
            vit = new boolean[x][y];
            for (int i = 0; i < x; i++) { // 맵 만들기
                String str = br.readLine();
                for (int j = 0; j < y; j++) {
                    arr[i][j] = str.charAt(j);
                    if (str.charAt(j) == '@') { // 현재 위치 확인
                        person.add(new int[]{i, j});
                        vit[i][j] = true;
                    } else if (str.charAt(j) == '*') { // 불위치
                        fire.add(new int[]{i, j});
                    }
                }
            }

            count = bfs(person, fire);

            if(count == -1)System.out.println("IMPOSSIBLE");
            else System.out.println(count);
        }
    }

    private static int bfs(Queue<int[]> person, Queue<int[]> fire) {
        int time = 0;

        while (!person.isEmpty()){
            time ++;

            //불지르기
            int fsize = fire.size();
            for(int k=0; k<fsize; k++){
                int next[] = fire.poll();
                int nx = next[0];
                int ny = next[1];

                for(int i=0; i<4; i++){
                    int nfx = nx + dx[i];
                    int nfy = ny + dy[i];
                    if(nfx<0 || nfy<0 || nfx>=x || nfy>=y) continue;
                    if(arr[nfx][nfy] == '#' || arr[nfx][nfy] == '*') continue;

                    arr[nfx][nfy] = '*';
                    fire.add(new int[]{nfx,nfy});
                }

            }

            //사람 움직이기
            int psize = person.size();
            for(int k=0; k<psize; k++){
                int next[] = person.poll();
                int nx = next[0];
                int ny = next[1];



                for(int i=0; i<4; i++){
                    int npx = nx + dx[i];
                    int npy = ny + dy[i];
                    if(npx<0 || npy<0 || npx>=x || npy>=y) {
                        return time;
                    }
                    if(arr[npx][npy] == '#' || arr[npx][npy] == '*') continue;
                    if(vit[npx][npy] == true) continue;
                    vit[npx][npy] = true;
                    person.add(new int[]{npx,npy});
                }
            }

        }


        return -1;
    }

}
