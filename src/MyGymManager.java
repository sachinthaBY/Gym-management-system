import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MyGymManager implements GymManager, Serializable {

    private List<DefaultMember> memberList = new ArrayList<DefaultMember>();

    @Override
    public void addMember(DefaultMember member){

        System.out.println("Number of members : " + (memberList.size()+1));
        System.out.println("Number of free spaces : " + (99 - memberList.size()));
        System.out.println("-----------------------------------");

        if (memberList.size()<100) {
            memberList.add(member);

        }else {
            System.out.println("Sorry no any slots available for a new member.");
        }
    }

    @Override
    public boolean deleteMember(String membershipNo){

        boolean gym = false;
        for (DefaultMember member:memberList){
            if (member.getMembershipNo().equals(membershipNo)){
                gym = true;
                memberList.remove(member);
                System.out.println(membershipNo + " Successfully removed");
                System.out.println("Number of members : " + (memberList.size()));
                System.out.println("Number of free spaces : " + (100 - memberList.size()));

                if (member instanceof StudentMember){
                    System.out.println("Member type is : Student Member");
                } else if (member instanceof Over60Member) {
                    System.out.println("Member type is : Over 60 Member");
                }else {
                    System.out.println("Member type is : Default Member");
                }
                System.out.println("--------------------------------------");
                break;
            }
        }
        if (!gym){
            System.out.println(membershipNo + " is not found");
        }
        return gym;
    }

    @Override
    public void printMemberList(){

        for (DefaultMember member:memberList){
            System.out.println("Membership Number : " + member.getMembershipNo() + " ");
            if (member instanceof StudentMember){
                System.out.println("Member type is : Student Member");
            } else if (member instanceof Over60Member) {
                System.out.println("Member type is : Over 60 Member");
            }else {
                System.out.println("Member type is : Default Member");
            }

            System.out.println("First name is " + member.getFirstName() + " ");
            System.out.println("Membership start date is : " + member.getMembershipStartDate());
        }

        if (memberList.size()==0){
            System.out.println("The member list is empty!");
        }
    }

    void merge(DefaultMember arr[], int l, int m, int r){

        // find the sizes of two sub arrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        // create temporary arrays
        DefaultMember L[] = new DefaultMember[n1];
        DefaultMember R[] = new DefaultMember[n2];

        // copy data to temporary arrays
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        // merge the temporary arrays

        // initial indexes of first and second sub arrays
        int i = 0, j = 0;

        // initial index of merged sub array
        int k = l;
        while (i < n1 && j < n2){

            if (L[i].getFirstName().compareToIgnoreCase(R[j].getFirstName()) < 0){
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // copy remaining elements of L[] if any
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // copy remaining elements of R[] if any
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // main function that sorts arr[l..r] using merge()
    void msort(DefaultMember arr[], int l, int r){

        if (l < r) {
            // find the middle point
            int m = (l + r) / 2;

            // sort first and second halves
            msort(arr, l, m);
            msort(arr, m + 1, r);

            // merge the sorted halves
            merge(arr, l, m, r);
        }
    }

    @Override
    public void sort(){
        DefaultMember[] arr = new DefaultMember[memberList.size()];
        memberList.toArray(arr);
        msort(arr, 0, memberList.size()-1);
        memberList.clear();
        for (DefaultMember m : arr){
            memberList.add(m);
        }
    }

    @Override
    public void save(){

        try {
            FileOutputStream fileOut = new FileOutputStream("members.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.println("Serialized data saved in members.ser");

        } catch (FileNotFoundException f) {
            f.printStackTrace();

        }catch (IOException i) {
            i.printStackTrace();
        }
    }

    @Override
    public int getCount(){
        return memberList.size();

    }

    @Override
    public List<DefaultMember> getMemberList(){
        return this.memberList;
    }

    @Override
    public DefaultMember getMemberByMembershipNo(String membershipNo){

        DefaultMember found = null;
        for (int i = 0; i < memberList.size(); i++){
            DefaultMember current = memberList.get(i);
            if (current.getMembershipNo().equals(membershipNo)){
                found = current;
            }
        }
        return found;
    }

    @Override
    public List<DefaultMember> getMemberByFirstName(String firstName){

        List<DefaultMember> found = new ArrayList<>();
        for (int i = 0; i < memberList.size(); i++) {
            DefaultMember current = memberList.get(i);
            if (current.getFirstName().equalsIgnoreCase(firstName)){
                found.add(current);
            }
        }
        return found;
    }
}
