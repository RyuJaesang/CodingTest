public class Solution {
    public static void main(String[] args){

    }


    public static String solution(String new_id) {
        String answer = stage1(new_id);
        return answer;
    }

    private static String stage1(String id) {
        return stage2(id.toLowerCase());
    }

    private static String stage2(String id) {
        return stage3(id.replaceAll("[^a-z0-9-_.]*", ""));
    }

    private static String stage3(String id) {
        return stage4(id.replaceAll("\\.+", "."));
    }

    private static String stage4(String id) {
        if (id.equals(".")){
            return stage5("");
        }
        if (id.charAt(0) == '.'){
            id = id.substring(1);
        }
        if (id.charAt(id.length()-1) == '.'){
            id = id.substring(0, id.length() - 1);
        }
        return stage5(id);
    }

    private static String stage5(String id) {
        if (id.length() == 0) {
            id = "a";
        }
        return stage6(id);
    }

    private static String stage6(String id) {
        if (id.length() >= 16) {
            id = id.substring(0, 15);
        }
        if (id.charAt(id.length()-1) == '.') {
            id = id.substring(0, id.length() - 1);
        }
        return stage7(id);
    }

    private static String stage7(String id) {
        if (id.length() <= 2) {
            char last = id.charAt(id.length()-1);
            while (id.length() < 3) {
                id += last;
            }
        }
        return id;
    }
}
