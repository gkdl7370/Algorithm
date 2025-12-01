
import java.io.*;
class Main{
    static int N, M;
    static int[][] block = new int[10][10];
    static int[][] c_block = new int[10][10];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] str = bf.readLine().split(" ");

        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);

        for(int i = 0; i < N; i++){
            str = bf.readLine().split(" ");
            for(int j = 0; j < M; j++){
                block[i][j] = Integer.parseInt(str[j]);
            }
        }

        System.out.println(b_force(3));

    }



    public static int b_force(int r_wall){
        int local_max = 0;
        int ret;
        if(r_wall <=0){
            return safe_space();
        }
        for(int n1 = 0; n1 < N; n1++){
            for(int m1 = 0; m1 < M; m1++){
                if(block[n1][m1] != 0){
                    continue;
                }

                block[n1][m1] = 1;
                ret = b_force(r_wall - 1);
                if(ret > local_max){
                    local_max = ret;
                }
                block[n1][m1] = 0;
            }
        }

        return local_max;
    }

    public static int safe_space(){
        int count = 0;
        for(int n = 0; n < N; n++){
            for(int m = 0; m < M; m++){
                c_block[n][m] = block[n][m];
            }
        }
        for(int n = 0; n < N; n++){
            for(int m = 0; m < M; m++){
                if(c_block[n][m] == 2){
                    virus_test(n,m);
                }
            }
        }
        for(int n = 0; n < N; n++){
            for(int m = 0; m < M; m++){
                if(c_block[n][m] == 0){
                    count++;
                }
            }
        }
        return count;
    }

    public static void virus_test(int x, int y){
        int[] dx = {1,-1,0,0};
        int[] dy = {0,0,1,-1};
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx >= 0 && nx < N && ny >= 0 && ny < M && c_block[nx][ny] == 0){
                c_block[nx][ny] = 2;
                virus_test(nx,ny);
            }
        }
    }
}