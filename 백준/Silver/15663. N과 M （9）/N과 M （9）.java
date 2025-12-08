
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int result[];
    static ArrayList<Integer> list;
    static StringBuilder sb = new StringBuilder();
    static boolean vit[];
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        result = new int[m];

        list = new ArrayList<>();
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<n; i++){
            int a = Integer.parseInt(st.nextToken());
            if(m == 1){
                if(list.contains(a)) continue; // m이 1이면 하나만 출력하니까 중복값 제거
                list.add(a);
            } else{
                list.add(a);
            }
        }
        vit = new boolean[list.size()];
        Collections.sort(list);

        dfs(0);

        System.out.println(sb);
    }

    private static void dfs(int depth) {
        if(depth == m){
            for(int i=0; i<m; i++){
                sb.append(result[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        int before = -1;
        for(int i=0; i<list.size(); i++){
            if(vit[i] == true) continue;
            int now = list.get(i);
            if(now == before) continue;

            result[depth] = now;
            before = now;
            vit[i] = true;
            dfs(depth+1);
            vit[i] = false;
        }
    }
}
