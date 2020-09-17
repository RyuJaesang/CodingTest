import java.util.ArrayList;
import java.util.HashMap;

public class Solution {
    public static void main(String[] args){
        String[] companies = new String[]{"A fabdec 2", "B cebdfa 2", "C ecfadb 2"};
        String[] applicants = new String[]{"a BAC 1", "b BAC 3", "c BCA 2", "d ABC 3", "e BCA 3", "f ABC 2"};

        String[] result = solution(companies, applicants);

        for(String elem : result){
            System.out.println(elem);
        }
    }

    static int roundIdx = 0;
    static HashMap<Character, String[]> applicantsInfo;
    static HashMap<Character, String[]> compaiesInfo;

    public static String[] solution(String[] companies, String[] applicants) {
        for(int i = 0; i < applicants.length; i++){
            String[] applicant = applicants[i].split(" ");
            applicantsInfo.put(applicant[0].charAt(0), new String[]{applicant[1], applicant[2]});
        }
        for(int i = 0; i<companies.length; i++){
            String[] company = companies[i].split(" ");
            compaiesInfo.put(company[0].charAt(0), new String[]{company[1], company[2]});
        }

        String[] answer = {};
        ArrayList<String> rejectedList = new ArrayList<>();


        while(!(rejectedList.size() == 0)){




        }

        return answer;
    }

    private static void stage1(){
        HashMap<Character, String> tempCompanyApply = new HashMap<>();

        for(Character key : applicantsInfo.keySet()){
            String[] applicant = applicantsInfo.get(key);

            if(Integer.parseInt(applicant[1]) > roundIdx) {
                Character wantedCompany = applicant[0].charAt(roundIdx);

                String companyApplier = tempCompanyApply.get(wantedCompany);
                companyApplier += String.valueOf(key);

                tempCompanyApply.put(wantedCompany, companyApplier);
            }
        }
        stage2(tempCompanyApply);
    }

    private static void stage2(HashMap<Character, String> tempCompanyApply){
        HashMap<Character, String> accpetedCompanyApply = new HashMap<>();

        for(Character key : tempCompanyApply.keySet()){
            String companyApplier = tempCompanyApply.get(key);
            String companyWant = compaiesInfo.get(key)[0];
            int companyCapacity = Integer.parseInt(compaiesInfo.get(key)[1]);

            StringBuilder acceptedApplier = new StringBuilder();

            for(int i = 0; i < companyWant.length(); i++){
                if(companyCapacity < acceptedApplier.length()) {
                    Character want = companyWant.charAt(i);
                    for (int j = 0; j < companyApplier.length(); j++) {
                        Character applier = companyApplier.charAt(j);
                        if (want == applier) {
                            acceptedApplier.append(applier);
                            break;
                        }
                    }
                }
            }
            accpetedCompanyApply.put(key) acceptedApplier.toString()
        }
    }
}
