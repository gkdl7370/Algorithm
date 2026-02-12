import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        String[][] answer = new String[record.length][2];
        int count = 0;
        
        Map<String, String> map = new HashMap<>();
        
        for(int i=0; i<record.length; i++){
            String[] str = record[i].split(" ");
        
            String state = str[0];
            String id = str[1];
            if(!state.equals("Leave")){
                map.put(id, str[2]);
            }
            
            answer[i][0] = state;
            answer[i][1] = id;
            
            if(!state.equals("Change")) {
                count++;
            }
        }
        
        String[] result = new String[count];
        
        int nowIndex = 0;
        for(int i=0; i<record.length; i++){
            String name = map.get(answer[i][1]);
        
            if(answer[i][0].equals("Enter")){
                result[nowIndex] = name + "님이 들어왔습니다.";
                nowIndex++;
            } else if(answer[i][0].equals("Leave")){
                result[nowIndex] = name + "님이 나갔습니다.";
                nowIndex++;
            }
         
        }
        
        return result;
    }
}