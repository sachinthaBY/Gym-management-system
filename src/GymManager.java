import java.util.List;

public interface GymManager {

    public void addMember(DefaultMember member);
    public boolean deleteMember(String membershipNo);
    public void printMemberList();
    public void sort();
    public void save();
    public int getCount();
    public List<DefaultMember> getMemberList();
    public DefaultMember getMemberByMembershipNo(String membershipNo);
    public List<DefaultMember> getMemberByFirstName(String firstName);
}
