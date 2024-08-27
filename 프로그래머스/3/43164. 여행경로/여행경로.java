
import java.util.ArrayList;
import java.util.Collections;
class Solution {
    ArrayList<String> list = new ArrayList<>();
    boolean visited[];
    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];

        dfs(0, "ICN", "ICN", tickets);

        Collections.sort(list);

        return list.get(0).split(" ");
    }

    public void dfs(int count, String now, String path, String[][] tickets){

          if(count == tickets.length){
            list.add(path);
            return;
        }

        for(int i=0; i<tickets.length; i++){
            if(tickets[i][0].equals(now)){
                if(!visited[i]){
                    visited[i] = true;
                    dfs(count+1,tickets[i][1],path+" "+tickets[i][1], tickets);
                    visited[i] = false;
                }
            }
        }
    }
}
