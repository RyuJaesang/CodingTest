import java.util.*;

public class Solution {
    public static void main(String[] args) {

        //orders 는 크기가 20 이하, 각 원소는 10글자 이하
        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG","ACDEH"};
        int[] course = {2, 3, 4};

        String[] solution = solution(orders, course);

        for (String s : solution) {
            System.out.println(s);
        }
    }

    static Map<Integer, Map<String, Integer>> map = new HashMap<>();
    public static String[] solution(String[] orders, int[] course) {
        String[] answer = {};

        for (int i=0; i<orders.length; i++) {
            StringBuilder sb = new StringBuilder();
            String order = orders[i];
            char[] menus = order.toCharArray();
            Arrays.sort(menus);
            for (char menu : menus) {
                sb.append(menu);
            }
            orders[i] = sb.toString();
        }

        for (int elem : course) {
            map.put(elem, new HashMap<>());
            for (String order : orders) {
                if (order.length() < elem) continue;
                char[] menuList = order.toCharArray();
                boolean[] visited = new boolean[menuList.length];
                permutation(visited, menuList, 0,0, elem);
            }
        }

        Map<Integer, Integer> courseCountMap = new HashMap<>();
        for (Map.Entry<Integer, Map<String, Integer>> allCases : map.entrySet()) {
            int courseCount = allCases.getKey();

            int max = 0;
            for (Integer elem : allCases.getValue().values()) {
                max = Math.max(max, elem);
            }
            courseCountMap.put(courseCount, max);
        }

        List<String> answerList = new ArrayList<>();

        for (Map.Entry<Integer, Map<String, Integer>> allCourse : map.entrySet()) {
            int courseSize = allCourse.getKey();
            int popularCourse = courseCountMap.get(courseSize);
            Map<String, Integer> courses = allCourse.getValue();
            for (Map.Entry<String, Integer> courseEntry : courses.entrySet()) {
                if (courseEntry.getValue() == popularCourse && popularCourse >= 2) {
                    answerList.add(courseEntry.getKey());
                }
            }
        }

        Collections.sort(answerList);
        answer = new String[answerList.size()];
        for (int i=0; i<answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }

    private static void permutation(boolean[] visited, char[] menuList, int start, int current, int n) {
        if (current == n) {
            String newCourse = makeCourse(visited, menuList);
            Map<String, Integer> courseMap = map.get(n);
            courseMap.put(newCourse, courseMap.getOrDefault(newCourse, 0) + 1);
            return;
        }

        for(int i=start; i<menuList.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                current++;
                permutation(visited, menuList, i+1, current, n);
                current--;
                visited[i] = false;
            }
        }
    }

    private static String makeCourse(boolean[] visited, char[] menuList) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<menuList.length; i++) {
            if (visited[i]) {
                sb.append(menuList[i]);
            }
        }
        return sb.toString();
    }
}
