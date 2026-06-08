import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class StudentService {

    private ArrayList<Student> students = new ArrayList<>();

    public void addStudent(Student student) throws Exception {

        for (Student s : students) {

            if (s.getId() == student.getId()
                    && s.getName().equalsIgnoreCase(student.getName())
                    && s.getAge() == student.getAge()) {

                throw new Exception(
                        "Duplicate student not allowed!"
                );
            }
        }

        students.add(student);
    }

    public Student searchStudent(int id) {

        for (Student student : students) {

            if (student.getId() == id) {
                return student;
            }
        }

        return null;
    }

    public boolean deleteStudent(int id) {

        Student student = searchStudent(id);

        if (student != null) {
            students.remove(student);
            return true;
        }

        return false;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void saveToFile() throws IOException {

        BufferedWriter writer =
                new BufferedWriter(new FileWriter("students.txt"));

        for (Student student : students) {

            writer.write(
                    student.getId() + "," +
                            student.getName() + "," +
                            student.getAge()
            );

            writer.newLine();
        }

        writer.close();
    }
}