import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int arr1[]={0,1,-1,0};
    static int arr2[]={1,0,0,-1};
    static int m;
    static int n;
    static int h;
    static ArrayList<int[][]> list;
    static class Node{
        int x;
        int y;
        int height;
        public Node(int x, int y, int height){
            this.x = x;
            this.y = y;
            this.height = height;
        }
    }
    static Queue<Node> q = new LinkedList<>();
    static Queue<Node> q2 = new LinkedList<>();

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        list = new ArrayList<>(h);

        for(int i = 0; i<h; i++){
            int[][] array = new int[n][m];
            list.add(array);
        }

        boolean check = false;
        for(int l=0; l<h; l++){
            for(int i=0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<m; j++){
                    list.get(l)[i][j] = Integer.parseInt(st.nextToken());
                    if(list.get(l)[i][j] == 0 && check == false) check = true;
                    if(list.get(l)[i][j] == 1) q.add(new Node(i,j,l));
                }
            }
        }

        if(check == false){
            System.out.println(0);
            return;
        }

        int day=0;

        while (!q.isEmpty()){
            bfs();
            day = day + 1;
        }

        check = false;

        for(int l=0; l<h; l++){
            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    if(list.get(l)[i][j] == 0){
                        check = true;
                        break;
                    }
                }
                if(check == true) break;
            }
            if(check == true) break;
        }

        if(check == true){
            System.out.println(-1);
        } else{
            System.out.println(day-1);
        }

    }

    private static void bfs() {

        while (!q.isEmpty()){
            Node poll = q.poll();

            int nowX = poll.x;
            int nowY = poll.y;
            int height = poll.height;

            if(h == 2){
                if(height == 0){
                    if(list.get(height+1)[nowX][nowY] == 0) {
                        list.get(height+1)[nowX][nowY] = 1;
                        q2.add(new Node(nowX,nowY,height+1));
                    }
                } else if(height == 1){
                    if(list.get(height-1)[nowX][nowY] == 0) {
                        list.get(height-1)[nowX][nowY] = 1;
                        q2.add(new Node(nowX,nowY,height-1));
                    }
                }

            } else if(h > 2){
                if(height-1 >=0 && list.get(height-1)[nowX][nowY] == 0){
                    list.get(height-1)[nowX][nowY] = 1;
                    q2.add(new Node(nowX,nowY,height-1));
                }
                if(height+1<h && list.get(height+1)[nowX][nowY] == 0){
                    list.get(height+1)[nowX][nowY] = 1;
                    q2.add(new Node(nowX,nowY,height+1));
                }
            }

            for(int i=0; i<4; i++){
                int nextX = nowX + arr1[i];
                int nextY = nowY + arr2[i];
                if(nextX < 0 || nextY < 0 || nextX >= n || nextY >= m) continue;
                if(list.get(height)[nextX][nextY] == 0){
                    list.get(height)[nextX][nextY] = 1;
                    q2.add(new Node(nextX,nextY,height));
                } else if(list.get(height)[nextX][nextY] == -1){
                    continue;
                }
            }
        }

        q.addAll(q2);
        q2.clear();
    }


}