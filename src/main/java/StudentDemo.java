import command.Commands;
import command.UserCommands;
import model.Lesson;
import model.Role;
import model.Student;
import model.User;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import storage.LessonStorage;
import storage.StudentStorage;
import storage.UserStorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

import static util.DateUtil.stringToDate;

public class StudentDemo implements Commands, UserCommands {
    private static Scanner scanner = new Scanner(System.in);
    private static StudentStorage studentStorage = new StudentStorage();
    private static LessonStorage lessonStorage = new LessonStorage();
    private static UserStorage userStorage = new UserStorage();
    private static User currentUser = null;

    public static void main(String[] args) {
        initData();
        boolean run = true;
        while (run) {
            Commands.printLoginCommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case EXIT:
                    run = false;
                    break;
                case LOGIN:
                    login();
                    break;
                case REGISTER:
                    register();
                    break;
                default:
                    System.out.println("Invalid command, please try again");
            }
        }
    }

    private static void initData() {
        Lesson java = new Lesson("java", "Galstyan", 5, 5000, stringToDate("12/03/2022"));
        lessonStorage.add(java);
        User admin = new User("admin", "admin", "admin@gmail.com", "admin", Role.ADMIN);
        userStorage.add(admin);
        studentStorage.add(new Student("Tigran", "Tigranyan", 18, "099", "Yerevan", java, admin, new Date()));
    }

    private static void login() {
        System.out.println("Please input email,password");
        String emilPasswordStr = scanner.nextLine();
        String[] emailPassword = emilPasswordStr.split(",");
        User user = userStorage.getUserByEmailAndPassword(emailPassword[0]);
        if (user == null) {
            System.out.println("User does not exist");
        } else {
            if (!user.getPassword().equals(emailPassword[1])) {
                System.out.println("Password is incorrect");
            } else {
                currentUser = user;
                if (user.getRole() == Role.ADMIN) {
                    adminLogin();
                } else if (user.getRole() == Role.USER) {
                    userLogin();
                }
            }
        }
    }

    private static void register() {
        System.out.println("Please input name,surname,email,password");
        String userDataStr = scanner.nextLine();
        String[] userData = userDataStr.split(",");
        if (userData.length < 4) {
            System.out.println("Please input correct user data");
        } else {
            if (userStorage.getUserByEmailAndPassword(userData[2]) == null) {
                User user = new User();
                user.setName(userData[0]);
                user.setSurname(userData[1]);
                user.setEmail(userData[2]);
                user.setPassword(userData[3]);
                user.setRole(Role.USER);
                userStorage.add(user);
                System.out.println("You are registered");
            } else {
                System.out.println("User with " + userData[2] + " already exists");
            }
        }
    }

    private static void adminLogin() {
        System.out.println("Welcome " + currentUser.getName());
        boolean run = true;
        while (run) {
            Commands.printAdminCommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case LOGOUT:
                    currentUser = null;
                    run = false;
                    break;
                case ADD_STUDENT:
                    addStudent();
                    break;
                case PRINT_ALL_STUDENTS:
                    studentStorage.print();
                    break;
                case DOWNLOAD_STUDENTS_EXCEL:
                    downloadStudentsExcel();
                    break;
                case READING_FROM_EXCEL:
                    readingFromExcel();
                    break;
                case PRINT_STUDENTS_COUNT:
                    System.out.println(studentStorage.getSize());
                    break;
                case DELETE_STUDENT_BY_INDEX:
                    deleteStudentByIndex();
                    break;
                case PRINT_STUDENTS_BY_LESSON:
                    printStudentByLesson();
                    break;
                case CHANGE_STUDENT_LESSON:
                    changeLesson();
                    break;
                case ADD_LESSON:
                    addLesson();
                    break;
                case PRINT_ALL_LESSONS:
                    lessonStorage.print();
                    break;
                default:
                    System.out.println("Invalid command, please try again");
            }
        }
    }


    private static void downloadStudentsExcel() {
        System.out.println("Please input the location of the file");
        String fileDir = scanner.nextLine();
        try {
            studentStorage.writeStudentsToExcel(fileDir);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void userLogin() {
        System.out.println("Welcome " + currentUser.getName());
        boolean run = true;
        while (run) {
            UserCommands.printUserCommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case LOGOUT:
                    currentUser = null;
                    run = false;
                    break;
                case ADD_STUDENT:
                    addStudent();
                    break;
                case PRINT_ALL_STUDENTS:
                    studentStorage.print();
                    break;
                case DOWNLOAD_STUDENTS_EXCEL:
                    downloadStudentsExcel();
                    break;
                case READING_FROM_EXCEL:
                    readingFromExcel();
                    break;
                case PRINT_STUDENTS_COUNT:
                    System.out.println(studentStorage.getSize());
                    break;
                case PRINT_STUDENT_BY_LESSON:
                    printStudentByLesson();
                    break;
                case PRINT_LESSONS:
                    lessonStorage.print();
                    break;
                default:
                    System.out.println("Invalid command, please try again");
            }
        }
    }

    private static void readingFromExcel() {
        try {
            System.out.println("Please input the filepath");
            String fileLocation = scanner.nextLine();
            studentStorage.readfromExcel(fileLocation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addStudent() {
        if (lessonStorage.getSize() == 0) {
            System.out.println("Please add the lesson");
            addLesson();
        } else {
            lessonStorage.print();
            System.out.println("Please choose the index of the lesson");
            try {
                int lessonIndex = Integer.parseInt(scanner.nextLine());
                Lesson lesson = lessonStorage.getLessonByIndex(lessonIndex);
                if (lesson == null) {
                    System.out.println("Please input correct index");
                    addStudent();
                } else {
                    System.out.println("Please input the student's name");
                    String name = scanner.nextLine();

                    System.out.println("Please input the student's surname");
                    String surname = scanner.nextLine();

                    System.out.println("Please input the student's age");
                    int age = Integer.parseInt(scanner.nextLine());

                    System.out.println("Please input the student's phoneNumber");
                    String phoneNumber = scanner.nextLine();

                    System.out.println("Please input the student's city");
                    String city = scanner.nextLine();

                    Student student = new Student(name, surname, age, phoneNumber, city, lesson, currentUser, new Date());
                    studentStorage.add(student);
                    System.out.println("Thank you, student was added");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please input only a number");
                addStudent();
            }
        }
    }

    private static void deleteStudentByIndex() {
        try {
            studentStorage.print();
            System.out.println("Please choose student's index");
            int index = Integer.parseInt(scanner.nextLine());
            studentStorage.delete(index);
        } catch (NumberFormatException e) {
            System.out.println("Please input only a number");
            deleteStudentByIndex();
        }
    }

    private static void printStudentByLesson() {
        System.out.println("Please input the lesson name");
        String lessonName = scanner.nextLine();
        studentStorage.printStudentsByLesson(lessonName);
    }

    private static void changeLesson() {
        studentStorage.print();
        System.out.println("Please select the index");
        try {
            int newIndex = Integer.parseInt(scanner.nextLine());
            Student student = studentStorage.getStudentByIndex(newIndex);
            if (student != null) {
                lessonStorage.print();
                System.out.println("Please choose lesson index");
                try {
                    int lessonIndex = Integer.parseInt(scanner.nextLine());
                    Lesson lesson = lessonStorage.getLessonByIndex(lessonIndex);
                    if (lesson != null) {
                        student.setLesson(lesson);
                        System.out.println("Lesson changed");
                    } else {
                        System.out.println("Please input correct index");
                        changeLesson();
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please input only a number");
                    changeLesson();
                }
            } else {
                System.out.println("Invalid index, please try again");
                changeLesson();
            }
        } catch (NumberFormatException e) {
            System.out.println("Please input only a number");
            changeLesson();
        }
    }

    private static void addLesson() {
        System.out.println("Please input the name of the lasson");
        String name = scanner.nextLine();

        System.out.println("Please input the name of the teacher");
        String teacherName = scanner.nextLine();

        System.out.println("Please input the lasson duration by month");
        int duration = Integer.parseInt(scanner.nextLine());

        System.out.println("PLease input the price of the lesson");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.println("Please input the date the subject started");
        String StrDate = scanner.nextLine();

        Lesson lesson = new Lesson(name, teacherName, duration, price, stringToDate(StrDate));
        lessonStorage.add(lesson);
        System.out.println("The lesson was created");
    }
}



