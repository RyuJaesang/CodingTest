class Solution {
    public static void main(String[] args){
        int n = 21;

        System.out.println(solution(n));
    }

    public static String solution(int n) {
        String answer = dectotri(n);
        answer = filter(answer);
        return answer;
    }

    public static String dectotri(int n){
        String result = "";
        while(n != 0){
            int temp = n % 3;
            n = n / 3;
            result = Integer.toString(temp) + result;
        }
        return result;
    }

    public static String filter(String input){
        while(input.contains("40") || input.contains("20") || input.contains("10")){
            input = input.replaceAll("10", "04");
            input = input.replaceAll("20", "14");
            input = input.replaceAll("40", "24");
        }
        return Long.toString(Long.parseLong(input));
    }

}