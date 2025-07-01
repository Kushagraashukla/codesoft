import java.io.*;
import java.util.*;

class Student {
    private String name;
    private String rollNumber;
    private String grade;
    private String email;

    public Student(String name, String rollNumber, String grade, String email) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.email = email;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return "Name: " + name + ", Roll: " + rollNumber + ", Grade: " + grade + ", Email: " + email;
    }

    public String toFileString() {
        return name + "," + rollNumber + "," + grade + "," + email;
    }

    public static Student fromFileString(String line) {
        String[] parts = line.split(",");
        if (parts.length == 4) {
            return new Student(parts[0], parts[1], parts[2], parts[3]);
        }
        return null;
    }
}

class StudentManagementSystem {
    private List<Student> students = new ArrayList<>();
    private final String fileName = "students.txt";

    public StudentManagementSystem() {
        loadFromFile();
    }

    public void addStudent(Student student) {
        students.add(student);
        saveToFile();
        System.out.println("Student added successfully.");
    }

    public void removeStudent(String rollNumber) {
        Student toRemove = null;
        for (Student s : students) {
            if (s.getRollNumber().equals(rollNumber)) {
                toRemove = s;
                break;
            }
        }

        if (toRemove != null) {
            students.remove(toRemove);
            saveToFile();
            System.out.println("Student removed.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public void editStudent(String rollNumber, String newGrade, String newEmail) {
        for (Student s : students) {
            if (s.getRollNumber().equals(rollNumber)) {
                s.setGrade(newGrade);
                s.setEmail(newEmail);
                saveToFile();
                System.out.println("Student information updated.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public void searchStudent(String rollNumber) {
        for (Student s : students) {
            if (s.getRollNumber().equals(rollNumber)) {
                System.out.println(s);
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    private void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            for (Student s : students) {
                pw.println(s.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    private void loadFromFile() {
        students.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                Student s = Student.fromFileString(line);
                if (s != null) {
                    students.add(s);
                }
            }
        } catch (IOException e) {
            // File may not exist on first run. That’s okay.
        }
    }
}

public class Task5_StudentManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem();

        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Edit Student");
            System.out.println("4. Search Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            System.out.print("Choose option (1-6): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine().trim();
                    System.out.print("Enter Roll Number: ");
                    String roll = scanner.nextLine().trim();
                    System.out.print("Enter Grade: ");
                    String grade = scanner.nextLine().trim();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine().trim();

                    if (name.isEmpty() || roll.isEmpty() || grade.isEmpty() || email.isEmpty()) {
                        System.out.println("All fields are required.");
                    } else {
                        Student newStudent = new Student(name, roll, grade, email);
                        sms.addStudent(newStudent);
                    }
                    break;

                case 2:
                    System.out.print("Enter Roll Number to remove: ");
                    String rollToRemove = scanner.nextLine();
                    sms.removeStudent(rollToRemove);
                    break;

                case 3:
                    System.out.print("Enter Roll Number to edit: ");
                    String rollToEdit = scanner.nextLine();
                    System.out.print("Enter new Grade: ");
                    String newGrade = scanner.nextLine();
                    System.out.print("Enter new Email: ");
                    String newEmail = scanner.nextLine();
                    sms.editStudent(rollToEdit, newGrade, newEmail);
                    break;

                case 4:
                    System.out.print("Enter Roll Number to search: ");
                    String rollToSearch = scanner.nextLine();
                    sms.searchStudent(rollToSearch);
                    break;

                case 5:
                    sms.displayAllStudents();
                    break;

                case 6:
                    System.out.println("Exiting system.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
