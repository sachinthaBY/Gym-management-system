public class Over60Member extends DefaultMember{

    private int age;
    private String occupation;
    private String status;

    public Over60Member(String membershipNo, String firstName, String membershipStartDate, String weight, String height, int age, String occupation, String status) {
        super(membershipNo, firstName, membershipStartDate, weight, height);
        this.age = age;
        this.occupation = occupation;
        this.status = status;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws IllegalAccessException{
        if (age >= 60) {
            this.age = age;
        }else {
            throw new IllegalAccessException("Invalid age!!! You should be over 60");
        }
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
