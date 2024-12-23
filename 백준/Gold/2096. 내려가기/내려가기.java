import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] dpMax = new int[2][3]; // 최댓값 DP
        int[][] dpMin = new int[2][3]; // 최솟값 DP

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int[] row = new int[3];
            for (int j = 0; j < 3; j++) {
                row[j] = Integer.parseInt(st.nextToken());
            }

            if (i == 0) {
                // 첫 번째 행 초기화
                dpMax[0][0] = dpMin[0][0] = row[0];
                dpMax[0][1] = dpMin[0][1] = row[1];
                dpMax[0][2] = dpMin[0][2] = row[2];
            } else {
                // 최댓값 계산
                dpMax[1][0] = row[0] + Math.max(dpMax[0][0], dpMax[0][1]);
                dpMax[1][1] = row[1] + Math.max(dpMax[0][0], Math.max(dpMax[0][1], dpMax[0][2]));
                dpMax[1][2] = row[2] + Math.max(dpMax[0][1], dpMax[0][2]);

                // 최솟값 계산
                dpMin[1][0] = row[0] + Math.min(dpMin[0][0], dpMin[0][1]);
                dpMin[1][1] = row[1] + Math.min(dpMin[0][0], Math.min(dpMin[0][1], dpMin[0][2]));
                dpMin[1][2] = row[2] + Math.min(dpMin[0][1], dpMin[0][2]);

                // DP 배열 갱신
                dpMax[0][0] = dpMax[1][0];
                dpMax[0][1] = dpMax[1][1];
                dpMax[0][2] = dpMax[1][2];
                dpMin[0][0] = dpMin[1][0];
                dpMin[0][1] = dpMin[1][1];
                dpMin[0][2] = dpMin[1][2];
            }
        }

        // 최종 결과 계산
        int maxResult = Math.max(dpMax[0][0], Math.max(dpMax[0][1], dpMax[0][2]));
        int minResult = Math.min(dpMin[0][0], Math.min(dpMin[0][1], dpMin[0][2]));

        System.out.println(maxResult + " " + minResult);
    }
}