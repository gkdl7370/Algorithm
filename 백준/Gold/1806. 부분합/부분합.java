
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.DoubleStream;

public class Main {
    static int N, S;
    static int[] arr;
    public static void main(String[] args) throws IOException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

       int n = Integer.parseInt(st.nextToken());
       int s = Integer.parseInt(st.nextToken());

       st = new StringTokenizer(br.readLine());

       int arr[] = new int[n];

       for(int i=0; i<n; i++){
           arr[i] = Integer.parseInt(st.nextToken());
       }


       int start=0; //시작점
       int end=0; //끝점
       int len=Integer.MAX_VALUE; //길이
       int sum = 0;

       while (start <=n && end <=n){

           if(sum >= s){
               len = Math.min(len,end-start);
               sum -= arr[start];
               start++;
           } else if(sum<s){
               if(end == n) break;
               sum += arr[end];
               end++;
           }

       }
       if(len == Integer.MAX_VALUE){
           System.out.println(0);
       } else{
           System.out.println(len);
       }



    }
}

