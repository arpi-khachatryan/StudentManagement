package command;

public interface UserCommands extends Commands {
    int PRINT_STUDENT_BY_LESSON = 4;
    int PRINT_LESSONS = 5;

    static void printUserCommands() {
        System.out.println("Please input " + LOGOUT + " for logout");
        System.out.println("Please input " + ADD_STUDENT + " to add student");
        System.out.println("Please input " + PRINT_ALL_STUDENTS + " to print the student/students");
        System.out.println("Please input " + PRINT_STUDENTS_COUNT + " to print the number of students");
        System.out.println("Please input " + PRINT_STUDENT_BY_LESSON + " to print the student/students by lesson");
        System.out.println("Please input " + PRINT_LESSONS + " to print all lessons");
        System.out.println("Please input " + DOWNLOAD_STUDENTS_EXCEL + " to download students into exel");
    }
}


