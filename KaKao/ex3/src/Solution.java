import java.util.HashMap;
import java.util.Map;

class Solution {
    public static void main(String[] args){
        String[] info = new String[]{"java backend junior pizza 150","python frontend senior chicken 210"
                ,"python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};

        String[] query = new String[]{"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250",
                "- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};

        int[] result = solution(info, query);
        for(int i = 0; i<result.length; i++){
            System.out.println(result[i]);
        }
    }

    public static int[] solution(String[] info, String[] query) {
        Map<Integer, String[]> infoList = initInfoList(info);
        Map<Integer, String[]> queryList = initQueryList(query);

        int[] answer = new int[query.length];

        for(int i = 0; i<query.length; i++){
            int eachQueryAnswer = 0;

            for (int j = 0; j < infoList.size(); j++) {
                if (isFitToQuery(queryList.get(i), infoList.get(j))) {
                    eachQueryAnswer++;
                }
            }
            answer[i] = eachQueryAnswer;
        }

        return answer;
    }

    private static Map<Integer, String[]> initQueryList(String[] query){
        Map<Integer, String[]> queryList = new HashMap<>();
        for(int i = 0; i<query.length; i++) {
            String[] queryTokenList = new String[5];
            String[] tempList = query[i].split(" and ");

            for (int j = 0; j < 3; j++) {
                queryTokenList[j] = tempList[j];
            }
            queryTokenList[3] = tempList[3].split(" ")[0];
            queryTokenList[4] = tempList[3].split(" ")[1];

            queryList.put(i, queryTokenList);
        }

        return queryList;
    }

    private static Map<Integer, String[]> initInfoList(String[] info){
        Map<Integer, String[]> infoList = new HashMap<>();

        for(int i = 0; i<info.length; i++){
            String[] personInfo = info[i].split(" ");
            infoList.put(i, personInfo);
        }
        return infoList;
    }

    private static boolean isFitToQuery(String[] queryTokenList, String[] info){
        if(queryTokenList[0].equals(info[0]) || queryTokenList[0].equals("-")){
            if(queryTokenList[1].equals(info[1]) || queryTokenList[1].equals("-")){
                if(queryTokenList[2].equals(info[2]) || queryTokenList[2].equals("-")){
                    if(queryTokenList[3].equals(info[3]) || queryTokenList[3].equals("-")){
                        if(Integer.parseInt(queryTokenList[4]) <= Integer.parseInt(info[4])){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}