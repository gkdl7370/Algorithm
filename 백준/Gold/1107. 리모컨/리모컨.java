import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int target;
    static boolean error[];
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        target = Integer.parseInt(br.readLine());
        error = new boolean[11];
        int m = Integer.parseInt(br.readLine());

        if(m != 0){
            st = new StringTokenizer(br.readLine());
            for (int i=0; i<m; i++){
                int a = Integer.parseInt(st.nextToken());
                error[a] = true;
            }
        }

        int val = 0;

        //위 아래로만 움직여서 도착하는 방법
        val = Math.abs(target - 100);

        //숫자조합 만들어서 + - 만들기
        for(int i=0; i<=999999; i++){ //0부터 6자리 최대값까지
            int len = search(i); //만들수 있는 숫자인지 확인
            if(len != -1){ //만들수 있으면
                int num = Math.abs(target-i);
                val = Math.min(val,num + len);
            }
        }

        System.out.print(val);

    }

    private static int search(int num) {
        if(num == 0){
            if(error[num] == true) return -1;
            else return 1;
        }

        int len = 0;
        while (num > 0){
            int now = num % 10; //뒷자리부터
            if(error[now] != true){ //누를수있으면
                num = num / 10;
                len++;
            } else{
                return -1;
            }
        }

        return len;
    }

}