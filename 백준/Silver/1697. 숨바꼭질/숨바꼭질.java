
import java.util.*;
import java.io.*;

public class Main {
    static int n,k,time;
    static int map[] = new int[100001];
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        time = 0;

        Queue<Integer> q = new LinkedList<>();

        q.add(n);
        while(!q.isEmpty()){
            int x = q.poll();

            if(x == k) break;

            for(int i=0; i<3; i++){
                if(i==0){
                  int next = x - 1;
                  if(next<0 || next>100000) continue;
                  if(map[next] != 0) continue;
                  map[next] = map[x] + 1;
                  q.add(next);
                } else if(i==1){
                    int next = x + 1;
                    if(next<0 || next>100000) continue;
                    if(map[next] != 0) continue;
                    map[next] = map[x] + 1;
                    q.add(next);
                } else if(i==2){
                    int next = x * 2;
                    if(next<0 || next>100000) continue;
                    if(map[next] != 0) continue;
                    map[next] = map[x] + 1;
                    q.add(next);
                }
            }


        }

        System.out.print(map[k]);

    }
}
