import java.util.HashMap;
import java.util.Map;

public class Person {
    private String name;
    private String paymentMethod;
    private HashMap<Person, Double> moneyOwed;

    public Person(String name, String paymentMethod) {
        this.name = name;
        this.paymentMethod = paymentMethod;
        this.moneyOwed = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public boolean addMoneyOwed(Person otherPerson, double money) {
        if (this.equals(otherPerson)) {
            return false;
        }

        double newMoney = money + (moneyOwed.get(otherPerson) == null ? 0 : moneyOwed.get(otherPerson));

        moneyOwed.put(otherPerson, newMoney);

        return true;
    }

    public double getRawMoneyOwed(Person otherPerson) {
        //System.out.println(moneyOwed.get(otherPerson));

        return moneyOwed.get(otherPerson);
    }

    public double calculateFinalMoneyOwedTo(Person otherPerson) {
        //System.out.println(this.getRawMoneyOwed(otherPerson) - otherPerson.getRawMoneyOwed(this));
        Double otherOweThis = otherPerson.moneyOwed.getOrDefault(this, null);

        if (otherOweThis == null) {
            return this.getRawMoneyOwed(otherPerson);
        }

        return this.getRawMoneyOwed(otherPerson) - otherPerson.getRawMoneyOwed(this);
    }

    public Person[] getOtherPeople() {
        return (Person[]) moneyOwed.keySet().toArray();
    }

    public void setPaymentMethod(String url) {
        paymentMethod = url;
    }

    public String toString() {
        return "This is " + name + (paymentMethod != null ? ". They accept payment through " + paymentMethod : ". ");
    }

    public String howMuchOwedString() {
        String owed = name + " owes: ";

        for (Person entry : moneyOwed.keySet()) {
            Double value = calculateFinalMoneyOwedTo(entry);
            if (value <= 0) continue;

            owed += entry.getName() + " " + value + " dollars, ";
        }

        owed = owed.substring(0, owed.length() - 2) + ".";

        if (owed.equals(name + " owes.")) return name + " does not owe anyone!";

        return owed;
    }
}
