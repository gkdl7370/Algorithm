import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static boolean[] visited;
    static ArrayList<Integer>[] graph;
    static ArrayList<Integer> route;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        visited = new boolean[n + 1];
        graph = new ArrayList[n + 1];
        route = new ArrayList<>();

        for(int i=1; i<=n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++){
                int a = Integer.parseInt(st.nextToken());
                if(a == 1) graph[i].add(j);
            }
        }

        st = new StringTokenizer(br.readLine());

        for(int i=0; i<m; i++){
            route.add(Integer.parseInt(st.nextToken()));
        }

        bfs(route.get(0));

        boolean check = true;

        for(int i=0; i<route.size(); i++){
            if(visited[route.get(i)] == false) check = false;
        }

        System.out.println(check? "YES":"NO");
    }

    private static void bfs(Integer a) {
        Queue<Integer> p = new LinkedList<>();
        p.add(a);

        while (!p.isEmpty()){
            Integer x = p.poll();

            if(visited[x] == true) continue;
            visited[x] = true;

            for(int i=0; i<graph[x].size(); i++){
                if(visited[graph[x].get(i)] == true) continue;
                p.add(graph[x].get(i));
            }
        }
    }

}
