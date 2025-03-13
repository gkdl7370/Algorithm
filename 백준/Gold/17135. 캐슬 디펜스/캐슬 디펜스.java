import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, D, enemies, result;
    static int[] archer;
    static int[][] map;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        archer = new int[3];//궁수 3명의 위치
        map = new int[N][M];
        enemies = 0;//적들의 수

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) enemies++;
            }
        }

        position(0, 0);
        System.out.println(result);
    }

    public static void position(int start, int cnt) { //궁수 위치 정하기
        if(cnt == 3){ //궁수 3명 채우면 공격
            result = Math.max(attack(), result);
            return;
        }

        for(int i=start; i<M; i++){
            archer[cnt] = i;
            position(i+1, cnt+1);
        }
    }

    public static int attack() {//bfs
        int monster = enemies;
        int dx[] = {0, -1, 0};
        int dy[] = {-1, 0, 1};
        int[][] copy = new int[N][M];
        for(int i=0; i<N; i++){
            copy[i] = Arrays.copyOf(map[i], M);
        }
        int where = N;

        while (monster>0 && where>0){
            for(int i=0; i<3; i++){ //궁수 숫자만큼
                Queue<int[]> q = new LinkedList<>();
                q.add(new int[] {where, archer[i]}); //현재 궁수의 위치
                boolean vit[][] = new boolean[N][M];
                int len = 0;

                loop2: while (!q.isEmpty()){
                    if(++len > D) break;

                    for(int k=0, size=q.size(); k<size; k++){
                        int[] node = q.poll();
                        for(int j=0; j<3; j++){
                            int nx = node[0] + dx[j];
                            int ny = node[1] + dy[j];

                            if(nx>=0 && ny>=0 && nx<where && ny<M){
                                if(vit[nx][ny] == true) continue;

                                if(copy[nx][ny] == 1){ //몬스터 찾음
                                    monster--;
                                    copy[nx][ny] = -where;
                                    break loop2;
                                } else if(copy[nx][ny] == -where){ //몬스터 이미 누군가가 죽임
                                    break loop2;
                                }
                                vit[nx][ny] = true;
                                q.add(new int[]{nx, ny});
                            }
                        }
                    }
                }
            }
            where--;
        }

        return enemies - monster;
    }

}
