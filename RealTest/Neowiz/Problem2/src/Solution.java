class Solution{
    public static void main(String[] args){
        int[] input = new int[]{1, 2, 3, 5, 6};
        System.out.println(solution(input));
    }

    static public int solution(int[] s) {
        int answer = 0;

        for (int i = 0; i < s.length - 1; i++) {
            if ((i+answer) % 2 == 0 && s[i] >= s[i + 1])
                answer++;
            else if ((i+answer) % 2 == 1 && s[i] <= s[i + 1])
                answer++;
        }

        return answer;
    }
}