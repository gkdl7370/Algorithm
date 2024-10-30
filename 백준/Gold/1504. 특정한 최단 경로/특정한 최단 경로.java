import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int a, b;
    static ArrayList<Node> list[];

    static class Node implements Comparable<Node> {
        int end;
        int len;

        public Node(int end, int len) {
            this.end = end;
            this.len = len;
        }

        @Override
        public int compareTo(Node o) {
            return this.len - o.len;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        list = new ArrayList[a + 1];
        for (int i = 1; i <= a; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < b; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            list[x].add(new Node(y, l));
            list[y].add(new Node(x, l));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int path1 = dijkstra(1, v1);
        int path2 = dijkstra(1, v2);
        int path3 = dijkstra(v1, v2);
        int path4 = dijkstra(v1, a);
        int path5 = dijkstra(v2, a);

        // 유효한 경로가 없을 경우 Integer.MAX_VALUE를 반환하도록 설정
        int val1 = (path1 == Integer.MAX_VALUE || path3 == Integer.MAX_VALUE || path5 == Integer.MAX_VALUE) ? Integer.MAX_VALUE : path1 + path3 + path5;
        int val2 = (path2 == Integer.MAX_VALUE || path3 == Integer.MAX_VALUE || path4 == Integer.MAX_VALUE) ? Integer.MAX_VALUE : path2 + path3 + path4;

        int result = Math.min(val1, val2);

        if (result >= Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }

    private static int dijkstra(int start, int end) {
        PriorityQueue<Node> pQueue = new PriorityQueue<>();
        pQueue.add(new Node(start, 0));
        int[] dist = new int[a + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        while (!pQueue.isEmpty()) {
            Node currentNode = pQueue.poll();
            int current = currentNode.end;
            int currentLen = currentNode.len;

            if (dist[current] < currentLen) continue;

            for (int i = 0; i < list[current].size(); i++) {
                Node nextNode = list[current].get(i);
                int next = nextNode.end;
                int newDist = currentLen + nextNode.len;

                if (newDist < dist[next]) {
                    dist[next] = newDist;
                    pQueue.add(new Node(next, newDist));
                }
            }
        }
        return dist[end];
    }
}