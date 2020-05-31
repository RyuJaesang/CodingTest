import java.util.ArrayList;


public class main {

    public static void main(String[] args){
        Solution sol = new Solution();
        long n = 1000000000;

        long result = sol.solution(n);
        System.out.println(result);
    }
}

class Solution {
    long solution(long n){
        ArrayList<Long> ansList = new ArrayList<Long>();
        ansList.add((long) 0);
        ansList.add((long) 1);

        long index = 1;
        int count = 1;

        for(int i = 2; i <= n; i++) {
            if(i == count * 2) {
                index *= 3;
                count *= 2;
                ansList.add(index);
            } else {
                ansList.add(ansList.get(count) + ansList.get((i - count)));
            }
        }

        return ansList.get((int)n);
    }
}