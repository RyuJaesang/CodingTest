import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Solution {

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        int test_case = sc.nextInt();

        for(int i = 1 ;i<= test_case; i++){
            solution(sc, i);
        }
    }

    static ArrayList<Integer> inputList;
    static int n;

    public static void solution(Scanner sc, int test_case){
        inputList = new ArrayList<>();

        n = sc.nextInt();

        for(int i = 0; i<n; i++){
            inputList.add(sc.nextInt());
        }

        Collections.sort(inputList);
//        printList(inputList);

        inputList = swapList(inputList);

        int result = countResult(inputList);

        System.out.println("# " + test_case + " " + result);
    }

    public static int countResult(ArrayList<Integer> inputList){
        int count = 0;

        for(int i = 0; i<inputList.size()-1; i++){
            if(inputList.get(i) < inputList.get(i+1)){
                count++;
            }
        }

        return count;
    }

    public static ArrayList<Integer> swapList(ArrayList<Integer> inputList){
        for(int i = 0; i<inputList.size()-2; i++){
            if(inputList.get(i) == inputList.get(i+1)){
                int temp = inputList.get(i+1);
                inputList.set(i+1, inputList.get(i+2));
                inputList.set(i+2, temp);
            }
        }

        return inputList;
    }

    public static void printList(ArrayList<Integer> inputList){
        for(int i = 0 ;i<inputList.size(); i++){
            System.out.print(inputList.get(i) + " ");
        }
    }
}
