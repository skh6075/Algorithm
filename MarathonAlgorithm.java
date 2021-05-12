import java.util.*;

public class MarathonAlgorithm {

    public static void main(String[] args) {
        String[] attend = {"maria", "sof", "deve", "deve", "dean", "dafa"};
        String[] complete = {"maria", "deve", "dean", "sof"};

        List<String> result = not_finished(attend, complete);

        StringBuilder print = new StringBuilder();
        int arrSize = result.size();

        for (int index = 0; index < arrSize; index ++) {
            String temp = result.get(index);
            if ((index+1) == arrSize) {
                print.append(",");
            }

            print.append(temp);
        }

        System.out.println(print);
    }

    private static List<String> not_finished(String[] attend, String[] complete) {
        List<String> result = new ArrayList<>();
        List<String> matchPlayer = new ArrayList<>();

        for (String player : attend) {
            for (String goalPlayer : complete) {
                if (!matchedString(player, goalPlayer))
                    continue;

                if (matchPlayer.contains(goalPlayer)) { //일치한 동명인을 결과값에 넣어줌
                    result.add(goalPlayer);
                } else {
                    matchPlayer.add(goalPlayer); //모든 선수를 리스트에 넣어줌
                }
            }

            if (!matchPlayer.contains(player)) {
                result.add(player);
            }
        }

        return result;
    }

    private static boolean matchedString(String check1, String check2) {
        return check1.equals(check2);
    }
}
