import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class StudentManagementSystem extends JFrame {
    private List<Student> students;
    private JTextField nameField, rollNumberField, gradeField, emailField, addressField, searchField;
    private JTextArea displayArea;

    private class Student {
        private String name;
        private int rollNumber;
        private String grade;
        private String email;
        private String address;

        public Student(String name, int rollNumber, String grade, String email, String address) {
            this.name = name;
            this.rollNumber = rollNumber;
            this.grade = grade;
            this.email = email;
            this.address = address;
        }

        @Override
        public String toString() {
            return "Name: " + name + "\n" +
                    "Roll Number: " + rollNumber + "\n" +
                    "Grade: " + grade + "\n" +
                    "Email: " + email + "\n" +
                    "Address: " + address;
        }
    }

    public StudentManagementSystem() {
        students = new ArrayList<>();

        setTitle("STUDENT MANAGEMENT SYSTEM");
        setSize(400, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel titleLabel = new JLabel("STUDENT MANAGEMENT SYSTEM", SwingConstants.CENTER);
        titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        titleLabel.setBounds(50, 10, 300, 30);
        add(titleLabel);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 50, 80, 30);
        add(nameLabel);
        nameField = new JTextField();
        nameField.setBounds(150, 50, 200, 30);
        add(nameField);

        JLabel rollLabel = new JLabel("Roll Number:");
        rollLabel.setBounds(50, 90, 80, 30);
        add(rollLabel);
        rollNumberField = new JTextField();
        rollNumberField.setBounds(150, 90, 200, 30);
        add(rollNumberField);

        JLabel gradeLabel = new JLabel("Grade:");
        gradeLabel.setBounds(50, 130, 80, 30);
        add(gradeLabel);
        gradeField = new JTextField();
        gradeField.setBounds(150, 130, 200, 30);
        add(gradeField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 170, 80, 30);
        add(emailLabel);
        emailField = new JTextField();
        emailField.setBounds(150, 170, 200, 30);
        add(emailField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(50, 210, 80, 30);
        add(addressLabel);
        addressField = new JTextField();
        addressField.setBounds(150, 210, 200, 30);
        add(addressField);

        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setBounds(50, 250, 80, 30);
        add(searchLabel);
        searchField = new JTextField();
        searchField.setBounds(150, 250, 200, 30);
        add(searchField);

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });
        addButton.setBounds(60, 290, 130, 30);
        add(addButton);

        JButton deleteButton = new JButton("Delete Student");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteStudent();
            }
        });
        deleteButton.setBounds(200, 290, 130, 30);
        add(deleteButton);

        JButton updateButton = new JButton("Update Student");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStudent();
            }
        });
        updateButton.setBounds(60, 330, 130, 30);
        add(updateButton);

        JButton displayButton = new JButton("Display All");
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAllStudents();
            }
        });
        displayButton.setBounds(200, 330, 130, 30);
        add(displayButton);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchStudent();
            }
        });
        searchButton.setBounds(60, 370, 130, 30);
        add(searchButton);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        resetButton.setBounds(200, 370, 130, 30);
        add(resetButton);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBounds(50, 410, 300, 80);
        add(scrollPane);
    }

    private void addStudent() {
        String name = nameField.getText();
        int rollNumber = Integer.parseInt(rollNumberField.getText());
        String grade = gradeField.getText();
        String email = emailField.getText();
        String address = addressField.getText();
        Student student = new Student(name, rollNumber, grade, email, address);
        students.add(student);
        displayArea.append("Student added:\n" + student.toString() + "\n\n");
        clearFields();
    }

    private void deleteStudent() {
        int rollNumber = Integer.parseInt(rollNumberField.getText());
        Student studentToRemove = null;
        for (Student student : students) {
            if (student.rollNumber == rollNumber) {
                studentToRemove = student;
                break;
            }
        }
        if (studentToRemove != null) {
            students.remove(studentToRemove);
            displayArea.append("Student removed:\n" + studentToRemove.toString() + "\n\n");
        } else {
            displayArea.append("Student with roll number " + rollNumber + " not found.\n\n");
        }
        clearFields();
    }

    private void updateStudent() {
        int rollNumber = Integer.parseInt(rollNumberField.getText());
        for (Student student : students) {
            if (student.rollNumber == rollNumber) {
                student.name = nameField.getText();
                student.grade = gradeField.getText();
                student.email = emailField.getText();
                student.address = addressField.getText();
                displayArea.append("Student updated:\n" + student.toString() + "\n\n");
                clearFields();
                return;
            }
        }
        displayArea.append("Student with roll number " + rollNumber + " not found.\n\n");
    }

    private void displayAllStudents() {
        if (students.isEmpty()) {
            displayArea.append("No students in the system.\n\n");
        } else {
            displayArea.append("List of Students:\n");
            for (Student student : students) {
                displayArea.append(student.toString() + "\n\n");
            }
        }
    }

    private void searchStudent() {
        String searchText = searchField.getText();
        boolean found = false;
        if (!searchText.isEmpty()) {
            displayArea.setText("");
            for (Student student : students) {
                if (student.name.toLowerCase().contains(searchText.toLowerCase()) ||
                        String.valueOf(student.rollNumber).contains(searchText) ||
                        student.grade.toLowerCase().contains(searchText.toLowerCase()) ||
                        student.email.toLowerCase().contains(searchText.toLowerCase()) ||
                        student.address.toLowerCase().contains(searchText.toLowerCase())) {
                    displayArea.append("Search Result:\n" + student.toString() + "\n\n");
                    found = true;
                }
            }
            if (!found) {
                displayArea.append("No matching records found.\n\n");
            }
        } else {
            displayArea.append("Please enter search criteria.\n\n");
        }
    }

    private void clearFields() {
        nameField.setText("");
        rollNumberField.setText("");
        gradeField.setText("");
        emailField.setText("");
        addressField.setText("");
        searchField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StudentManagementSystem().setVisible(true);
            }
        });
    }
}
