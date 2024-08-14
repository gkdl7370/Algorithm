import java.util.PriorityQueue;
class Solution {
    boolean visited[];
    int dist[];
    class Node implements Comparable<Node>{
        int index;
        int val;
        String str;

        public Node(int index, int val, String str){
            this.index = index;
            this.val = val;
            this.str = str;
        }

        @Override
        public int compareTo(Node o) {
            return this.val - o.val;
        }
    }
    public int solution(String begin, String target, String[] words) {
      int answer = 0;

        visited = new boolean[words.length];
        dist = new int[words.length];

        int a = -1;
        for(int i=0; i<words.length; i++){
            if(words[i].equals(target)){
                a = i;
            }
        }
        
        if(a==-1) return 0;
        
        bfs(0,0, words, begin);
        

        
        return dist[a];
    }
    
    public void bfs(int x, int val, String[] words, String begin) {
        PriorityQueue<Node> p = new PriorityQueue<>();
        p.add(new Node(x, val, begin));

        while (!p.isEmpty()){
            Node poll = p.poll();

            int nx = poll.index;
            int nv = poll.val;
            String str = poll.str;



            String now = str;
            char arr[] = new char[now.length()];
            for(int i=0; i<now.length(); i++){
                arr[i] = now.charAt(i);
            }

            for(int i=0; i<words.length; i++){
                if(visited[i] == true) continue;

                String next = words[i];
                int count = 0;
                for(int j=0; j<next.length(); j++){
                    if(arr[j] == next.charAt(j)) count ++;
                }

                if(count == next.length()-1){
                    visited[i] = true;
                    dist[i] = nv+1;
                    p.add(new Node(i,nv+1, next));
                }
            }
        }
    }
}