import java.util.ArrayList;
class Solution {
    static int dx[] = {1,0,-1}; //아래 옆 대각선
    static int dy[] = {0,1,-1};
    public ArrayList<Integer> solution(int n) {
        int x = 0;
        int y = 0;
        int count = 2;

        int map[][] = new int[n][n];
        map[0][0] = 1;


        while (true){
            int check = 0;
            for(int i=x; i<n; i++){
                x = x + dx[0];
                y = y + dy[0];

                if(x<0 || y<0 || x>=n || y>=n)continue;
                if(map[x][y] == 0){
                    map[x][y] = count;
                    count++;
                    check++;
                } else{
                    break;
                }
            }
            x--;
            for(int i=y; i<n; i++){
                x = x + dx[1];
                y = y + dy[1];

                if(x<0 || y<0 || x>=n || y>=n)continue;
                if(map[x][y] == 0){
                    map[x][y] = count;
                    count++;
                    check++;
                }else{
                    break;
                }
            }
            y--;
            for(int i=x; i>0; i--){
                x = x + dx[2];
                y = y + dy[2];

                if(x<0 || y<0 || x>=n || y>=n)continue;
                if(map[x][y] == 0){
                    map[x][y] = count;
                    count++;
                    check++;
                }else{
                    break;
                }
            }
            x += 1;
            y += 1;
            if(check==0) break;
        }

        ArrayList<Integer> list = new ArrayList<>();

        for(int i=0; i<n; i++){
            for(int j=0; j<=i; j++){
                list.add(map[i][j]);
            }
        }
        return list;

    }
    
}