import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        int numbers[] = new int[arr.length];
        
        numbers[0] = arr[0];
        int size = 1;
        
        for(int i=1; i<arr.length; i++){
            if(arr[i] != arr[i-1]){
                numbers[size] = arr[i];
                size++;
            }
        }
        
        int result[] = new int[size];
        
        for(int i=0; i<size; i++){
            result[i] = numbers[i];
        }
        
        return result;
    }
}