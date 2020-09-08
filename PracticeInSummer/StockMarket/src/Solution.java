class Solution {
//    public static void main(String[] args){
//        int[] prices = new int[]{1, 2, 3, 2, 3};
//
//        int[] result = solution(prices);
//        for(int elem : result){
//            System.out.println(elem);
//        }
//
//    }


    public static int[] solution(int[] prices) {
        int[] answer = new int[prices.length];

        for(int i = 0; i<prices.length; i++){
            for(int j = i+1; j<prices.length; j++){
                if(prices[i] > prices[j]){
                    answer[i] = j-i;
                    break;
                } else if(j == prices.length-1){
                    answer[i] = j-i;
                }
            }
        }



        return answer;
    }
}