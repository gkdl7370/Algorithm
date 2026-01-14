import java.util.*;
class Solution {
    public int[] solution(int[] fees, String[] records) {
        int baseTime = fees[0];
        int baseFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];
        
        Map<String, Integer> parking = new HashMap<>(); //차량 현황
        Map<String, Integer> timeMap = new TreeMap<>(); //차량별 주차시간 - 정렬
        
        //우선 배열에 담긴 정보를 가공
        for(int i=0; i<records.length; i++){
            String poll[] = records[i].split(" ");
            int time = parseTime(poll[0]); //String ->int형으로 변환 + 시 -> 분으로 변환
            String carNum = poll[1];
            String status = poll[2];
            
            if(status.equals("IN")){ //입차인경우
                parking.put(carNum, time);
            } else{ //출차인경우
                //주차한 시간 계산해서 timeMap에 저장
                int outTime = parking.remove(carNum);
                int totalTime = time - outTime; //출차시간 - 입차시간 = 주차시간
                //기존에 저장된 값을 갱신
                timeMap.put(carNum, timeMap.getOrDefault(carNum, 0) + totalTime);
            }
        }
        
        int outTime = parseTime("23:59");        
        //23:59분까지 안나간 차들 모두 정리
        List<String> list = new ArrayList<>(parking.keySet());
        for(int i=0; i<list.size(); i++){
            String carNum = list.get(i);
            int intTime = parking.remove(carNum);
            int totalTime = outTime - intTime;
            
            timeMap.put(carNum, timeMap.getOrDefault(carNum, 0) + totalTime);
        }
        
        //요금계산
        list = new ArrayList<>(timeMap.keySet());
        int answer[] = new int[list.size()];
        
        for(int i=0; i<list.size(); i++){
            String carNum = list.get(i);
            int totalTime = timeMap.remove(carNum);
            
            if(totalTime <= baseTime){
                answer[i] = baseFee;
            } else{
                int costTime = totalTime - baseTime;
                answer[i] = baseFee + ((int) Math.ceil((double) costTime / unitTime)) * unitFee;
            }
            
        }
        return answer;
    }
    
    private int parseTime(String time){
        String poll[] = time.split(":");
        return Integer.parseInt(poll[0]) * 60 + Integer.parseInt(poll[1]);
    }
}