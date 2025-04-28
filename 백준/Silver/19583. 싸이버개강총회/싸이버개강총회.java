import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String startTime = scanner.next();
        String endTime = scanner.next();
        String streamingEndTime = scanner.next();

        Set<String> enteredStudents = new HashSet<>();
        Set<String> attendedStudents = new HashSet<>();

        while (scanner.hasNext()) {
            String chatTime = scanner.next();
            String studentName = scanner.next();

            if (chatTime.compareTo(startTime) <= 0) {
                enteredStudents.add(studentName);
            } else if (chatTime.compareTo(endTime) >= 0 && chatTime.compareTo(streamingEndTime) <= 0) {
                if (enteredStudents.contains(studentName)) {
                    attendedStudents.add(studentName);
                }
            }
        }

        System.out.println(attendedStudents.size());
    }
}
