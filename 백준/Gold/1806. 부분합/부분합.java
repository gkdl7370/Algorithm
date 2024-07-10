
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

       while (start <=n && end <=n){ //***(중요)배열의 마지막 수까지 찾아야하기 때문에 <= 사용

           if(sum >= s){ //값이 기준값을 넘기면
               len = Math.min(len,end-start); //end까지의 길이 저장
               sum -= arr[start]; //현재 합에서 지금 start포인트 값 빼고
               start++; //start 값 증가
           } else if(sum<s){ //값이 기준값보다 적으면
               if(end == n) break; //end가 n이면 배열인덱스 오류 남
               sum += arr[end]; // end포인트값 더하고
               end++; // end값 증가
           }

       }
       if(len == Integer.MAX_VALUE){ //len값이 변하지 않았으면 기준값을 못만든것
           System.out.println(0);
       } else{
           System.out.println(len);
       }



    }
}

