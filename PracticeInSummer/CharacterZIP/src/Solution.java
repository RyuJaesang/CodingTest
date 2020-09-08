import java.util.ArrayList;

public class Solution {
    public static void main(String[] args){
        String s = "ababababababc";
        System.out.println(solution(s));
    }


    public static int solution(String s) {
        int answer = s.length();

        for(int i = 1; i<=s.length()/2; i++){
            int a = getResult(s, i);

            if(a < answer){
                answer = a;
            }
        }
        return answer;
    }

    public static int getResult(String s, int index){
        String result = "";
        ArrayList<String> s_token = new ArrayList<>();

        // Tokenizing String
        for(int i = 0; i < s.length(); i += index){
            if(i+index < s.length()){
                s_token.add(s.substring(i, i+index));
            } else{
                s_token.add(s.substring(i, s.length()));
            }
        }

        // Calculate result
        for(int i = 0; i<s_token.size()-1; i++){
            if(s_token.get(i).equals(s_token.get(i+1))){
                // 이미 2인경우
                if(i == 0){
                    s_token.set(i, "2");
                } else if(s_token.get(i-1).charAt(0) >= 48 && s_token.get(i-1).charAt(0) <= 57){
                    s_token.remove(i);
                    int count = Integer.parseInt(s_token.get(i-1));
                    s_token.set(i-1, String.valueOf(count+1));
                    i--;
                } else{
                    s_token.set(i, "2");
                }
            }
        }

        // Concatenate
        for(String elem : s_token){
            result += elem;
        }

        return result.length();
    }
}
