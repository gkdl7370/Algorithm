class Solution {
    static String str = "";
    static int answer;
    static char[] arr;
    static boolean find;
    public int solution(String word) {
      answer = 0;

        arr = new char[]{'A', 'E', 'I', 'O', 'U'};

        for(int i=0; i<5; i++){
            dfs(i,str,word);
        }




        return answer;
    }

    private static void dfs(int a, String val, String word) {
        if(find){
            return;
        }

        answer++;
        val += arr[a];

        if(val.equals(word)){
            find = true;
            return;
        }

        if(val.length()>=5){
            return;
        }

        for(int i=0; i<5; i++){
            dfs(i, val, word);
        }

    }

}
