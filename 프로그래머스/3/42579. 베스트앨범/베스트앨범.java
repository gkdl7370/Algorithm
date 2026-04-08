import java.util.*;
class Solution {
    public int[] solution(String[] genres, int[] plays) {
        
        Map<String, Integer> total = new HashMap<>();
        Map<String, List<int[]>> songs = new HashMap<>();
        
        for(int i=0; i<genres.length; i++){
            String type = genres[i];
            
            total.put(type, total.getOrDefault(type, 0) + plays[i]);
            
            songs.putIfAbsent(type, new ArrayList<>());
            songs.get(type).add(new int[]{i, plays[i]});
        }
        
        List<String> sName = new ArrayList<>(total.keySet());
        sName.sort((a,b) -> total.get(b) - total.get(a));
        
        List<Integer> result = new ArrayList<>();
        
        for(String gen : sName){
            List<int[]> list = songs.get(gen);
            
            list.sort((a,b) ->{
                if(b[1] == a[1]) return a[0] - b[0];
                return b[1] - a[1];
            });
            
            for(int i=0; i<list.size() && i<2; i++){
                result.add(list.get(i)[0]);        
            }
        }
        
        int[] answer = new int[result.size()];

        for(int i=0; i<result.size(); i++){
            answer[i] = result.get(i);
        }

        return answer;
        
    }
}