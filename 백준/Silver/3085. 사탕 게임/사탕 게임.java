
import java.util.*;
import java.io.*;
public class Main {
    static int n;
    static char map[][];
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new char[n][n];

        for(int i=0; i<n; i++){
            String str = br.readLine();
            for(int j=0; j<n; j++){
                map[i][j] = str.charAt(j);
            }
        }

        int result = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                //오른쪽 아래쪽 바꿔서 탐색 돌리기
                if(j+1<n){ //오른쪽
                    if(map[i][j] != map[i][j+1]){
                        char temp = map[i][j];
                        map[i][j] = map[i][j+1];
                        map[i][j+1] = temp;

                        result = Math.max(search(), result);

                        temp = map[i][j]; //바꾼거 원상복귀
                        map[i][j] = map[i][j+1];
                        map[i][j+1] = temp;
                    }
                }

                if(i+1<n){ //아래
                    if(map[i][j] != map[i+1][j]){
                        char temp = map[i][j];
                        map[i][j] = map[i+1][j];
                        map[i+1][j] = temp;

                        result = Math.max(search(), result);

                        temp = map[i][j];
                        map[i][j] = map[i+1][j];
                        map[i+1][j] = temp;
                    }
                }
            }
        }

        System.out.print(result);
    }

    static int search(){
        int max = 1; //자기자신 포함이라

        for(int i=0; i<n; i++){
            int count = 1;
            for(int j=1; j<n; j++){
                if(map[i][j-1] == map[i][j]){
                    count++;
                } else{
                    count = 1; // 다르면 다시 1로 초기화
                }
                max = Math.max(max,count);
            }
        }

        for(int j=0; j<n; j++){
            int count = 1;
            for(int i=1; i<n; i++){
                if(map[i-1][j] == map[i][j]){
                    count++;
                } else{
                    count = 1; // 다르면 다시 1로 초기화
                }
                max = Math.max(max,count);
            }
        }

        return max;
    }
}
