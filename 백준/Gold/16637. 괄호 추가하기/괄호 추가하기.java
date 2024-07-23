
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static int n;
    static int max;
    static int[] number;
    static char[] operation;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        String str = br.readLine();

        number = new int[n/2+1];
        operation = new char[n/2];

        for(int i=0; i<n; i++){
            if(i % 2 == 0){
                number[i/2] = Integer.parseInt(String.valueOf(str.charAt(i)));
            }else{
                operation[i/2] = str.charAt(i);
            }
        }

        max = Integer.MIN_VALUE;

        dfs(0,number[0]);

        System.out.println(max);

    }

    private static void dfs(int index, int sum) {

        if(index >= n/2){
            max = Math.max(max,sum);
            return;
        }

        int val = cal(index,sum,number[index+1]);

        dfs(index+1, val);

        if(index+2 <= n/2){
            int num = cal(index+1,number[index+1],number[index+2]);
            int value = cal(index, sum, num);
            dfs(index+2, value);
        }

    }

    private static int cal(int index, int x, int y) {
        char op = operation[index];
        int value = 0;
        if(op == '+'){
            value = x+y;
        } else if(op == '-'){
            value = x-y;
        } else if(op == '*'){
            value = x*y;
        }
        return value;
    }

}

