import java.util.ArrayList;
import java.util.Scanner;

public class FrontEnd {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Outing outing = intro();

        System.out.println("Calculating how much you owe eachother!!");

        System.out.println(outing);


    }

    public static Outing intro() {

        System.out.println("Welcome to Pay Split!! An easy way to calculate how much money a person owes to someone else after a day spent together.");
        System.out.println("The first step is to enter all the people present through the day. Please enter each name on a new line, and enter twice when all names are down.\n");

        ArrayList<Person> peopleInOuting = addPeople();

        System.out.print("\nOk! Moving on... ");
        System.out.print("The second step is to add events. How many events would you like to add? ");
        int count = input.nextInt();
        input.nextLine();
        System.out.println();

        ArrayList<Event> events = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            events.add(addEvent(i + 1, peopleInOuting));
        }

        Outing thisOuting = new Outing(events, peopleInOuting);

        return thisOuting;

    }

    public static Event addEvent(int eventNum, ArrayList<Person> possiblePeople) {
        System.out.print("This is event number " + eventNum + ". What is the name of the event? ");
        String title = input.nextLine();

        Person payer = null;

        while (payer == null) {

            System.out.print("\nWho paid for " + title.toLowerCase() + "? ");
            String paidBy = input.nextLine();


            for (int i = 0; i < possiblePeople.size(); i++) {
                if (possiblePeople.get(i).getName().equalsIgnoreCase(paidBy)) {
                    payer = possiblePeople.get(i);
                    break;
                }
            }

            if (payer == null) {
                System.out.println("Oops! Looks like this person was not at the event! Please double check your spelling."); //make own method for recursion
            }
        }

        System.out.print("\nHow much did " + payer.getName() + " pay? ");
        double totalPayment = input.nextDouble();
        input.nextLine();

        System.out.print("\nDid " + title.toLowerCase() + " cost the same for each person? ");
        boolean evenSplit = input.nextLine().equalsIgnoreCase("yes");
        System.out.println();

        ArrayList<Person> peopleAtEvent = new ArrayList<>();
        ArrayList<Double> costPerPerson = new ArrayList<>();

        for (int i = 0; i < possiblePeople.size(); i++) {
            System.out.print("Did " + possiblePeople.get(i).getName() + " attend " + title.toLowerCase() + "? ");
            if (input.nextLine().equalsIgnoreCase("yes")) {
                peopleAtEvent.add(possiblePeople.get(i));

                if (!evenSplit) {
                    System.out.print("How much did " + possiblePeople.get(i).getName() + " spend at " + title.toLowerCase() + "? ");
                    costPerPerson.add(input.nextDouble());
                    input.nextLine();
                }
            }
        }

        System.out.println();
        System.out.print("That wraps up this event!\n");

        if (evenSplit) return new Event(title, payer, totalPayment, peopleAtEvent);

        return new Event(title, payer, peopleAtEvent, costPerPerson, totalPayment);
    }
    
    public static ArrayList<Person> addPeople() {
        ArrayList<Person> peopleInOuting = new ArrayList<>();

        String name = input.nextLine();

        while (!name.equals("")) {
            peopleInOuting.add(new Person(name, null));
            name = input.nextLine();
        }

        System.out.print("Great! Would you like to add payment methods for each of the people added? ");

        if (input.nextLine().equalsIgnoreCase("Yes")) {
            System.out.println("\nPlease type out the preferred payment method of each of the given people: ");
            for (int i = 0; i < peopleInOuting.size(); i++) {
                System.out.print(peopleInOuting.get(i).getName() + ": ");
                peopleInOuting.get(i).setPaymentMethod(input.nextLine());
            }
        }

        return peopleInOuting;
    }
}
