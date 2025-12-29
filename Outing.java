import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collection;

public class Outing {
    private ArrayList<Event> events;
    private ArrayList<Person> people;


    public Outing(ArrayList<Event> events, ArrayList<Person> people) {
        this.events = events;
        this.people = people;
    }

    public HashMap<Person, Double> calculateFinalMoneyOwedByIndividually(Person person) {
        HashMap<Person, Double> finalMoneyOwed = new HashMap<>();
        Person[] allPeople = person.getOtherPeople();
        for (int i = 0; i < allPeople.length; i++) {
            finalMoneyOwed.put(allPeople[i], person.getRawMoneyOwed(allPeople[i]) - allPeople[i].getRawMoneyOwed(person));
        }

        return finalMoneyOwed;
    }

    public String toString() {

        String allPeople = "All people at outing: ";

        for (int i = 0; i < people.size(); i++) {
            allPeople += people.get(i).getName() + ", ";
        }

        allPeople = allPeople.substring(0, allPeople.length() - 2);

        String allEvents = "There were a total of " + events.size() + " events in this outing.\n";

        for (int i = 0; i < events.size(); i++) {
            allEvents += (i + 1) + ". " + events.get(i) + "\n";
            System.out.println();
        }

        String moneyOwed = "Total transactions: \n";

        for (int i = 0; i < people.size(); i++) {
            moneyOwed += people.get(i).howMuchOwedString() + "\n";
        }

        return allPeople + "\n\n" + allEvents + "\n" + moneyOwed;
    }



}
