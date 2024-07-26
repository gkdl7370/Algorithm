import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n;
        int m;
        char arr[];
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new char[n];
        String s = br.readLine();

        for(int i=0; i<n; i++){
            arr[i] =s.charAt(i);
        }

        int count=0;
        boolean check;


        for(int i=0; i<n; i++){
            if(arr[i] == 'H'){
                continue;
            } else if(arr[i] == 'P'){ //사람인 경우를 찾는다
                for(int j=m; j>0; j--){ //찾았으면
                    check = false; //햄버거를 왼쪽에서 찾았는지 확인하기위한 용도

                        //최대거리이동한게 배열을 벗어나지 않으면서 - 왼쪽부터 햄버거를 찾는다
                        if(i-j >= 0){
                            if(arr[i-j] =='H'){
                                arr[i-j] = 'E';
                                count++;
                                check = true;
                                break;
                            }
                        }

                        if(j==1 && check == false){ //왼쪽으로 최대거리만큼 찾았는데 못찾았어 그럼 오른쪽으로 찾아
                            for(int k=1; k<=m; k++){

                                if(i+k<=arr.length-1){
                                    if(arr[i+k] == 'H'){
                                        arr[i+k] = 'E';
                                        count++;
                                        break;
                                    }
                                }
                            }
                        }
                }

            }
        }

        System.out.println(count);
    }
}
