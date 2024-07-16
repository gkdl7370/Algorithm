
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int v;
    static int e;
    static ArrayList<Tree> list[];
    static int dist[];
    static boolean visited[];
    static int count;
    static PriorityQueue<Tree> p;
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

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        list = new ArrayList[v+1];
        dist = new int[v+1];
        visited = new boolean[v+1];

        for(int i=0; i<v+1; i++){
            list[i] = new ArrayList<>();
        }


        p = new PriorityQueue<>();

        for(int i=0; i<e; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            list[start].add(new Tree(end,dist));
            list[end].add(new Tree(start,dist)); //양방향이기 때문에 양쪽 모두 저장
        }

        count = 0;

        moving(1,0); //1을 시작점으로 시작

        System.out.println(count);
    }

    private static void moving(int now, int val) {
        PriorityQueue<Tree> nowPoint = new PriorityQueue<>();
        nowPoint.add(new Tree(now, val));

        while (!nowPoint.isEmpty()){
            Tree nowNode = nowPoint.poll();

            if(visited[nowNode.now] == true){
                continue;
            }

            visited[nowNode.now] = true;

            count += nowNode.val;

            for(int i=0; i<list[nowNode.now].size(); i++){
                Tree nextNode = list[nowNode.now].get(i); //인접노드

                if(visited[nextNode.now] == true){
                    continue;
                }


                nowPoint.add(new Tree(nextNode.now,nextNode.val));

            }


        }



    }
}