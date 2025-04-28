import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static boolean chk;
    static ArrayList<Integer> list[];
    static char arr[];
    static int coin[];
    static boolean vit[];
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            if(n == 0) break;
            arr = new char[n+1];
            coin = new int[n+1];
            vit = new boolean[n+1];
            list = new ArrayList[n+1];
            chk = false;

            for(int i=1; i<=n; i++){
                list[i] = new ArrayList<>();
            }

            for(int i=1; i<=n; i++){
                st = new StringTokenizer(br.readLine());
                char c = st.nextToken().charAt(0);
                int cost = Integer.parseInt(st.nextToken());
                arr[i] = c;
                coin[i] = cost;

                while (true){
                    int a = Integer.parseInt(st.nextToken());
                    if(a == 0) break;
                    list[i].add(a);
                }

            }

            dfs(1,n,0);

            if(chk == true) System.out.println("Yes");
            else System.out.println("No");
        }



    }

    private static void dfs(int start, int end, int cost) {
        if(chk == true) return;

        if(start == end){
            chk = true;
            return;
        }

        vit[start] = true;

        for(int i=1; i<=list[start].size(); i++){
            int next = list[start].get(i-1);
            if(vit[next] == true) continue;

            if(arr[next] == 'E'){ //빈공간
                dfs(next,end,cost);
            } else if(arr[next] == 'L'){ //돈
                int newCost = cost;
                if(coin[next] > newCost) newCost = coin[next];
                dfs(next, end, newCost);
            } else if(arr[next] == 'T'){ //보물
                if(cost >= coin[next]){
                    dfs(next,end,cost - coin[next]);
                }
            }
        }
        vit[start] = false;
    }
}
