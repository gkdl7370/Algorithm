import java.util.HashSet;
import java.util.Set;
class Solution {
    boolean visited[];
    int count;
    int parent[];
    public int solution(int n, int[][] computers) {
       int answer = 0;
        parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && computers[i][j] == 1) {
                    int x = find(i);
                    int y = find(j);

                    if (x != y) { // 이어져 있지 않다면
                        union(x, y);
                    }
                }
            }
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(find(i)); // 모든 노드의 루트를 확인
        }

        answer = set.size();
        return answer;
    }

    public void union(int x, int y) {
          if (x != y) {
            parent[x] = y;
        }
    }

    public int  find(int x) {
         if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
}