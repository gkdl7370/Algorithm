import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static class Node{
        int end;
        int len;

        public Node(int end, int len){
            this.end = end;
            this.len = len;
        }
    }
    static ArrayList<Node> list[];
    static int max = Integer.MIN_VALUE;
    static boolean visited[];
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        list = new ArrayList[n+1];

        for(int i=1; i<=n; i++){
            list[i] = new ArrayList<>();
        }


        for(int i=0; i<n-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list[a].add(new Node(b,c));
            list[b].add(new Node(a,c));
        }

        for(int i=1; i<=n; i++){
            visited = new boolean[n+1];
            dfs(i,0);
        }

        System.out.println(max);

    }

    private static void dfs(int a, int b) {

        visited[a] = true;

        if(max<b){
            max = b;
        }

        for(int i=0; i<list[a].size(); i++){
            if(visited[list[a].get(i).end] == true) continue;
            dfs(list[a].get(i).end,list[a].get(i).len+b);
        }
    }
}
