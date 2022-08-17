package command;

public interface Commands {
    int LOGOUT = 0;
    int ADD_STUDENT = 1;
    int PRINT_ALL_STUDENTS = 2;
    int PRINT_STUDENTS_COUNT = 3;
    int DOWNLOAD_STUDENTS_EXCEL = 4;
    int READING_FROM_EXCEL = 5;
    int DELETE_STUDENT_BY_INDEX = 6;
    int PRINT_STUDENTS_BY_LESSON = 7;
    int CHANGE_STUDENT_LESSON = 8;
    int ADD_LESSON = 9;
    int PRINT_ALL_LESSONS = 10;


    int EXIT = 0;
    int LOGIN = 1;
    int REGISTER = 2;

    static void printLoginCommands() {
        System.out.println("Please input " + EXIT + " for exit");
        System.out.println("Please input " + LOGIN + " for login");
        System.out.println("Please input " + REGISTER + " for register");
    }

    static void printAdminCommands() {
        System.out.println("Please input " + LOGOUT + " for logout");
        System.out.println("Please input " + ADD_STUDENT + " to add student");
        System.out.println("Please input " + PRINT_ALL_STUDENTS + " to print the student/students");
        System.out.println("Please input " + PRINT_STUDENTS_COUNT + " to print the number of students");
        System.out.println("Please input " + DOWNLOAD_STUDENTS_EXCEL + " to download student data into an excel");
        System.out.println("Please input " + READING_FROM_EXCEL + " to read from excel");
        System.out.println("Please input " + DELETE_STUDENT_BY_INDEX + " to delete the student by index");
        System.out.println("Please input " + PRINT_STUDENTS_BY_LESSON + " to print the student/students by lesson");
        System.out.println("Please input " + CHANGE_STUDENT_LESSON + " to change the students's lesson");
        System.out.println("Please input " + ADD_LESSON + " to add the students's lesson");
        System.out.println("Please input " + PRINT_ALL_LESSONS + " to print  all lessons");
    }
}
