import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("================================");
        System.out.println("||                            ||");
        System.out.println("||  STUDENT GRADE CALCULATOR  ||");
        System.out.println("||                            ||");
        System.out.println("================================");
        System.out.println("Enter number of subjects: ");
        int n = s.nextInt();
        s.nextLine();

        String[] subjects = new String[n];
        int[] marks = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter subject " + (i + 1) + " name: ");
            subjects[i] = s.nextLine();

            System.out.print("Enter marks obtained in " + subjects[i] + ": ");
            marks[i] = s.nextInt();
            s.nextLine();
        }

        int total = 0;
        for (int mark : marks) {
            total += mark;
        }

        double avg = (double) total / n;
        String grade = calculateGrade(avg);

        System.out.println("\n------------------------");
        System.out.println("|   Subject   |  Marks |");
        System.out.println("------------------------");
        for (int i = 0; i < n; i++) {
            System.out.printf("| %-11s | %6d |\n", subjects[i], marks[i]);
        }
        System.out.println("------------------------");
        System.out.printf(" Total Marks --------->  %d\n", total);
        System.out.printf(" Average Percentage -->  %.2f%%\n", avg);
        System.out.println(" Grade --------------->  " + grade);
    }

    public static String calculateGrade(double avg) {
        if (avg >= 90) {
            return "A+";
        } else if (avg >= 80) {
            return "A";
        } else if (avg >= 70) {
            return "B";
        } else if (avg >= 60) {
            return "C";
        } else if (avg >= 50) {
            return "D";
        } else {
            return "Fail";
        }
    }
}
