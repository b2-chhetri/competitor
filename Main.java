package assestment1;

public class Main {
    public static void main(String[] args) {
        // Create Name objects
        Name name1 = new Name("Alice", "Green");
        Name name2 = new Name("Bob", "Brown");
        Name name3 = new Name("Carol", "White");
        Name name4 = new Name("David", "Black");

        // Create Competitor objects
        Competitor competitor1 = new Competitor(200, name1, "Beginner", "UK");
        Competitor competitor2 = new Competitor(201, name2, "Intermediate", "USA");
        Competitor competitor3 = new Competitor(202, name3, "Advanced", "Canada");
        Competitor competitor4 = new Competitor(203, name4, "Advanced", "Australia");

        // Test methods
        System.out.println(competitor1.getFullDetails());
        System.out.println(competitor1.getShortDetails());

        System.out.println(competitor2.getFullDetails());
        System.out.println(competitor2.getShortDetails());

        System.out.println(competitor3.getFullDetails());
        System.out.println(competitor3.getShortDetails());

        System.out.println(competitor4.getFullDetails());
        System.out.println(competitor4.getShortDetails());
    }
}
