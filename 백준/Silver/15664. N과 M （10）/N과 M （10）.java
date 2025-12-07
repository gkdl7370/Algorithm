
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static ArrayList<Integer> list;
    static int result[];
    static StringBuilder sb = new StringBuilder();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            int a = Integer.parseInt(st.nextToken());
            if(m == 1){
                if(list.contains(a) != true) list.add(a);
            } else{
                list.add(a);
            }

        }

        result = new int[m];

        Collections.sort(list);

        dfs(0,0);

        System.out.println(sb);
    }

    static void dfs(int depth,int start) {

        if(depth == m){
            for (int i=0; i<m; i++){
                sb.append(result[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        int beforeNum = -1;
        for(int i=start; i<list.size(); i++){
            int nowNum = list.get(i);
            if(nowNum == beforeNum) continue;
            result[depth] = nowNum;
            beforeNum = nowNum;
            dfs(depth+1, i+1);
        }

    }
}
