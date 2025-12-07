import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static ArrayList<Integer> map;
    static int[] result;
    static StringBuilder sb = new StringBuilder();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new ArrayList<>();
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<n; i++){
            int a = Integer.parseInt(st.nextToken());
            if(!map.contains(a)) map.add(a);
        }

        Collections.sort(map);

        result = new int[m];
        dfs(0, 0);

        System.out.println(sb);
    }

    // depth: 현재 몇개 선택했는지
    // start: map에서 어디부터 선택 가능한지 (비내림차순 유지)
    private static void dfs(int depth, int start) {
        if(depth == m){
            for(int i=0; i<m; i++){
                sb.append(result[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=start; i<map.size(); i++){
            result[depth] = map.get(i);
            dfs(depth+1,i);
        }

    }
}


