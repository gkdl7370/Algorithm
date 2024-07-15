
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Road implements Comparable<Road>{
    int now;
    int val;

    Road(int now, int val){
        this.now = now;
        this.val = val;
    }

    @Override
    public int compareTo(Road o) {
        return this.val - o.val;
    }
}

public class Main {
    static int n;
    static int m;
    static ArrayList<Road> list[];
    static int dist[];
    static boolean visited[];
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList[n+2];
        visited = new boolean[n+2];
        dist = new int[n+2];

        for(int i=0; i<n+1; i++){ //배열 선언해주고
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            list[start].add(new Road(end,dist));
            list[end].add(new Road(start,dist));
        }

        moving(1,0);

        System.out.println(dist[n]);
    }

    private static void moving(int start, int len) {
        PriorityQueue<Road> nowPoint = new PriorityQueue<>();
        nowPoint.add(new Road(start,len)); //현재 위치를 넣고
        Arrays.fill(dist, Integer.MAX_VALUE); //누적치 채워두고
        dist[start] = 0; //스타트 지점 누적치 0

        while (!nowPoint.isEmpty()){
            Road nowRoad = nowPoint.poll(); //현재 위치
            int city = nowRoad.now;

            if(visited[city] == true){
                continue;
            }

            visited[nowRoad.now] = true;

            for(int i=0; i<list[nowRoad.now].size(); i++){
                //인접 노드 꺼내
                Road a = list[nowRoad.now].get(i); //인접 노드 저장

                if(visited[a.now] == true){
                    continue;
                }

                if(dist[a.now] > dist[nowRoad.now] + a.val){
                    dist[a.now] = dist[nowRoad.now] + a.val;
                    nowPoint.add(new Road(a.now, dist[a.now]));
                }

            }
        }

    }
}
