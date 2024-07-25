import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
class Solution {
    static String numbers;
    static char arr[];
    static Set<Integer> set = new HashSet<>();
    public int solution(String numbers) {
        int answer = 0;

        arr = new char[numbers.length()];

        for(int i=0; i<numbers.length(); i++){
            arr[i] = numbers.charAt(i);
        }

        Arrays.sort(arr);

        for(int i=0; i<arr.length/2; i++){
            char temp = arr[i];
            arr[i] = arr[arr.length-1-i];
            arr[arr.length-1-i] = temp;
        }

        String str = new String(arr); //char배열에 있는 수 문자열로 만들어두기


        int val = 0; // 숫자로 최대값 만들기
        for(int i=0; i<arr.length; i++){
            int num = Character.getNumericValue(arr[i]);
            val = val*10+num;
        }

        for(int i=0; i<=val; i++){ //0~최대값까지
            String temp = String.valueOf(i); //숫자를 문자열로 변환
            StringBuilder sb = new StringBuilder(temp); //문자열 수정하기 위해서 StringBuilder 사용
            for(int k=0; k<str.length(); k++){
                char ch = arr[k];
                int index = sb.indexOf(String.valueOf(ch)); //sb에서 arr에 있는 수를 찾아본다

                if(index != -1){ //찾았으면
                    sb.deleteCharAt(index); //지워버리기
                }

                if(sb.length() == 0){ //sb의 길이가 0이되면 다지워진거니까
                    set.add(Integer.parseInt(temp)); //해당수는 조합상 만들어지는 수가 된다
                    break; // 더이상 안찾아도됨
                }
            }
        }

        for(Integer num : set){ //set은 이런식으로 사용해야함
            int count = 0;

            if(num==2){
                answer++;
                continue;
            }
            for(int k=2; k<num; k++){
                if(num%k == 0){
                    count++;
                }
            }

            if(count == 0 && num > 1){ // 나눠지는게 없었다면 찾았다
                answer++;
            }
        }

        return answer;
    }
}
