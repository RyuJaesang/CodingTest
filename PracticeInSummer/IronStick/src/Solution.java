import java.util.Stack;

class Solution {
    public static void main(String[] args){
        String arrangement = "()(((()())(())()))(())";
        System.out.println(solution(arrangement));
    }

    public static int solution(String arrangement) {
        int answer = 0;

        Stack<Character> arrangementStack = new Stack<>();

        for(int i = 0; i<arrangement.length(); i++){
            if(arrangement.charAt(i) == '(' && !isLaser(arrangement, i)){
                arrangementStack.push(arrangement.charAt(i));
            }
            else if(arrangement.charAt(i) == '(' && isLaser(arrangement, i)){
                answer += arrangementStack.size();
                i++;
            }
            else if(arrangement.charAt(i) == ')'){
                arrangementStack.pop();
                answer += 1;
            }
        }
        return answer;
    }

    public static boolean isLaser(String arrangement, int index){
        if(arrangement.charAt(index+1) == ')'){
            return true;
        }
        return false;
    }
}