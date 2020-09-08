class Solution {
    public static void main(String[] args){
        int[] numbers = new int[]{1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
        String hand = "right";

        System.out.println(solution2(numbers, hand));
    }

    public static String solution1(int n) {
        String answer = "";
        if(n%2 == 0){
            for(int i = 0; i< n/2; i++){
                answer += "수박";
            }
        } else{
            for(int i = 0; i< n/2; i++){
                answer += "수박";
            }
            answer += "수";
        }

        return answer;
    }

    public static String solution2(int[] numbers, String hand) {
        String answer = "";

        for(int  : numbers){
            if(elem == 0){
                elem = 11;
            }
        }

        int curL = 10;
        int curR = 12;

        for(int i = 0; i < numbers.length; i++){
            if(numbers[i] == 1 ||  numbers[i] == 4 || numbers[i] == 7){
                answer += "L";
                curL = numbers[i];
            } else if (numbers[i] == 3 ||  numbers[i] == 6 || numbers[i] == 9){
                answer += "R";
                curR = numbers[i];
            } else{
                int distL = (Math.abs(curL - numbers[i]) % 3) + (Math.abs(curL - numbers[i]) / 3);
                int distR = (Math.abs(curR - numbers[i]) % 3) + (Math.abs(curR - numbers[i]) / 3);

                if(distL > distR){
                    answer += "R";
                    curR = numbers[i];
                } else if(distL < distR){
                    answer += "L";
                    curL = numbers[i];
                } else{
                    if(hand.equals("right")){
                        answer += "R";
                        curR = numbers[i];
                    } else{
                        answer += "L";
                        curL = numbers[i];
                    }
                }
            }
        }


        return answer;
    }

}