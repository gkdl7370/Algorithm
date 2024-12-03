import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int c;
    static ArrayList<Integer> liar;
    static ArrayList<Integer> party[];
    static boolean visited[];
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        c = Integer.parseInt(st.nextToken());

        if(c == 0){ //들은사람 없으면 다 거짓말해도됨
            System.out.println(m);
            return;
        }

        liar = new ArrayList<>();
        party = new ArrayList[m+1];
        visited = new boolean[m+1];
        for(int i=1; i<=m; i++){
            party[i] = new ArrayList<>();
        }

        for(int i=0; i<c; i++){
            int a = Integer.parseInt(st.nextToken());
            liar.add(a);
        }


        for(int i=1; i<=m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            for(int j=0; j<a; j++){
                int b = Integer.parseInt(st.nextToken());
                party[i].add(b);
            }
        }

        int val = 0;

        for(int i=1; i<=m; i++){
            for(int j=0; j<liar.size(); j++){
                int num = liar.get(j);
                if(party[i].contains(num)){ //파티에 첩자가있다
                    if(visited[i] != true){
                        for(int k=0; k<party[i].size(); k++){ //파티인원 싹 다 첩자로 잡아넣어
                            if(liar.contains(party[i].get(k)) == false){
                                liar.add(party[i].get(k));
                            }
                        }
                        visited[i] = true;
                        i=0;
                        break;
                    }
                }
            }
        }

        for(int i=1; i<=m; i++){
            for(int j=0; j<liar.size(); j++){
                int num = liar.get(j);
                if(party[i].contains(num)){
                    if(visited[i] != true) visited[i] = true;
                    break;
                }
            }
        }

        for(int i=1; i<visited.length; i++){
            if(visited[i] == false) val += 1;
        }

        System.out.println(val);
    }


}
