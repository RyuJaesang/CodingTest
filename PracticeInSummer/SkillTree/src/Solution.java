class Solution {
    public static void main(String[] args){
        String skill = "CBD";
        String[] skill_trees = new String[]{"BACDE", "CBADF", "AECB", "BDA"};

        System.out.println(solution(skill, skill_trees));
    }

    public static int solution(String skill, String[] skill_trees) {
        int answer = 0;

        for(String skill_tree : skill_trees){
            int skill_index = 0;
            boolean tf = true;

            for(int i = 0; i<skill_tree.length(); i++){
                if(skill.contains(Character.toString(skill_tree.charAt(i)))){
                    if(skill.charAt(skill_index) == skill_tree.charAt(i)){
                        skill_index++;
                    } else{
                        tf = false;
                        break;
                    }
                }
            }
            if(tf){
                answer++;
            }

        }

        return answer;
    }
}