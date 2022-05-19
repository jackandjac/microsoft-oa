import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientZero {
    public String findPatientZero(int studentCount, Student[] students){
        int[] infected = new int[studentCount];
        Map<Student, Integer> idxMap = new HashMap<>();
        for(int i = 0; i < studentCount; i++){
            idxMap.put(students[i],i);
        }
        for(Student student:students){
            if(student.students.size() == 0) continue;
            for(Student stu: student.students){
                infected[idxMap.get(stu)]++;
            }
        }
        String res = null;
        for(int i=0; i<infected.length;i++){
            if(infected[i] == 0 && students[i].students.size()>0){
                res = students[i].name;
                break;
            }
        }
        return res;
    }
}
class Student{
    public String name;
    public List<Student> students = new ArrayList<>();
    public Student(){}
}
