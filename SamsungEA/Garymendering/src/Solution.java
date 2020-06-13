import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int test_case = sc.nextInt();
//        solution(sc);

        //////////////////////////////////////////
        for(int i = 1 ; i <= test_case; i++){
            solution(sc);
        }
        ///////////////////
    }

    static int[][] board;
    static int n;
    static int totalSum;
    static int result;

    public static void solution(Scanner sc){
        n = sc.nextInt();
        board = new int[n][n];
        totalSum = 0;
        result = 999999999;

        for(int i = 0 ; i<n; i++){
            for(int j = 0 ; j<n; j++){
                board[i][j] = sc.nextInt();
                totalSum += board[i][j];
            }
        }

        for(int i  =0; i<n-1; i++){
            for(int j = 0 ;j<n-1; j++){
                for(int d1 = 1; d1<n-1; d1++){
                    for(int d2 = 1; d2 < n-1; d2++){
                        //d1, d2 ≥ 1, 1 ≤ x < x+d1+d2 ≤ N, 1 ≤ y-d1 < y < y+d2 ≤ N
                        if (!(j-d1 < 0 && j+d2 > n-1 && i+d1+d2 > n-1)) {
                            calDiff(i,j,d1,d2);
                        }
                    }
                }
            }
        }

        System.out.println(result);

    }


    public static void calDiff(int i, int j, int d1, int d2){
        int localResult = calculateDiff(i, j, d1, d2);
        if (result > localResult) {
            result = localResult;
        }
    }

    public static int calculateDiff(int i, int j, int d1, int d2){
        int a1 = cal1(i, j, d1, d2);
        int a2 = cal2(i, j, d1, d2);
        int a3 = cal3(i, j, d1, d2);
        int a4 = cal4(i, j, d1, d2);
        int a5 = totalSum - (a1 + a2 + a3 + a4);

        ArrayList<Integer> areaList = new ArrayList<>();
        areaList.add(a1);
        areaList.add(a2);
        areaList.add(a3);
        areaList.add(a4);
        areaList.add(a5);

        Collections.sort(areaList);

        int diff = areaList.get(4) - areaList.get(0);
        return diff;

    }

    public static int cal1(int row, int col, int d1, int d2){
        int result = 0;
        for(int i = 0; i < row; i++){
            if(row-d1 <= i){
                d1--;
            }
            for(int j = 0; j <= col + d1; j++){
                result += board[i][j];
            }
        }
        return result;
    }

    public static int cal2(int row, int col, int d1, int d2){
        int result = 0;
        for(int i = n-1; i >= row; i--){
            if(i < row + d2){
                d2--;
            }
            for(int j = 0; j < col + d2; j++){
                result += board[i][j];
            }
        }
        return result;
    }

    public static int cal3(int row, int col, int d1, int d2){
        int result = 0;
        for(int i = 0; i <= row - d1 + d2; i++){
            if(i > row - d1){
                d1++;
            }
            for(int j = n-1; j > col + d1 ; j--){
                result += board[i][j];
            }
        }
        return result;
    }

    public static int cal4(int row, int col, int d1, int d2){
        int result = 0;
        int temp_d2 =d2;
        for(int i = n-1; i > row - d1 + temp_d2; i--){
            if(i <= row + d2){
                d2++;
            }
            for(int j = n-1; j >= col + d2 ; j--){
                result += board[i][j];
            }
        }
        return result;
    }


}

