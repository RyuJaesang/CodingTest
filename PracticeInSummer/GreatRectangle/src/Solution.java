class Solution {
    public static void main(String[] args){
        long w = 1;
        long h = 2;

        System.out.println(gcd(w, h));
    }

    public static long solution(long w, long h) {
        long answer = 0;

        long gcd = gcd(w, h);

        long box_row = w / gcd;
        long box_col = h / gcd;

        answer = (w * h) - (((box_row -1) + box_col) * gcd);

        return answer;
    }

    public static long gcd(long a,long b){
        if(a % b == 0)
            return b;
        return gcd(b,a%b);
    }
}