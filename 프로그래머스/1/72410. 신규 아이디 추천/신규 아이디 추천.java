class Solution {
    public String solution(String new_id) {
        
        String str = new_id.toLowerCase();
        
        str = str.replaceAll("[^a-z0-9-_.]","");
        str = str.replaceAll("[.]{2,}",".");
        str = str.replaceAll("^[.]|[.]$","");
        
        if(str.isEmpty()){
            str = "a";
        }
        if(str.length() >= 16){
            str = str.substring(0,15);
            str = str.replaceAll("[.]$","");
        }
        while(str.length() < 3){
            str += str.charAt(str.length() - 1);
        }
        
        return str;
                            
        
    }
}