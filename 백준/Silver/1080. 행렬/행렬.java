import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n;
        int m;

        int  check = 0;
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());

        char arr[][] = new char [n][m];
        char brr[][] = new char [n][m];


        String str;
        for(int i=0; i<n; i++){
            str = br.readLine();
            for(int j=0; j<m; j++){
                arr[i][j] = str.charAt(j);
            }
        }

        for(int i=0; i<n; i++){
            str = br.readLine();
            for(int j=0; j<m; j++){
                brr[i][j] = str.charAt(j);
                if(arr[i][j] == brr[i][j]){
                    check++;
                }
            }
        }

        int val=0;

        if (check == n*m){
            System.out.println(0);
        } else if(m<3 || n<3){
            System.out.println(-1);
        } else {

            for(int i=0; i<n-2; i++){
                for(int j=0; j<m-2; j++){


                    if(arr[i][j] != brr[i][j]){

                        for (int k = i; k < i + 3; k++) {
                        for (int l = j; l < j + 3; l++) {
                            if (arr[k][l] == '1') {
                                arr[k][l] = '0';
                            } else {
                                arr[k][l] = '1';
                            }
                        }
                    }
                    val++;
                    }


                }
            }

            boolean ch = true;

            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    if(arr[i][j]!=brr[i][j]){
                        ch = false;
                        break;
                    }
                }
                if(ch == false) break;
            }


            if (ch) {
                System.out.println(val);
            } else {
                System.out.println(-1);
            }


        }
    }
}
