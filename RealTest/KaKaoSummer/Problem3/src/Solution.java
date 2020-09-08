import java.util.ArrayList;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        solution(sc);
    }

    static ArrayList<Double> numList;

    public static void solution(Scanner sc){
        int n = sc.nextInt();
        int k = sc.nextInt();

        double min = 100000000;
        double totalSum = 0;

        numList = new ArrayList<>();

        for(int i = 0 ; i<n; i++){
            double num = sc.nextDouble();
            numList.add(num);

            if(min > num){
                min = num;
            }
            totalSum += num;
        }
        ////// EOP /////////////////////////////////

        double th;

        if((totalSum / n) < min){
            th = totalSum / k;
        }
        else{
            th = min;
        }

        double result = 0;

        // Threshold 기준으로 브루트포스 시작
        while(true){
            if(checkProfit(th, k) == true){
                result = th;
                break;
            }
            th -= 0.01;
            // 중요!!!! 이거 꼭 100.0으로 나눠야해 안그럼 자바는 int로 바뀌더라 나 이거 해결하는데만 20분씀 ㅠ
            th = Math.round(th*100)/100.0;
        }

        System.out.println(result);

    }

    public static boolean checkProfit(double th, int k){
        int count = 0;

        for(int i  = 0; i<numList.size(); i++){
            count += numList.get(i) / th;
        }
        if(count == k){
            return true;
        }
        else{
            return false;
        }
    }
















}
