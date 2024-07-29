class Solution {
    public int[] solution(int brown, int yellow) {
       int[] answer = {};
        int map = brown + yellow; //총크기

        int line = map/3;
        boolean check =false;
        while (!check){
            for(int k=3; k<=line; k++){
                if(line>=k){
                    if(line*k>map){
                        break;
                    }
                    if(line*k == map){
                        if((k-2)*(line-2) == yellow){
                            answer = new int[]{line, k};
                            check = true;
                            break;
                        }
                    }
                }
            }

            line--;
        }

        return answer;
    }
}