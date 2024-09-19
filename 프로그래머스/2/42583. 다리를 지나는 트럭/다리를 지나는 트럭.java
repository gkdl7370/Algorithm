import java.util.*;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        int index=0;
        Queue<Integer> q = new LinkedList<>();
        List<Integer> list = new LinkedList<>();
        
        
        for(int i=0; i<truck_weights.length; i++){
            q.add(truck_weights[i]);
        }
        
        while(!q.isEmpty()){
            int t=0;
           
            if(weight >= q.peek()){
                t = q.poll();
                list.add(bridge_length);
                weight = weight - t;
            }
            
            answer++;
            
            for(int i=0; i<list.size(); i++){
                if(list.get(i) != 0){
                    list.set(i, list.get(i) - 1);
                    if(list.get(i) == 0){
                        list.remove(i);
                        weight += truck_weights[index];
                        index++;
                        if(list.size() != 0) i--;
                    }
                }
            }
        }
        
        int x = list.get(list.size()-1) + 1;
        answer += x;
        return answer;
    }
}