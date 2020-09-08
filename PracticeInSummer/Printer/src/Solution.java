import java.util.*;
import java.util.stream.Collectors;

public class Solution {
	public static void main(String[] args) {
		int[] priorities = new int[]{8,1,1,8,1,1,9};
		int location = 2;

		int answer = solution(priorities, location);
		System.out.println("The answer is " + answer);
	}

	public static int solution(int[] priorities, int location) {
		Queue<Integer> listPriorities = new LinkedList<>(Arrays.stream(priorities).boxed().collect(Collectors.toList()));
		int answer = 1;

		while(!(listPriorities.size() == 1)){
			int j = listPriorities.poll();

			if(j >= Collections.max(listPriorities)){
				if(location == 0){
					return answer;
				}
				else{
					answer++;
				}
			}
			else{
				listPriorities.add(j);
			}
			if(--location < 0){
				location = listPriorities.size()-1;
			}
		}
		return answer;
	}
}
