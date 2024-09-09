import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
         int[] answer = new int[progresses.length];

        Queue<Integer[]> q = new LinkedList<>();
        int[] val = new int[progresses.length];

        for(int i=0; i<progresses.length; i++){
            q.add(new Integer[]{progresses[i], i, 0});
        }



        while (!q.isEmpty()){

            Integer[] poll = q.poll();

            int x = poll[0];
            int y = poll[1];
            int z = poll[2];

            if(x >= 100){
                val[y] = z;
            } else{
                x += speeds[y];
                q.add(new Integer[]{x, y, z+1});
            }


        }

        List<Integer> list = new LinkedList<>();
        int x = val[0];
        int num = 0;
        while (num < val.length) {
            int count = 0;
            int currentDay = val[num];

            // 완료일이 같은 작업들을 세는 과정
            while (num < val.length && val[num] <= currentDay) {
                count++;
                num++;
            }

            // 완료된 작업 수를 리스트에 추가
            list.add(count);
        }

        for (int i=0; i<list.size(); i++){
            answer[i] = list.get(i);
        }

        return Arrays.copyOf(answer, list.size());
    }
}