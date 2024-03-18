import javafx.application.Application;

import java.io.*;
import java.util.Scanner;

public class ConsoleUI {

    private MyGymManager manager = null;
    private HomeGUI homeGUI = null;

    ConsoleUI(){
        this.manager = new MyGymManager();
        try {
            FileInputStream fileIn = new FileInputStream("members.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            this.manager = (MyGymManager) in.readObject();
            in.close();
            fileIn.close();
        }catch (FileNotFoundException f){
            f.printStackTrace();
        }catch (IOException i){
            i.printStackTrace();
        }catch (ClassNotFoundException c){
            System.out.println("MyGymManager class not found");
            c.printStackTrace();
        }
    }

    public static void main(String[] args){

        boolean exit = true;
        ConsoleUI myConsole = new ConsoleUI();
        do {
            Scanner menu = new Scanner(System.in);
            System.out.println("=====================================");
            System.out.println("------- Gym Management System -------");
            System.out.println("=====================================");
            System.out.println(" ");
            System.out.println("Enter 1 to insert a member : ");
            System.out.println("Enter 2 to delete a member : ");
            System.out.println("Enter 3  to print member list : ");
            System.out.println("Enter 4 to save : ");
            System.out.println("Enter 5 to sort : ");
            System.out.println("Enter 6 to view table : ");
            System.out.println("Enter 7 to exit : ");

            int guess = menu.nextInt();

            switch (guess){
                case 1:
                    myConsole.insertMember();
                    break;
                case 2:
                    myConsole.deleteMember();
                    break;
                case 3:
                    myConsole.getManager().printMemberList();
                    break;
                case 4:
                    myConsole.getManager().save();
                    break;
                case 5:
                    myConsole.getManager().sort();
                    break;
                case 6:
                    myConsole.showHomeGUI();
                    break;
                case 7:
                    exit = false;
                    break;

                default:
                    System.out.println("Input is invalid!");
            }
        }while (exit != false);
    }

    public void showHomeGUI() {

        HomeGUI.setManager(getManager());
        new Thread(() -> Application.launch(HomeGUI.class)).start();
    }

    public MyGymManager getManager() {
        return this.manager;
    }

    private void deleteMember() {

        Scanner menu = new Scanner(System.in);
        System.out.println("Enter the membership number : ");
        String membershipNo = menu.next();
        boolean result = manager.deleteMember(membershipNo);
    }

    private void insertMember() {

        Scanner menu = new Scanner(System.in);
        if (manager.getCount() < 100){

            System.out.println("Enter the membership number : ");
            String membershipNo = menu.next();

            System.out.println("Enter the name : ");
            String firstName = menu.next();

            System.out.println("Enter the date in the following format DD/MM/YYYY : ");
            String membershipStartDate = menu.next();

            System.out.println("Enter the weight(kg) : ");
            String weight = menu.next();

            System.out.println("Enter the height(cm) : ");
            String height = menu.next();

            System.out.println("Enter the type of membership(D-Default member/S-School member/O-Over 60 member) : ");
            String model = menu.next();

            DefaultMember member = null;

            switch (model){

                case "D":
                case "d":
                    member = new DefaultMember(membershipNo, firstName, membershipStartDate, weight, height);
                    break;

                case "S":
                case "s":
                    System.out.println("Enter member's school name : ");
                    String schoolName = menu.next();

                    System.out.println("Enter member's grade : ");
                    String grade = menu.next();

                    System.out.println("Enter member's NIC number : ");
                    String nicAvailability = menu.next();

                    member = new StudentMember(membershipNo, firstName, membershipStartDate, weight, height, schoolName, grade, nicAvailability);
                    break;

                case "O":
                case "o":
                    System.out.println("Enter member's age : ");
                    int age = menu.nextInt();

                    System.out.println("Enter member's occupation : ");
                    String occupation = menu.next();

                    System.out.println("Enter member's marriage status(married or unmarried) : ");
                    String status = menu.next();

                    member = new Over60Member(membershipNo, firstName, membershipStartDate, weight, height, age, occupation, status);
                    break;

                default:
                    System.out.println("Input is invalid!");
            }
            manager.addMember(member);
        }else {
            System.out.println("Sorry, can't add a new member. No any spaces left!");
        }
    }


}
