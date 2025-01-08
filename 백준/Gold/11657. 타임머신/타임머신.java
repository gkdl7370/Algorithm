import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Edge {
        int from, to, weight;
        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Edge> list = new ArrayList<>();

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list.add(new Edge(a,b,c));
        }

        long dist[] = new long[n+1];

        Arrays.fill(dist,Long.MAX_VALUE);

        dist[1] = 0;
        boolean check = false;

        for(int i=1; i<=n; i++){
            for(int j=0; j<list.size(); j++){
                Edge edge = list.get(j);

                if(dist[edge.from] != Long.MAX_VALUE){
                    if(dist[edge.to] > dist[edge.from] + edge.weight){
                        dist[edge.to] = dist[edge.from] + edge.weight;

                        if(i == n) check = true;
                    }
                }
            }
        }


        if(check == true){
            System.out.println("-1");
        } else{
            for(int i=2; i<=n; i++){
                if (dist[i] == Long.MAX_VALUE) {
                    System.out.println("-1"); // 도달 불가능
                } else {
                    System.out.println(dist[i]);
                }
            }
        }
    }
}
