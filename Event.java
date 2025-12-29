import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;

public class Event {
    private String title;
    private Person paidBy;
    private Double totalCost;
    private boolean equalPricing;
    private HashMap<Person, Double> costPerPerson;

    public Event(String title, Person paidBy, double totalCost, ArrayList<Person> people) {
        this.title = title;
        this.paidBy = paidBy;
        this.totalCost = totalCost;
        this.equalPricing = true;
        this.costPerPerson = new HashMap<>();



        double individualCost = this.totalCost / people.size();

        for (int i = 0; i < people.size(); i++) {
            this.costPerPerson.put(people.get(i), individualCost);
        }

        updateMoneyOwed();
    }

    public Event(String title, Person paidBy, ArrayList<Person> people, ArrayList<Double> costPerPerson, double totalCost) {
        this.title = title;
        this.paidBy = paidBy;
        this.equalPricing = false;
        this.costPerPerson = new HashMap<>();
        this.totalCost = totalCost;

        for (int i = 0; i < people.size(); i++) {
            this.costPerPerson.put(people.get(i), costPerPerson.get(i));
        }

        updateMoneyOwed();
    }

    public void updateMoneyOwed() {
        for (Map.Entry<Person, Double> entry : costPerPerson.entrySet()) {
            Person key = entry.getKey();
            Double value = entry.getValue();

            key.addMoneyOwed(paidBy, value);

        }
    }

    public String toString() {
        return title + " cost a total of " + totalCost + " dollars. " + paidBy.getName() + " made the payment for " + costPerPerson.size() + " people. " +
                (equalPricing ? "It cost " + costPerPerson.get(paidBy) + " per person ": individualCostToString());
    }

    public String individualCostToString() {
        String individualCost = "";

        for (Map.Entry<Person, Double> entry : costPerPerson.entrySet()) {
            String key = entry.getKey().getName();
            Double value = entry.getValue();

            individualCost += "\n" + key + " spent " + value + " dollars here. ";
        }

        return individualCost;
    }
}
