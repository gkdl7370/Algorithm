
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static ArrayList<Tree> list[];
    static boolean visited[];
    static int count;
    static int max;
    static class Tree implements Comparable<Tree>{

        int now;
        int val;


        Tree(int now, int val){
            this.now = now;
            this.val = val;

        }

        @Override
        public int compareTo(Tree o) {
            return this.val - o.val;
        }
    }
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList[n+1];
        visited = new boolean[n+1];

        for(int i=0; i<=n; i++){
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            list[start].add(new Tree(end,val));
            list[end].add(new Tree(start,val));
        }

        count = 0;
        moving(1,0);

        System.out.println(count-max);
    }

    private static void moving(int now, int val) {
        PriorityQueue<Tree> nowPoint = new PriorityQueue<>();
        nowPoint.add(new Tree(now,val));

        max = val;

        while (!nowPoint.isEmpty()){
            Tree nowNode = nowPoint.poll();

            if(visited[nowNode.now] == true){
                continue;
            }


            visited[nowNode.now] = true;
            max = Math.max(max, nowNode.val);
            count += nowNode.val;

            for(int i=0; i<list[nowNode.now].size(); i++){ //인접 노드를 찾고
                Tree nextNode = list[nowNode.now].get(i);

                if(visited[nextNode.now]){
                    continue;
                }


                nowPoint.add(new Tree(nextNode.now, nextNode.val));

            }

        }


    }
}