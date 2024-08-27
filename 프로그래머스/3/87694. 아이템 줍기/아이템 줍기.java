import java.util.LinkedList;
import java.util.Queue;
class Solution {
    int arr1[]={0,1,-1,0};
    int arr2[]={1,0,0,-1};
    int map[][];
    boolean visited[][];
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;

        map = new int[101][101];
        visited = new boolean[101][101];

        for(int i=0; i<rectangle.length; i++){

            int x1 = rectangle[i][0]*2;
            int y1 = rectangle[i][1]*2;
            int x2 = rectangle[i][2]*2;
            int y2 = rectangle[i][3]*2;

            for(int k=x1; k<=x2; k++){ //제일 바깥라인 2로 만들기 위함
                for(int l=y1; l<=y2; l++){
                    if(map[k][l] == 1) continue; // 이미 1로 칠해진곳은 내부에 그려진 라인이므로 패스
                    map[k][l] = 1;

                    if(k==x1 || k==x2 || l==y1 || l==y2){
                        map[k][l] = 2;
                    }
                }
            }
        }


        answer = bfs(characterX*2,characterY*2,itemX*2,itemY*2);
        
        return answer;
    }

    public int bfs(int characterX, int characterY, int itemX, int itemY) {
        Queue<Integer[]> p = new LinkedList<>();
        p.add(new Integer[]{characterX,characterY,0});

        while (!p.isEmpty()){
            Integer[] poll = p.poll();
            int x = poll[0];
            int y = poll[1];
            int count = poll[2];

            if(x == itemX && y == itemY){
                return count/2;
            }

            if(visited[x][y] == true) continue;

            visited[x][y] = true;

            for(int i=0; i<4; i++){
                int nx = x + arr1[i];
                int ny = y + arr2[i];

                if(nx<0 || ny<0 || nx>=map.length || ny>=map.length) continue;

                if(visited[nx][ny] == true) continue;

                if(map[nx][ny] == 2) p.add(new Integer[]{nx,ny,count+1});
            }
        }
        
        return -1;
    }
}