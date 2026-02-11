
import java.util.*;
import java.io.*;
public class Main {
    static int n, result;
    static int map[][];
    static int team;
    static boolean teamChk[];
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n+1][n+1];
        team = n/2;
        teamChk = new boolean[n+1];
        result = Integer.MAX_VALUE;

        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        selectTeam(1, 0);

        System.out.print(result);
    }

    public static void selectTeam(int now, int count){
        if(count == team){
            result = Math.min(result, dfs());
            return;
        }

        for(int i=now; i<=n; i++){
            if(teamChk[i] == false){
                teamChk[i] = true;
                selectTeam(i+1, count+1);
                teamChk[i] = false;
            }
        }
    }

    public static int dfs(){
        int start = 0;
        int link = 0;

        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                if(teamChk[i] == true && teamChk[j] == true){
                    start += map[i][j];
                } else if(teamChk[i] == false && teamChk[j] == false){
                    link += map[i][j];
                }
            }
        }

        return Math.abs(start - link); // 절댓값 함수 사용으로 가독성 증대
    }
}
