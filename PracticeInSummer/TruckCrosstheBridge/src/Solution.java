public class Solution {
//    public static void main(String[] args){
//        int bridge_length = 2;
//        int weight = 10;
//        int[] truck_weights = new int[]{7,4,5,6};
//
//        System.out.println(solution(bridge_length, weight, truck_weights));
//    }

    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int index = 0;
        int answer = 0;
        int[] bridge = new int[bridge_length];

        while(index != truck_weights.length){
            bridge = moveBridge(bridge);
            if(calOnBridge(bridge)+truck_weights[index] <= weight){
                bridge[bridge.length-1] = truck_weights[index];
                index++;
            }
            answer++;

            if(index == truck_weights.length){
                answer += bridge_length;
            }
        }



        return answer;
    }

    public static int[] moveBridge(int[] bridge){
        for(int i = 0; i<bridge.length-1; i++){
            bridge[i] = bridge[i+1];
        }
        bridge[bridge.length-1] = 0;
        return bridge;
    }

    public static int calOnBridge(int[] bridge){
        int sum = 0;
        for(int elem : bridge){
            sum += elem;
        }
        return sum;
    }
}
