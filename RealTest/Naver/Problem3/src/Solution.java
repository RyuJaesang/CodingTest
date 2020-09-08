import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class Solution {
    public static void main(String[] args){
        int[] A = new int[]{1, 2, 4, 5};
        int[] B = new int[]{2, 3, 1, 4};
        int N = 5;
        solution(A, B, N);
    }

    public static int solution(int[] A, int[] B, int N) {
        HashMap<Integer, Integer> nodeHash = new HashMap<>();
        int length = A.length;
        int max = 0;

        for (int i = 0; i < length; i++) {
            if(nodeHash.get(A[i])==null) nodeHash.put(A[i],1);
            else nodeHash.put(A[i],nodeHash.get(A[i])+1);

            if(nodeHash.get(B[i])==null) nodeHash.put(B[i],1);
            else nodeHash.put(B[i],nodeHash.get(B[i])+1);
        }

        for (int i = 0; i < length; i++) {
            int temp = nodeHash.get(A[i]) + nodeHash.get(B[i]);
            if(temp>max) max = temp;
        }
        return max-1;
    }
}