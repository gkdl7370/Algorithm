import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
         if (arr.length == 0) {
            return new int[0];
        }

        int[] temp = new int[arr.length];
        int size = 0;

        temp[size++] = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i - 1]) {
                temp[size++] = arr[i];
            }
        }

        return Arrays.copyOf(temp, size);
    }
}