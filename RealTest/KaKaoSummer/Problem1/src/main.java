import java.lang.reflect.Array;
import java.util.*;
import java.util.ArrayList;

public class main {

    public static void main(String[] args){
        Solution sol = new Solution();

        //Test input
        String expression = "100-200*300-500+20";
        System.out.println(sol.solution(expression));

    }
}

class Solution {

    public long solution(String expression) {
        long answer = 0;
        int tempResult = 0;

        ArrayList<String> expList = new ArrayList<>();
        String number = "";

        for(int i = 0; i<expression.length(); i++){
            if (expression.charAt(i) >= '0' && expression.charAt(i) <= '9') {
                number = number.concat(Character.toString(expression.charAt(i)));
            }
            else {
                String operator = Character.toString(expression.charAt(i));
                expList.add(number);
                expList.add(operator);
                number = "";
            }
        }
        expList.add(number);

        ArrayList<String> tempList = expList;

        String[] posOperator1 = new String[]{"*", "+", "-"};
        String[] posOperator2 = new String[]{"*", "-", "+"};
        String[] posOperator3 = new String[]{"+", "*", "-"};
        String[] posOperator4 = new String[]{"+", "-", "*"};
        String[] posOperator5 = new String[]{"-", "+", "*"};
        String[] posOperator6 = new String[]{"-", "*", "+"};

        //1
        for(int j = 0; j<posOperator1.length; j++) {
            for(int i =0; i<expList.size(); i++){
                if (posOperator1[j].equals("*") && expList.get(i).equals("*")) {
                    int value = Integer.parseInt(expList.get(i - 1)) * Integer.parseInt(expList.get(i + 1));
                    expList.set(i - 1, Integer.toString(value));
                    expList.remove(i);
                    expList.remove(i);
                    i--;
                }
                else if (posOperator1[j].equals("+") && expList.get(i).equals("+")) {
                    int value = Integer.parseInt(expList.get(i - 1)) + Integer.parseInt(expList.get(i + 1));
                    expList.set(i - 1, Integer.toString(value));
                    expList.remove(i);
                    expList.remove(i);
                    i--;
                }
                else if (posOperator1[j].equals("-") && expList.get(i).equals("-")) {
                    int value = Integer.parseInt(expList.get(i - 1)) - Integer.parseInt(expList.get(i + 1));
                    expList.set(i - 1, Integer.toString(value));
                    expList.remove(i);
                    expList.remove(i);
                    i--;
                }
            }
        }
        if(Math.abs(Integer.parseInt(expList.get(0)))> tempResult){
            tempResult = Math.abs(Integer.parseInt(expList.get(0)));
        }

        expList = tempList;
        //2
        for(int j = 0; j<posOperator2.length; j++) {
            for(int i =0; i<expList.size(); i++){
                if (posOperator2[j].equals("*") && expList.get(i).equals("*")) {
                    int value = Integer.parseInt(expList.get(i - 1)) * Integer.parseInt(expList.get(i + 1));
                    expList.set(i - 1, Integer.toString(value));
                    expList.remove(i);
                    expList.remove(i);
                    i--;
                }
                else if (posOperator2[j].equals("+") && expList.get(i).equals("+")) {
                    int value = Integer.parseInt(expList.get(i - 1)) + Integer.parseInt(expList.get(i + 1));
                    expList.set(i - 1, Integer.toString(value));
                    expList.remove(i);
                    expList.remove(i);
                    i--;
                }
                else if (posOperator2[j].equals("-") && expList.get(i).equals("-")) {
                    int value = Integer.parseInt(expList.get(i - 1)) - Integer.parseInt(expList.get(i + 1));
                    expList.set(i - 1, Integer.toString(value));
                    expList.remove(i);
                    expList.remove(i);
                    i--;
                }
            }
        }
        if(Math.abs(Integer.parseInt(expList.get(0)))> tempResult){
            tempResult = Math.abs(Integer.parseInt(expList.get(0)));
        }

        expList = tempList;

        for(int j = 0; j<posOperator3.length; j++) {
            for(int i =0; i<expList.size(); i++){
                if (posOperator3[j].equals("*") && expList.get(i).equals("*")) {
                    int value = Integer.parseInt(expList.get(i - 1)) * Integer.parseInt(expList.get(i + 1));
                    expList.set(i - 1, Integer.toString(value));
                    expList.remove(i);
                    expList.remove(i);
                    i--;
                }
                else if (posOperator3[j].equals("+") && expList.get(i).equals("+")) {
                    int value = Integer.parseInt(expList.get(i - 1)) + Integer.parseInt(expList.get(i + 1));
                    expList.set(i - 1, Integer.toString(value));
                    expList.remove(i);
                    expList.remove(i);
                    i--;
                }
                else if (posOperator3[j].equals("-") && expList.get(i).equals("-")) {
                    int value = Integer.parseInt(expList.get(i - 1)) - Integer.parseInt(expList.get(i + 1));
                    expList.set(i - 1, Integer.toString(value));
                    expList.remove(i);
                    expList.remove(i);
                    i--;
                }
            }
        }
        if(Math.abs(Integer.parseInt(expList.get(0)))> tempResult){
            tempResult = Math.abs(Integer.parseInt(expList.get(0)));
        }


        expList = tempList;
        for(int j = 0; j<posOperator4.length; j++) {
            for(int i =0; i<expList.size(); i++){
                if (posOperator4[j].equals("*") && expList.get(i).equals("*")) {
                    int value = Integer.parseInt(expList.get(i - 1)) * Integer.parseInt(expList.get(i + 1));
                    expList.set(i - 1, Integer.toString(value));
                    expList.remove(i);
                    expList.remove(i);
                    i--;
                }
                else if (posOperator4[j].equals("+") && expList.get(i).equals("+")) {
                    int value = Integer.parseInt(expList.get(i - 1)) + Integer.parseInt(expList.get(i + 1));
                    expList.set(i - 1, Integer.toString(value));
                    expList.remove(i);
                    expList.remove(i);
                    i--;
                }
                else if (posOperator4[j].equals("-") && expList.get(i).equals("-")) {
                    int value = Integer.parseInt(expList.get(i - 1)) - Integer.parseInt(expList.get(i + 1));
                    expList.set(i - 1, Integer.toString(value));
                    expList.remove(i);
                    expList.remove(i);
                    i--;
                }
            }
        }
        if(Math.abs(Integer.parseInt(expList.get(0)))> tempResult){
            tempResult = Math.abs(Integer.parseInt(expList.get(0)));
        }

        expList = tempList;
        for(int j = 0; j<posOperator5.length; j++) {
            for(int i =0; i<expList.size(); i++){
                if (posOperator5[j].equals("*") && expList.get(i).equals("*")) {
                    int value = Integer.parseInt(expList.get(i - 1)) * Integer.parseInt(expList.get(i + 1));
                    expList.set(i - 1, Integer.toString(value));
                    expList.remove(i);
                    expList.remove(i);
                    i--;
                }
                else if (posOperator5[j].equals("+") && expList.get(i).equals("+")) {
                    int value = Integer.parseInt(expList.get(i - 1)) + Integer.parseInt(expList.get(i + 1));
                    expList.set(i - 1, Integer.toString(value));
                    expList.remove(i);
                    expList.remove(i);
                    i--;
                }
                else if (posOperator5[j].equals("-") && expList.get(i).equals("-")) {
                    int value = Integer.parseInt(expList.get(i - 1)) - Integer.parseInt(expList.get(i + 1));
                    expList.set(i - 1, Integer.toString(value));
                    expList.remove(i);
                    expList.remove(i);
                    i--;
                }
            }
        }
        if(Math.abs(Integer.parseInt(expList.get(0)))> tempResult){
            tempResult = Math.abs(Integer.parseInt(expList.get(0)));
        }

        expList = tempList;
        for(int j = 0; j<posOperator6.length; j++) {
            for(int i =0; i<expList.size(); i++){
                if (posOperator6[j].equals("*") && expList.get(i).equals("*")) {
                    int value = Integer.parseInt(expList.get(i - 1)) * Integer.parseInt(expList.get(i + 1));
                    expList.set(i - 1, Integer.toString(value));
                    expList.remove(i);
                    expList.remove(i);
                    i--;
                }
                else if (posOperator6[j].equals("+") && expList.get(i).equals("+")) {
                    int value = Integer.parseInt(expList.get(i - 1)) + Integer.parseInt(expList.get(i + 1));
                    expList.set(i - 1, Integer.toString(value));
                    expList.remove(i);
                    expList.remove(i);
                    i--;
                }
                else if (posOperator6[j].equals("-") && expList.get(i).equals("-")) {
                    int value = Integer.parseInt(expList.get(i - 1)) - Integer.parseInt(expList.get(i + 1));
                    expList.set(i - 1, Integer.toString(value));
                    expList.remove(i);
                    expList.remove(i);
                    i--;
                }
            }
        }
        if(Math.abs(Integer.parseInt(expList.get(0)))> tempResult){
            tempResult = Math.abs(Integer.parseInt(expList.get(0)));
        }

        answer = tempResult;
        return answer;
    }
}