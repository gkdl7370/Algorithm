import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int t;
    static int arr[];
    static boolean vit[];
    static boolean check;
    static ArrayList<Integer> list[];
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());

        for(int i=0; i<t; i++){
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            list = new ArrayList[n+1];
            arr = new int[n+1];
            vit = new boolean[n+1];

            for(int j=1; j<=n; j++){
                list[j] = new ArrayList<>();
            }

            for(int j=0; j<m; j++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                list[a].add(b);
                list[b].add(a);
            }


            check = false;
            for(int j=1; j<=n; j++){
                if(vit[j] == true) continue;
                arr[j] = 1;
                dfs(j);
            }


            if(check == false) System.out.println("possible");
            else System.out.println("impossible");
        }
    }

    private static void dfs(int start) {
        if(check == true) return; //안되면 리턴

        vit[start] = true; //방문체크
        int color; //색깔
        for(int i=0; i<list[start].size(); i++){
            if(arr[start] == 1) color = 2; //반대색으로 저장
            else color = 1;

            int next = list[start].get(i); //다음 노드

            if(arr[next] == arr[start]){ //다음 노드랑 색이 같으면 실패
                check = true; //실패체크
                return;
            }
            if(vit[next] == true) continue; //방문체크
            arr[next] = color; //색 칠하기
            dfs(next);
        }
    }

}
