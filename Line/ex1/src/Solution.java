import java.util.Arrays;

class Solution {
    public static void main(String[] args){
        int[][] boxes = new int[][]{{1, 2}, {2, 1}, {3, 3}, {4, 5}, {5, 6}, {7, 8}};

        System.out.println(solution(boxes));
    }

    public static int solution(int[][] boxes) {
        int[] itemList = new int[boxes.length * boxes[0].length];

        for(int i = 0 ; i < boxes.length; i++){
            for(int j = 0; j < boxes[0].length; j++){
                itemList[(boxes[0].length * i) + j] = boxes[i][j];
            }
        }
        Arrays.sort(itemList);

        int equalCount = 0;
        for(int i = 0 ; i<itemList.length-1; i++){
            if(itemList[i] == itemList[i+1]){
                itemList[i] = 0;
                itemList[i+1] = 0;
                equalCount++;
                i++;
            }
        }

        int answer = boxes.length-equalCount;
        return answer;
    }
}