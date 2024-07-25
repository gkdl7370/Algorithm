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
            char temp= arr[i];
            arr[i] = arr[arr.length-1-i];
            arr[arr.length-1-i] = temp;
        }

        String str = new String(arr);


        int val = 0;
        for(int i=0; i<arr.length; i++){
            int num = Character.getNumericValue(arr[i]);
            val = val*10+num;
        }

        for(int i=0; i<=val; i++){
            String temp = String.valueOf(i);
            StringBuilder sb = new StringBuilder(temp);
            for(int k=0; k<str.length(); k++){
                char ch = arr[k];
                int index = sb.indexOf(String.valueOf(ch));

                if(index != -1){
                    sb.deleteCharAt(index);
                }

                if(sb.length() == 0){
                    set.add(Integer.parseInt(temp));
                    break;
                }
            }
        }

        for(Integer num : set){
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

            if(count == 0 && num > 1){
                answer++;
            }
        }

        return answer;
    }
}
