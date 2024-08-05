import java.util.ArrayList;
class Solution {
    static ArrayList list[];
    static boolean visited[];
    public int solution(int n, int[][] wires) {
      int answer = Integer.MAX_VALUE;


        int[] arr = new int[n+1];
        list = new ArrayList[n+1];

        for(int i=1; i<n+1; i++){
            list[i] = new ArrayList<>();
        }


        for(int i=0; i<wires.length; i++){
            arr[wires[i][0]] = arr[wires[i][0]]+1;
            arr[wires[i][1]] = arr[wires[i][1]]+1;
            list[wires[i][0]].add(wires[i][1]);
            list[wires[i][1]].add(wires[i][0]);
        }


        for(int i=1; i<list.length; i++){
            for(int j=0; j<list[i].size(); j++){
                visited = new boolean[n+1];
                visited[i] = true;
                visited[(int) list[i].get(j)] = true;
                int count1 = moving(i);
                int count2 = moving((Integer) list[i].get(j));
                answer = Math.min(answer,Math.abs(count1 - count2));
            }
        }

        return answer;
    }

    private static int moving(int point) {
        visited[point] = true;
        int val = 1;

        for(int i=0; i<list[point].size(); i++){
            if(visited[(int) list[point].get(i)] != true){
                visited[(int) list[point].get(i)] = true;
                val += moving((int) list[point].get(i));
            }
        }
        return val;
    }

}
