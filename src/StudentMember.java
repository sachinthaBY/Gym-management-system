public class StudentMember extends DefaultMember{

    private String schoolName;
    private String grade;
    private String nicAvailability;

    public StudentMember(String membershipNo, String firstName, String membershipStartDate, String weight, String height, String schoolName, String grade, String nicAvailability) {
        super(membershipNo, firstName, membershipStartDate, weight, height);
        this.schoolName = schoolName;
        this.grade = grade;
        this.nicAvailability = nicAvailability;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getNicAvailability() {
        return nicAvailability;
    }

    public void setNicAvailability(String nicAvailability) {
        this.nicAvailability = nicAvailability;
    }
}
