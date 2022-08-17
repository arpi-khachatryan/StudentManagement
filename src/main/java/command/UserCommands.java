package command;

public interface UserCommands extends Commands {
    int PRINT_STUDENT_BY_LESSON = 6;
    int PRINT_LESSONS = 7;

    static void printUserCommands() {
        System.out.println("Please input " + LOGOUT + " for logout");
        System.out.println("Please input " + ADD_STUDENT + " to add student");
        System.out.println("Please input " + PRINT_ALL_STUDENTS + " to print the student/students");
        System.out.println("Please input " + DOWNLOAD_STUDENTS_EXCEL + " to download student data into an excel");
        System.out.println("Please input " + READING_FROM_EXCEL + " to read from excel");
        System.out.println("Please input " + PRINT_STUDENTS_COUNT + " to print the number of students");
        System.out.println("Please input " + PRINT_STUDENT_BY_LESSON + " to print the student/students by lesson");
        System.out.println("Please input " + PRINT_LESSONS + " to print all lessons");
    }
}


