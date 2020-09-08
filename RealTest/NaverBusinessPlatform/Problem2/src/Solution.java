import java.util.Stack;

class Solution {
    public static void main(String[] args){
        String compressed = "2(2(hi)2(co))x2(bo)";

        System.out.println(solution(compressed));
    }


    public static String solution(String compressed) {
        String answer = "";

        Stack<String> compressedStack = new Stack<>();

        for(int i = 0; i<compressed.length(); i++){
            if(compressed.charAt(i) != ')'){
                if(compressed.charAt(i) >= 48 && compressed.charAt(i) <= 57){
                    String tempNum = "";
                    while(compressed.charAt(i) >= 48 && compressed.charAt(i) <= 57){
                        tempNum += compressed.charAt(i);
                        i++;
                    }
                    compressedStack.push(tempNum);
                    i--;
                }
                else{
                    compressedStack.push(Character.toString(compressed.charAt(i)));
                }
            }

            else{
                String tempToken = "";
                while(!compressedStack.peek().equals("(")) {
                    tempToken = compressedStack.pop() + tempToken;
                }
                compressedStack.pop();
                int iterNum = Integer.parseInt(compressedStack.pop());

                String tempString = "";
                for(int j = 0; j<iterNum; j++){
                    tempString += tempToken;
                }
                compressedStack.push(tempString);
            }
        }
        while(!compressedStack.isEmpty()){
            answer = compressedStack.pop() + answer;

        }

        return answer;
    }
}