
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        for (int k = 0; k < n; k++) {
            int T = Integer.parseInt(br.readLine());
            long[] arr = new long[T];
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < T; j++) {
                arr[j] = Long.parseLong(st.nextToken());
            }

            long max = 0;
            long coin = 0;

            // 배열의 끝에서부터 탐색하여 최대 이익 계산
            for (int j = T - 1; j >= 0; j--) {
                if (arr[j] > max) {
                    max = arr[j];
                } else {
                    coin += (max - arr[j]);
                }
            }

            System.out.println(coin);
        }
    }
}