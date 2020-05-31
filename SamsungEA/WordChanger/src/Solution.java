import java.util.ArrayList;
import java.util.Collections;

class WordChanger{
    public static void main(String[] args){
        String begin = "hit";
        String target = "cog";

        String[] word1 = new String[]{"hot", "dot", "dog", "lot", "log"};
        String[] word2 = new String[]{"hot", "dot", "dog", "lot", "log", "cog"};

        Solution sol = new Solution();

        System.out.println("Answer : " + sol.solution(begin, target, word1));
    }
}

class Solution {
    static ArrayList<Integer> resultList = new ArrayList<>();
    public int solution(String begin, String target, String[] words) {

        ArrayList<Boolean> wordCheck = new ArrayList<>();
        for(int i = 0; i<words.length; i++){
            wordCheck.add(false);
        }
        dfs(begin, target, 0, wordCheck, words);

        int answer;
        if(resultList.isEmpty()){
            answer = 0;
        }
        else{
            Collections.sort(resultList);
            answer = resultList.get(0);
        }

        return answer;
    }

    public static void dfs(String begin, String target, int count, ArrayList<Boolean> wordCheck, String[] words){
        if(target.equals(begin)){
            resultList.add(count);
        }
        else if((wordCheck.contains(false))){
            for(int i = 0; i< words.length; i++){
                if(!wordCheck.get(i)){
                    int diffCount = 0;
                    for (int j = 0; j < words[0].length(); j++) {
                        if (begin.charAt(j) != words[i].charAt(j)) {
                            diffCount++;
                        }
                    }
                    if (diffCount == 1) {
                        wordCheck.set(i, true);
                        dfs(words[i], target, count+1, wordCheck, words);
                        wordCheck.set(i, false);
                    }
                }
            }
        }
    }
}