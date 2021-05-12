import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TimeCheckAlgorithm {

    private static final String SPLIT = " ~ ";

    public static void main(String[] args) {
        String[] input1 = {"10:50 ~ 12:00",
                           "11:00 ~ 12:00",
                           "09:00 ~ 16:00",
                           "11:50 ~ 14:00"};

        String[] input2 = {"10:50 ~ 12:00",
                           "13:00 ~ 16:00",
                           "09:00 ~ 16:00",
                           "10:00 ~ 16:00",
                           "11:50 ~ 14:00"};

        String[] input3 = {"10:50 ~ 17:00",
                           "13:00 ~ 16:00",
                           "09:00 ~ 13:30",
                           "10:00 ~ 21:00",
                           "11:50 ~ 13:00"};

        String result = switch ((new Scanner(System.in)).nextInt()) {
            case 1 -> searchMatchedTime(input1);
            case 2 -> searchMatchedTime(input2);
            case 3 -> searchMatchedTime(input3);
            default -> "-1";
        };

        System.out.println(result);
    }

    private static String searchMatchedTime(String[] input) {
        boolean noMeat = false;

        List<String> startTime = new ArrayList<>();
        List<String> endTime = new ArrayList<>();
        List<String> alreadyTime = new ArrayList<>();

        for (String s : input) {
            String[] time = s.split(SPLIT); // 0 -> 10:00 1 -> 11:00
            startTime.add(time[0]);
            endTime.add(time[1]);
        }

        int pos2hour = 24;
        int pos2minute = 60;
        for (String value : endTime) {
            String[] temp = value.split(":");
            int tempHour = Integer.parseInt(temp[0]);
            int tempMinute = Integer.parseInt(temp[1]);

            alreadyTime.add(value); // 모든 시간을 넣어줌

            if (pos2hour > tempHour && pos2minute > tempMinute) {
                pos2hour = tempHour;
                pos2minute = tempMinute;
            }
        }

        int pos1hour = -1, pos1minute = -1;
        for (String s : startTime) {
            String[] temp = s.split(":");
            int tempHour = Integer.parseInt(temp[0]);
            int tempMinute = Integer.parseInt(temp[1]);

            if (!alreadyTime.contains(s)) { //일치한 시간이 없을 경우
                if (pos1hour < tempHour) {
                    pos1hour = tempHour;
                }

                if (pos1hour == tempHour && pos1minute < tempMinute) {
                    pos1minute = tempMinute;
                }
            } else {
                // 최대로 만날 수 있는 시간일 경우 그대로 출력하고 반복을 끝냄
                pos1hour = pos2hour = tempHour;
                pos1minute = pos2minute = tempMinute;
                break;
            }
        }

        if (pos1hour > pos2hour) {
            noMeat = true;
        } else if (pos1hour == pos2hour && pos1minute > pos2minute) {
            noMeat = true;
        }

        String resultStartHour = String.valueOf(pos1hour);
        String resultStartMinute = convertMinute(pos1minute);

        String resultEndHour = String.valueOf(pos2hour);
        String resultEndMinute = convertMinute(pos2minute);

        return noMeat ? "-1" : resultStartHour + ":" + resultStartMinute + " ~ " + resultEndHour + ":" + resultEndMinute;
    }

    private static String convertMinute(int minute) {
        String temp = String.valueOf(minute);
        return temp.length() == 1 ? temp + "0" : temp;
    }
}
