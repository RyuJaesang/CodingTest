import java.util.*;

class Solution{
    public static int solution(String p) {
        int answer = 0;
        answer = countCheck(p, '<') + countCheck((new StringBuffer(p)).reverse().toString(), '>');

        return answer;
    }

    public static int countCheck(String s, char target) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if(c == target){
                count++;
            }
            else{
                return count;
            }
        }
        return count;
    }
}