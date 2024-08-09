class Solution {
    int answer;
    boolean visited[];
    public int solution(int[] numbers, int target) {
        answer = 0;
        visited = new boolean[numbers.length+1];
        dfs(0, 0,target, numbers);

        return answer;
    }
    
    public void dfs(int a, int count, int target, int[] numbers) {

        if(a == target && count == numbers.length){
            answer++;
            return;
        }

        if(count<numbers.length){
            dfs(a+numbers[count], count+1, target, numbers);
            dfs(a-numbers[count], count+1, target, numbers);
        }

    }
}