import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int k;
    static String arr[];
    static boolean check[];
    static int max = Integer.MIN_VALUE;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        check = new boolean[26];
        arr = new String[n];

        for (int i=0; i<n; i++){
            arr[i] = br.readLine().replaceAll("anta|tica", "");
        }

        check['a' - 'a'] = true;
        check['n' - 'a'] = true;
        check['t' - 'a'] = true;
        check['i' - 'a'] = true;
        check['c' - 'a'] = true;


        if(k == 26){
            System.out.println(n);
        } else if (k<5){
            System.out.println(0);
        } else{
            dfs(0,0);
            System.out.println(max);
        }


    }

    public static void dfs(int alpha, int len) {

        if(len == k-5){ // 최대 배울수있는 단어를 다배웠으면
            int count=0;
            //단어를 읽을수있는지 확인
            for(int i=0; i<n; i++){
                boolean complete = true; // 단어를 끝까지 읽었는지 확인용
                for(int j=0; j<arr[i].length(); j++){
                    if(!check[arr[i].charAt(j) - 'a']){ //단어를 비교해봤는데 못읽어
                        complete = false; //못읽었으니 못읽었다고 체크
                        break; // 다음 단어를 읽어야하니 두번째 for문 탈출
                    }
                }

                if(complete == true){ //못읽은게 없으면
                    count++;
                }
            }
            max = Math.max(max, count);
            return;
        }


        //알파벳 처음부터 배울 수 있는 갯수까지 배운다
        for(int i=alpha; i<check.length; i++){
            if(check[i] == false){
                check[i] = true; // 단어 배우고
                dfs(i, len+1); // 재귀돌리고
                check[i] = false;  // 배운단어 초기화
            }
        }

    }
}