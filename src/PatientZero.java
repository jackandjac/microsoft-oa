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
/*
A class has many children and there is a widespread flu infection.
Some of them are infected and some are not.
A child can infect many other children.
A child who has infected other children knows who they have infected.
The children who got infected do not know who they were infected by.
Find patient zero.

public class PatientZero {
    public String findPatientZero(int studentCount, Student[] students){
      // write you excellent code here
    }
}
class Student{
    public String name;
    public List<Student> infectedStudents = new ArrayList<>();
    public Student(){}
}

*
* */