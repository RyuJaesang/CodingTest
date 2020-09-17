public class Solution {
    public static void main(String[] args){
        String name = "JAN";

        System.out.println("The answer is ");
        System.out.println(solution(name));
    }


    public static int solution(String name) {
        int valueSum = 0;
        int case3Answer = 0;
        int case2Answer = 0;
        int case1Answer = 0;
        int aIntValue = (int) 'A';

        for(int i = 0; i<name.length(); i++){
            char t = name.charAt(i);
            int t_a = (int) t - aIntValue;
            if(t_a > 13){
                t_a = 26 - t_a;
            }
            valueSum += t_a;
        }

        // Case 1 순차적으로
        for(int i = name.length()-1; i >= 0; i--){
            if(name.charAt(i) != 'A'){
                case1Answer = i;
                break;
            }
        }

        // Case 2 역순으로

        for(int i = 1; i<name.length(); i++){
            if(name.charAt(i) != 'A'){
                case2Answer = name.length() - i;
                break;
            }
        }

        // Case 3 가다가 꺾기
        int pivot = name.length() /2;
        for(int i = pivot; i >= 0; i--){
            if(name.charAt(i) != 'A'){
                case3Answer = i * 2;
                break;
            }
        }
        for(int i = pivot+1; i < name.length(); i++){
            if(name.charAt(i) != 'A'){
                case3Answer += (name.length()-i);
                break;
            }
        }

        int minDist = Math.min(case1Answer, case2Answer);
        minDist = Math.min(minDist, case3Answer);

        return minDist + valueSum;
    }
}
