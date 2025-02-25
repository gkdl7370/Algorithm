class Solution {
    static int[] input, output;
    public int[] solution(int[][] edges) {
      int [] answer = new int[4];
        
        int maxNode = 0;
        for (int i = 0; i < edges.length; i++) {
            maxNode = Math.max(maxNode, Math.max(edges[i][0], edges[i][1]));
        }

        input = new int[maxNode+1];
        output = new int[maxNode+1];

        for(int i=0; i<edges.length; i++){
            int x = edges[i][0];
            int y = edges[i][1];

            input[y] = input[y] + 1;
            output[x] = output[x] + 1;

        }

        for(int i=1; i<=maxNode; i++){
            //받는거없이 나가는게 2개이상이면 추가된 노드
            if(input[i] == 0 && output[i] >= 2) {
                answer[0] = i;
            }

            //하나 이상 받고 나가는거 없으면 막대
            else if(input[i] >= 1 && output[i] == 0) {
                answer[2]++;
            }

            //두개 나가고 두개 들어오면 8자
            else if(input[i] >= 2 && output[i] >= 2) {
                answer[3]++;
            }
        }
        if (answer[0] > 0) {
            answer[1] = output[answer[0]] - (answer[2] + answer[3]);
        }

        return answer;
    }
}