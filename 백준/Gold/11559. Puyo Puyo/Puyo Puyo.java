import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int nx[] = {0,0,-1,1};//동서남북
    static int ny[] = {1,-1,0,0};
    static char map[][];
    static boolean vit[][];
    static boolean check;
    static boolean chk;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new char[12][6];


        for(int i=0; i<12; i++){
            String s = br.readLine();
            for(int j=0; j<6; j++){
                map[i][j] = s.charAt(j);
            }
        }

        int val=0;

        while (true){
            chk = false;
            vit = new boolean[12][6];
            for(int i=0; i<12; i++){
                for(int j=0; j<6; j++){
                    if(map[i][j] == '.') continue;
                    if(vit[i][j] != true) boom(i,j);
                }
            }

            if(chk == false) break;
            for(int i=0; i<6; i++){
                change(i);
            }
            val++;

        }

        System.out.println(val);
    }

    private static void boom(int a, int b) {
        Queue<int[]> q = new LinkedList<>();
        Queue<int[]> q2 = new LinkedList<>();
        q.add(new int[]{a,b});

        while (!q.isEmpty()){
            int[] poll = q.poll();
            int x = poll[0];
            int y = poll[1];
            char color = map[x][y];

            q2.add(new int[]{x,y});
            vit[x][y] = true;

            for(int i=0; i<4; i++){
                int dx = x + nx[i];
                int dy = y + ny[i];

                if(dx<0 || dy<0 || dx>=12 || dy>=6) continue;
                if(vit[dx][dy] == true) continue;
                if(map[dx][dy] != color) continue;

                q.add(new int[]{dx,dy});
            }
        }

        if(q2.size() >= 4){
            while (!q2.isEmpty()){
                int[] poll = q2.poll();
                int x = poll[0];
                int y = poll[1];
                map[x][y] = '.';
            }
            chk = true;
        }

    }

    private static void change(int y) {
        Queue<Character> q = new LinkedList<>();

        for(int i=11; i>=0; i--){
            if(map[i][y] != '.'){
                q.add(map[i][y]);
            }
        }

        for(int i=11; i>=0; i--){
            if(!q.isEmpty()){
                map[i][y] = q.poll();
            } else{
                map[i][y] = '.';
            }
        }

    }


}

