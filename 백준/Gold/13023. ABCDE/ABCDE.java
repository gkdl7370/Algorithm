import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static ArrayList<Integer> list[];
    static boolean val = false;
    static boolean visited[];
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList[n];

        for(int i=0; i<n; i++){
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        for(int i=0; i<n; i++){
            visited = new boolean[n];
            dfs(i,0);
            if(val == true) break;
        }

        if(val == true) System.out.println(1);
        else System.out.println(0);
    }

    private static void dfs(int a, int count) {
        if(val == true) return;
        if(count == 5){
            val = true;
            return;
        }

        for(int i=0; i<list[a].size(); i++){
            if(visited[list[a].get(i)] == false){
                visited[list[a].get(i)] = true;
                dfs(list[a].get(i),count+1);
                visited[list[a].get(i)] = false;
            }
        }
    }
}
