import java.util.ArrayList;

class Solution {
    public static void main(String[] args){
        String a = "2-1x5-4x3+2";

        System.out.println(solution(a));
    }

    static long result = Long.MIN_VALUE;

    public static long solution(String expression) {
        ArrayList<String> inputList = new ArrayList<>();

        for(int i = 0; i<expression.length(); i++){
            inputList.add(Character.toString(expression.charAt(i)));
        }

        for(int start = 0; start <= inputList.size()/2 ; start++){
            for(int end = start; end <= inputList.size()/2 ; end++){
                long temp = calculation(inputList, 2 * start, 2 * end);
                if(result < temp){
                    result = temp;
                }

            }
        }

        long answer = result;


        return answer;
    }

    public static long calculation(ArrayList<String> inputList, int start, int end){
        long result = 0;

        ArrayList<String> inlist = listCopy(inputList, start, end);
        ArrayList<String> outputList = new ArrayList<>();
        // 괄호 안 곱하기 연산
        while(inlist.contains("x")){
            int idx = inlist.indexOf("x");
            String op = inlist.get(idx);
            long a = Long.parseLong(inlist.get(idx-1));
            long b = Long.parseLong(inlist.get(idx+1));
            result = a * b;

            inlist.set(idx-1, String.valueOf(result));
            inlist.remove(idx+1);
            inlist.remove(idx);

        }

        // 괄호 안 더하기 빼기 연산
        while(inlist.size() > 1){
            String op = inlist.get(1);
            long a = Long.parseLong(inlist.get(0));
            long b = Long.parseLong(inlist.get(2));

            if(op.equals("+")){
                result = a+ b;
                inlist.set(0, String.valueOf(result));
                inlist.remove(2);
                inlist.remove(1);
            }
            else if(op.equals("-")){
                result = a - b;
                inlist.set(0, String.valueOf(result));
                inlist.remove(2);
                inlist.remove(1);
            }
        }

        // 괄호 밖 연결
        for(int i = 0; i<start; i++){
            outputList.add(inputList.get(i));
        }
        outputList.add(inlist.get(0));
        for(int i = end+1; i < inputList.size(); i++) {
            outputList.add(inputList.get(i));
        }

        // 총 연산
        while(outputList.contains("x")){
            int idx = outputList.indexOf("x");
            String op = outputList.get(idx);
            long a = Long.parseLong(outputList.get(idx-1));
            long b = Long.parseLong(outputList.get(idx+1));
            result = a * b;

            outputList.set(idx-1, String.valueOf(result));
            outputList.remove(idx+1);
            outputList.remove(idx);

        }

        // 괄호 안 더하기 빼기 연산
        while(outputList.size() > 1){
            String op = outputList.get(1);
            long a = Long.parseLong(outputList.get(0));
            long b = Long.parseLong(outputList.get(2));

            if(op.equals("+")){
                result = a+ b;
                outputList.set(0, String.valueOf(result));
                outputList.remove(2);
                outputList.remove(1);
            }
            else if(op.equals("-")){
                result = a - b;
                outputList.set(0, String.valueOf(result));
                outputList.remove(2);
                outputList.remove(1);
            }
        }

        return Long.parseLong(outputList.get(0));
    }

    public static ArrayList<String> listCopy(ArrayList<String> input, int start, int end){
        ArrayList<String> outputList = new ArrayList<>();
        for(int i = start; i<=end; i++){
            outputList.add(input.get(i));
        }

        return outputList;
    }

    public static void printList(ArrayList<String> input){
        for(int i = 0; i<input.size(); i++){

            System.out.print(input.get(i));
        }
    }
}