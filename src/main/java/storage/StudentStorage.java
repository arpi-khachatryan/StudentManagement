package storage;

import model.Student;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class StudentStorage {

    private Student[] array = new Student[10];
    private int size = 0;

    public void add(Student st) {
        if (size == array.length) {
            increaseArray();

        }
        array[size++] = st;
    }

    private void increaseArray() {
        Student[] newArray = new Student[array.length + 10];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(i + ". " + array[i]);
        }
    }

    public int getSize() {
        return size;
    }

    public void delete(int index) {
        if (index >= 0 && index < size) {
            for (int i = index; i < size; i++) {
                array[i] = array[++i];
            }
            size--;
            System.out.println("student is deleted");
        } else {
            System.out.println("Index out of bounds");
        }
    }

    public void printStudentsByLesson(String lessonName) {
        for (int i = 0; i < size; i++) {
            if (array[i].getLesson().getName().equals(lessonName)) {
                System.out.println(array[i]);
            }
        }
    }

    public Student getStudentByIndex(int index) {
        if (index >= 0 && index < size) {
            return array[index];
        }
        return null;
    }

    public void writeStudentsToExcel(String fileDir) throws Exception {
        File directory = new File(fileDir);
        if (directory.isFile()) {
            throw new RuntimeException("File must be a directory");
        }
        File excelFile = new File(directory, "student_" + System.currentTimeMillis() + ".XLSX");
        excelFile.createNewFile();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Students");
        Row headerRow = sheet.createRow(0);

        Cell nameCell = headerRow.createCell(0);
        nameCell.setCellValue("name");

        Cell surnameCell = headerRow.createCell(1);
        surnameCell.setCellValue("surname");

        Cell ageCell = headerRow.createCell(2);
        ageCell.setCellValue("age");

        Cell phoneNumberCell = headerRow.createCell(3);
        phoneNumberCell.setCellValue("phoneNumber");

        for (int i = 0; i < size; i++) {
            Student student = array[i];
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(student.getName());
            row.createCell(1).setCellValue(student.getSurname());
            row.createCell(2).setCellValue(student.getAge());
            row.createCell(3).setCellValue(student.getPhoneNumber());
        }
        workbook.write(new FileOutputStream(excelFile));
        System.out.println("Excel was created successfully");
    }
}