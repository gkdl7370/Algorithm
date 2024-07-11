
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Point implements Comparable<Point>{
    int city;
    int len;

    Point(int city, int len){
        this.city = city;
        this.len = len;
    }

    // 우선순위 큐에서 가중치(w)가 작은 순으로 정렬하기 위한 compareTo 메서드
    @Override
    public int compareTo(Point o) {
        return this.len - o.len;
    }
}

public class Main {
    static int n;
    static int m;
    static ArrayList<Point> list[]; //노드 연결
    static int dist[]; //거리
    static boolean visited[]; //방문체크
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        list = new ArrayList[n+1];
        visited = new boolean[n+1];
        dist = new int[n+1];

        for(int i=1; i<=n; i++){
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int len = Integer.parseInt(st.nextToken());
            list[start].add(new Point(end, len));
        }

        st = new StringTokenizer(br.readLine());
        int startCity = Integer.parseInt(st.nextToken());
        int endCity = Integer.parseInt(st.nextToken());

        moving(startCity);

        System.out.println(dist[endCity]);
    }

    private static void moving(int startCity) {
        PriorityQueue<Point> p = new PriorityQueue<>(); //우선순위 사용
        p.add(new Point(startCity,0)); //시작노드와 거리를 넣어준다 시작점이기 때문에 거리는 0
        Arrays.fill(dist,Integer.MAX_VALUE); //거리를 모두 맥스값으로 채워둔다
        dist[startCity] = 0; //시작점의 거리는 0으로 저장해둔다

        while (!p.isEmpty()){
            Point poll = p.poll(); // 큐에 저장된 최소 거리를 가진 도시를 가져온다
            int city = poll.city;
            
            if (visited[city]) {
                continue; // 이미 방문한 노드면 건너뜀
            }
            
            visited[poll.city] = true; // 가져왔으니 방문체크를 해준다

            for(int i=0; i<list[poll.city].size(); i++){ //꺼낸 도시와 연결되는 도시들의 수만큼
                Point a = list[poll.city].get(i); // 연결가능한 도시 꺼낸다

                if(visited[a.city] == true){ //방문한 도시이면
                    continue; // 다시 들어가지 않고 넘어간다
                }

                if(dist[a.city] > dist[poll.city] + a.len){ //인접 노드의 거리가 현재노드에서 출발했을때의 거리보다 크면
                    dist[a.city] = dist[poll.city] + a.len; // 현재노드에서 출발했을때의 거리로 교체
                    p.add(new Point(a.city, dist[a.city])); // 인접거리의 노드와 교체된 거리값으로 저장

                }
            }
        }
    }
}
