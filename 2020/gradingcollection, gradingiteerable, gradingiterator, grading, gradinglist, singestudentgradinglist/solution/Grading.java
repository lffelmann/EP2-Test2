// This class represents a grading (Zeugnis) with matriculationID, name of the course, grade and
// the date (as a string) when the grading was issued.
public class Grading {

    private int matriculationID;
    private String course;
    private int grade;
    private String issueDate;

    // Initializes this 'Grading'.
    public Grading(int matriculationID, String course, int grade, String issueDate) {
        this.matriculationID = matriculationID;
        this.course = course;
        this.grade = grade;
        this.issueDate = issueDate;
    }

    @Override
    public String toString() {
        return "Matrikel#: " + matriculationID + ", LV: " + course + ", Note: " + grade + ", Datum: " + issueDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != Grading.class) return false;
        Grading Obj = (Grading) obj;
        if (matriculationID == Obj.matriculationID && course.equals(Obj.course) && grade == Obj.grade && issueDate.equals(Obj.issueDate))
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        int code = matriculationID + grade;
        for (int i = 0; i < course.length(); i++)
            code += (int) course.charAt(i);
        for (int i = 0; i < issueDate.length(); i++)
            code += (int) issueDate.charAt(i);
        return code;
    }

    public int getMatriculationID() {
        return matriculationID;
    }
}
