import java.io.Serializable;

public class DefaultMember implements Comparable<DefaultMember>, Serializable {

    private String membershipNo;
    private String firstName;
    private String membershipStartDate;
    private String weight;
    private String height;

    public DefaultMember(String membershipNo, String firstName, String membershipStartDate, String weight, String height) {
        super();
        this.membershipNo = membershipNo;
        this.firstName = firstName;
        this.membershipStartDate = membershipStartDate;
        this.weight = weight;
        this.height = height;
    }

    public DefaultMember(String membershipNo){

    }

    public String getMembershipNo() {
        return membershipNo;
    }

    public void setMembershipNo(String membershipNo) {
        this.membershipNo = membershipNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMembershipStartDate() {
        return membershipStartDate;
    }

    public void setMembershipStartDate(String membershipStartDate) {
        this.membershipStartDate = membershipStartDate;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    @Override
    public int compareTo(DefaultMember o) {
        return this.firstName.compareTo(o.getFirstName());
    }
}
