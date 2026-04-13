import java.util.*;
class Solution {
    public int[] solution(String[] genres, int[] plays) {
        
         Map<String, Integer> total = new HashMap<>(); // 노래 종류 : 총 재생횟수
        Map<String, List<int[]>> count = new HashMap<>(); // 노래별 재생 횟수

        for(int i=0; i<genres.length; i++){
            String type = genres[i];

            total.put(type, total.getOrDefault(type, 0) + plays[i]); //현재 노래가 맵에 있는지 확인 있으면 재생수+

            count.putIfAbsent(type, new ArrayList<>());
            count.get(type).add(new int[]{i, plays[i]});
        }

        List<String> songType = new ArrayList<>(total.keySet()); // 노래 종류만 리스트에 넣어놓고

        songType.sort((a,b) -> total.get(b) - total.get(a)); //노래 재생횟수대로 내림차순 정렬
        
        List<Integer> result = new ArrayList<>();
        for(String val : songType){

            List<int[]> index = count.get(val);

            index.sort((a,b) ->{
               if(b[1] == a[1]) return a[0] - b[0]; //재생횟수 같으면 index 낮은거
               return b[1] - a[1]; // 재생횟수가 높은거 먼저
            });
            
            
            for(int i=0; i<index.size() && i<2; i++){
                result.add(index.get(i)[0]);
            }
        }
        
        int[] answer = new int[result.size()];

        for(int i=0; i<answer.length; i++){
            answer[i] = result.get(i);
        }
        
        return answer;
        
    }
}