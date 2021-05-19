import java.util.*;

public class MarathonAlgorithm<result> {

    public static void main(String[] args) {
        String[] attend = {"maria", "sof", "deve", "deve", "dean", "dafa"};
        String[] complete = {"maria", "deve", "dean", "sof"};

        List<String> result = not_finished(attend, complete);

        // not used String.join
        StringBuilder print = new StringBuilder();

        for (int index = 0; index < result.size(); index ++) {
            String temp = result.get(index);
            print.append(temp);
            if ((index + 1) != result.size())
                print.append(",");
        }

        System.out.println(print);
    }

    private static List<String> not_finished(String[] attend, String[] complete) {
        List<String> result = new ArrayList<>();        //결과값을 받기 위해서 변수를 선언함
        List<String> matchPlayer = new ArrayList<>();   //참가자를 넣어줌

        for (String player : attend) {
            for (String goalPlayer : complete) {
                if (!matchedString(player, goalPlayer)) //같지 않을 경우 결과값에 넣어줌
                    continue;

                if (matchPlayer.contains(goalPlayer)) { //일치한 동명인을 결과값에 넣어줌
                    result.add(goalPlayer);
                } else {
                    matchPlayer.add(goalPlayer);        //모든 선수를 리스트에 넣어줌
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
