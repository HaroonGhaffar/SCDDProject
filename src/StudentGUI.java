import javax.swing.*;
import java.awt.*;

public class StudentGUI extends JFrame {

    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;

    private JTextArea outputArea;

    private StudentService service;

    public StudentGUI() {

        service = new StudentService();

        setTitle("Student Management System");
        setSize(550, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Labels and Fields

        add(new JLabel("Student ID"));
        idField = new JTextField(15);
        add(idField);

        add(new JLabel("Student Name"));
        nameField = new JTextField(15);
        add(nameField);

        add(new JLabel("Age"));
        ageField = new JTextField(15);
        add(ageField);

        // Buttons

        JButton addButton = new JButton("Add Student");
        JButton searchButton = new JButton("Search Student");
        JButton deleteButton = new JButton("Delete Student");
        JButton saveButton = new JButton("Save File");

        add(addButton);
        add(searchButton);
        add(deleteButton);
        add(saveButton);

        // Output Area

        outputArea = new JTextArea(15, 40);
        outputArea.setEditable(false);

        JScrollPane scrollPane =
                new JScrollPane(outputArea);

        add(scrollPane);

        // Event Handling

        addButton.addActionListener(e -> addStudent());

        searchButton.addActionListener(e -> searchStudent());

        deleteButton.addActionListener(e -> deleteStudent());

        saveButton.addActionListener(e -> saveStudents());
    }

    private void addStudent() {

        try {

            int id =
                    Integer.parseInt(idField.getText());

            String name =
                    nameField.getText().trim();

            int age =
                    Integer.parseInt(ageField.getText());

            if (name.isEmpty()) {
                throw new Exception(
                        "Name cannot be empty!"
                );
            }

            if (age <= 0) {
                throw new Exception(
                        "Age must be greater than 0!"
                );
            }

            Student student =
                    new Student(id, name, age);

            service.addStudent(student);

            outputArea.append(
                    "Student Added: "
                            + student
                            + "\n"
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Student added successfully!"
            );

            clearFields();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "ID and Age must be numbers!"
            );

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage()
            );
        }
    }

    private void searchStudent() {

        try {

            int id =
                    Integer.parseInt(idField.getText());

            Student student =
                    service.searchStudent(id);

            if (student != null) {

                outputArea.append(
                        "Found: "
                                + student
                                + "\n"
                );

            } else {

                outputArea.append(
                        "Student Not Found\n"
                );
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage()
            );
        }
    }

    private void deleteStudent() {

        try {

            int id =
                    Integer.parseInt(idField.getText());

            boolean deleted =
                    service.deleteStudent(id);

            if (deleted) {

                outputArea.append(
                        "Student Deleted Successfully\n"
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Student deleted!"
                );

            } else {

                outputArea.append(
                        "Student Not Found\n"
                );
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage()
            );
        }
    }

    private void saveStudents() {

        try {

            service.saveToFile();

            JOptionPane.showMessageDialog(
                    this,
                    "Students Saved Successfully!"
            );

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "File Error: "
                            + ex.getMessage()
            );
        }
    }

    private void clearFields() {

        idField.setText("");
        nameField.setText("");
        ageField.setText("");
    }
}