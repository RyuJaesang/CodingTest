import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class main2 {

    public static void main(String[] args){
        Solution2 sol = new Solution2();

        int[][] land = {{1, 2}, {2, 3}};
        int p = 3;
        int q = 2;

        long result = sol.solution(land, p, q);
        System.out.println(result);
    }
}

class Solution2 {
    public long solution(int[][] land, int P, int Q) {
        int maxHeight=0;
        int minHeight=1000000000;

        int row = land.length;
        int col = land[0].length;

        // Make land array Hash Map
        for(int i =0 ; i<row; i++){
            for(int j = 0; j<col; j++){
                if(maxHeight < land[i][j]){
                    maxHeight = land[i][j];
                }
                if(minHeight > land[i][j]){
                    minHeight = land[i][j];
                }
            }
        }

        long midHeightCost, midplusHeightCost;
        long cost = 0;
        int midHeight;

        while(true){
//            System.out.println("Max height : " + maxHeight);
//            System.out.println("Min height : " + minHeight);

            midHeight = (maxHeight + minHeight) /2;

            midHeightCost = calCost(midHeight, land, P, Q);
            midplusHeightCost = calCost(midHeight + 1, land, P, Q);

            // add to Cost Array List
            if(midplusHeightCost < midHeightCost){
                cost = midplusHeightCost;
            }
            else{
                cost = midHeightCost;
            }

            // Escape
            if(minHeight == maxHeight || Math.abs(minHeight-maxHeight) == 1 || midHeightCost == midplusHeightCost){
                break;
            }

            // set Next height value
            if(midplusHeightCost < midHeightCost){
                minHeight = midHeight;
            }
            else{
                maxHeight = midHeight;
            }
        }

        long answer = cost;
        return answer;

    }

    public long calCost(int height, int[][] land, int P, int Q){
        long cost = 0;
        for(int i = 0; i < land.length; i++){
            for(int j = 0; j<land[0].length; j++){
                long diff = height - land[i][j];
                if(diff > 0){
                    cost += (diff * P);
                }
                else{
                    cost += ((-1 * diff) * Q);
                }
            }
        }

        return cost;
    }
}