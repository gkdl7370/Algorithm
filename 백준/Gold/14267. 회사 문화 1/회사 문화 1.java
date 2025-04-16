import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n,m;
    static int arr[];
    static ArrayList<Integer> list[];
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n+1];

        list = new ArrayList[n+1];

        for(int i=0; i<=n; i++){
            list[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());

        int root = 0;
        for (int i = 1; i <= n; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) {
                root = i;
                continue;
            }
            list[parent].add(i);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a] += b;
        }

        dfs(root);

        for(int i=1; i<=n; i++){
            System.out.print(arr[i]+" ");
        }

    }

    private static void dfs(int now) {
        for (int i = 0; i < list[now].size(); i++) {
            int next = list[now].get(i);
            arr[next] += arr[now];
            dfs(next);
        }
    }

}
