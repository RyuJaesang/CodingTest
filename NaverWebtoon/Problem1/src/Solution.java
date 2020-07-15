// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    public static void main(String[] args){
        int input = -0;

        int result = solution(input);

        System.out.println(result);
    }

    public static int solution(int N) {
        // write your code in Java SE 8
        String n = Integer.toString(N);

        if(N >= 0) {
            for (int i = 0; i < n.length(); i++) {
                int temp = Integer.parseInt(String.valueOf(n.charAt(i)));

                if (temp < 5) {
                    n = n.substring(0, i) + "5" + n.substring(i, n.length());
                    break;
                }
                if (i == n.length()){
                    n = n + "5";
                }
            }
        }
        else{
            for (int i = 1; i < n.length(); i++) {
                int temp = Integer.parseInt(String.valueOf(n.charAt(i)));

                if (temp > 5) {
                    n = n.substring(0, i) + "5" + n.substring(i, n.length());
                    break;
                }
                if (i == n.length()){
                    n = n + "5";
                }
            }
        }

        int result = Integer.parseInt(n);

        return result;
    }
}

