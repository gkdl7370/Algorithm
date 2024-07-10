
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        int arr[][] = new int[h][w];
        boolean vit[][] = new boolean[h][w];

        String str[] = br.readLine().split(" ");

        for(int i=0; i<str.length; i++){
            int k = Integer.parseInt(str[i]);
            for(int j=1; j<=k; j++){
                vit[h-j][i] = true;
            }
        }

        int val=0;


        //배열 마지막부터 돌려야함 -> 바닥
        for(int i=h-1; i>=0; i--){
            int rain=0;
            boolean wall = false;
            for(int j=0; j<w; j++){
                if(vit[i][j]==true){//바닥 왼쪽부터 오른쪽까지 벽을 찾는다
                    if(j-1 >= 0 && vit[i][j-1]==true){
                        wall = true;
                        continue;
                    } else if(wall == false){ //벽을 찾았는데 처음 찾은거인지 확인
                        wall = true; //찾았으면 하나 찾았다고 저장
                    } else if(wall == true) { //벽을 찾았는데 두번째 이상 찾은거야
                        val += rain; //결과값에 지금까지 채워놓은 비를 저장해준다
                        rain = 0; //모아둔 비도 초기화
                    }
                } else if(vit[i][j]== false){ //벽이 아니면
                    if(wall == true){ // 왼쪽에서 벽을 찾았으면
                        rain++; //비를 채워준다
                    }
                }
            }
        }


        System.out.println(val);

    }
}
