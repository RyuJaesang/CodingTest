class Solution{
    public static void main(String[] args){
        System.out.println(solution(2147483647));
    }

    public static int solution(int n) {
        int answer = 0;
        int i;

        for (i = 5; i <= n; i *= 5) {
            if(i<0) break;
            answer += n / i;
        }

        return answer+1;
    }

}