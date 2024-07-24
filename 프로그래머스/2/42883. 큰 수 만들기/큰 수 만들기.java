class Solution {
    public String solution(String number, int k) {
     int numLen = number.length();
        int valLen = numLen - k; 
        char[] answer = new char[valLen]; 
        int start = 0;
        int remain = valLen;

        for (int i = 0; i < valLen; i++) {
            char max = '0';
            int maxIndex = start;

            for (int j = start; j <= numLen - remain; j++) {
                char current = number.charAt(j);
                if (max < current) {
                    max = current;
                    maxIndex = j;
                }
            }

            answer[i] = max;
            start = maxIndex + 1; 
            remain--; 
        }

        return new String(answer);
    }
}