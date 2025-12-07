

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
            if(list.contains(a) != true) list.add(a);
        }

        result = new int[m];

        Collections.sort(list);

        dfs(0);
        
        System.out.println(sb);
    }

    static void dfs(int depth) {
        if(depth == m){
            for(int i=0; i<m; i++){
                sb.append(result[i]).append(" ");
            }
            sb.append("\n");
            return;
        }


        for(int i=0; i<list.size(); i++){
            result[depth] = list.get(i);
            dfs(depth+1);
        }
    }
}
