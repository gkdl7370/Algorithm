import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    static int n;
    static int arr[];
    static ArrayList list;
    static boolean vit[];
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n+1];

        for(int i=1; i<=n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        list = new ArrayList();
        vit = new boolean[n+1];

        for(int i=1; i<=n; i++){
            vit[i] = true;
            dfs(i,i);
            vit[i] = false;
        }

        Collections.sort(list);
        System.out.println(list.size());
        for(int i=0; i<list.size(); i++){
            System.out.println(list.get(i));
        }

    }

    static void dfs(int start, int target) { // 시작과 다음의 인덱스를 파라미터로 넣어줌

        if(vit[arr[start]] != true){
            vit[arr[start]] = true;
            dfs(arr[start], target);
            vit[arr[start]] = false;
        }

        if(arr[start] == target){
            list.add(target);
        }

    }

}