public class Solution {
    public static void main(String[] args){
        int n = 999999999;
        int[] result = solution(n);
        for(int i = 0; i<result.length; i++){
            System.out.println(result[i]);
        }
    }

    static int answerCount = Integer.MAX_VALUE;
    static int answerValue = 0;
    public static int[] solution(int n) {

        find(n, 0);

        int[] answer = {answerCount, answerValue};
        return answer;
    }

    public static void find(int n, int count){
        if(n / 10 == 0){
            if(answerCount > count){
                answerCount = count;
                answerValue = n;
            }
            return;
        }
        if(answerCount < count){
            return;
        }

        String nString = Integer.toString(n);
        for(int i = 1; i<nString.length(); i++){
            String a = nString.substring(0, i);
            String b = nString.substring(i, nString.length());

            if(!b.startsWith("0")){
                find(Integer.parseInt(a) + Integer.parseInt(b), count+1);
            } else if(b.equals("0")){
                find(Integer.parseInt(a) + Integer.parseInt(b), count+1);
            }
        }
    }
}
