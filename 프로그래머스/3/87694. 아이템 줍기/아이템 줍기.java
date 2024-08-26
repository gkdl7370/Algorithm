import java.util.LinkedList;
import java.util.Queue;

class Solution {
     static int arr1[]={0,1,-1,0};
    static int arr2[]={1,0,0,-1};
    static boolean visited[][];
    static int map[][];
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
     int answer = 0;

        map = new int[101][101];
        visited = new boolean[101][101];
        for(int i=0; i<rectangle.length; i++){

            int x1 = rectangle[i][0]*2; //1
            int y1 = rectangle[i][1]*2; //1
            int x2 = rectangle[i][2]*2; //7
            int y2 = rectangle[i][3]*2; //4

            for(int j=x1; j<=x2; j++){
                for(int k=y1; k<=y2; k++){
                    if(map[j][k]==1) continue;
                    map[j][k] = 1;
                    if(j==x1 || j==x2 || k==y1 || k==y2) map[j][k] = 2;
                }
            }

        }

        answer = bfs(characterX*2,characterY*2,itemX*2,itemY*2);
        

        return answer;
    }

    public int bfs(int characterX, int characterY, int itemX, int itemY) {
        Queue<Integer[]> q = new LinkedList<>();
        q.add(new Integer[]{characterX,characterY,0});
        visited[characterX][characterY] = true;

        while (!q.isEmpty()){
            Integer[] poll = q.poll();
            int x = poll[0];
            int y = poll[1];
            int count = poll[2];

            if(x == itemX && y == itemY){
                return count/2;
            }

            for (int i=0; i<4; i++){
                int nx = x+arr1[i];
                int ny = y+arr2[i];

                if(nx<0 || ny<0 || nx>map.length-1 || ny>map.length-1) continue;

                if(visited[nx][ny] == true || map[nx][ny] != 2) continue;

                visited[nx][ny] = true;
                q.add(new Integer[]{nx,ny,count+1});
            }
        }

        return 0;
    }
}