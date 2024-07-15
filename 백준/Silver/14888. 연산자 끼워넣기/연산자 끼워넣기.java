
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int arr[];
    static int cal[];
    static int max = -1000000000;
    static int min = 1000000000;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        cal = new int[4];

        st = new StringTokenizer(br.readLine());

        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        for(int i=0; i<4; i++){
            cal[i] = Integer.parseInt(st.nextToken());
        }


        dfs(arr[0],1);
        System.out.println(max);
        System.out.println(min);

    }

    private static void dfs(int number, int index) {

        if(index == n){
            if(number>max){
                max = number;
            }
            if(number<min){
                min = number;
            }
            return;

        }


        for(int i=0; i<4; i++){
            if(cal[i]>0){
                cal[i]--;
                if(i==0){//+
                    int x = number + arr[index];
                    dfs(x,index+1);
                } else if(i==1){//-
                    int x = number - arr[index];
                    dfs(x,index+1);
                } else if(i==2){//*
                    int x = number * arr[index];
                    dfs(x,index+1);
                } else if(i==3){// /
                    int x = number / arr[index];
                    dfs(x,index+1);
                }
                cal[i]++;
            }
        }


    }
}
