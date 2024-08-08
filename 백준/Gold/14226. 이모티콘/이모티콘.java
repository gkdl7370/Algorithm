import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    static int dist[];
    static boolean visited[];
    static int count;
    static class Point implements Comparable<Point>{
        int end;
        int val;
        int draw;
        int count;
        public Point(int end, int val, int draw, int count){
            this.end = end;
            this.val = val;
            this.draw = draw;
            this.count = count;
        }

        @Override
        public int compareTo(Point o) {
            return this.val - o.val;
        }
    }
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        dist = new int[n+2];
        visited = new boolean[n+2];

        Arrays.fill(dist,Integer.MAX_VALUE);
        count = 0;
        bfs(n);

        if(n == 0){
            System.out.print(0);
        }
        System.out.print(dist[n]);
    }

    private static void bfs(int a) {
        PriorityQueue<Point> p = new PriorityQueue<>();
        p.add(new Point(1,0,0,0));

        while (!p.isEmpty()){
            Point poll = p.poll();
            int now = poll.end; //현재 노드
            int val = poll.val; // 값
            int num = poll.draw; // 123중에 뭐했는지
            int count = poll.count; // 클립보드

            if(now<=0 || now>=dist.length) continue; //범위 체크 해주고

            if(visited[now] && count == 0) continue; //방문 체크 해주고


            if(num != 1){ //복사가 아니면
                visited[now] = true;
                dist[now] = val;
            }

            if(now == a){
                break;
            }

            for(int i=0; i<3; i++){
                if(i==0){//복붙
                    if(num != 1){ //복붙하고 또 다음에 복붙하는건 의미가 없음
                        int c = copy(now,count);
                        p.add(new Point(now,val+1,1, c));
                    }
                }
                if(i==1) {//붙여넣기
                    if(now+count<visited.length){
                        if(visited[now+count] != true){
                            p.add(new Point(now+count,val+1, 2, count));
                        }
                    }

                }
                if(i==2) {//빼기
                    if(visited[now-1] != true)
                    p.add(new Point(now-1,val+1, 3, count));
                }
            }



        }
    }

    private static int copy(int a, int count) {
        count = a;
        return count;
    }


}
