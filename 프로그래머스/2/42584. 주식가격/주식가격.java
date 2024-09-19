import java.util.*;
class Solution {
    public int[] solution(int[] prices) {
       int[] answer = new int[prices.length];
        Queue<Integer> q = new LinkedList<>();
        int count;
        for(int i=0; i<prices.length; i++){
            count = 0;
            for(int j = i+1; j<prices.length; j++){
                if(prices[i]>prices[j]){
                    count++;
                    break;
                } else{
                   count++;
                }
            }

            q.add(count);
        }

        for(int i=0; i<answer.length; i++){
            answer[i] = q.poll();
        }
        return answer;
    }
}