import java.util.Scanner;
import java.sql.SQLException;

/**
 * Manager class handles user interaction and system operations
 */
public class Manager {
    private CompetitorList competitorList;
    private Scanner scanner;
    
    public Manager() {
        competitorList = new CompetitorList();
        scanner = new Scanner(System.in);
    }
    
    /**
     * Starts the management system and shows the main menu
     */
    public void start() {
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getUserChoice();
            
            try {
                switch (choice) {
                    case 1:
                        displayFullReport();
                        break;
                    case 2:
                        addNewCompetitor();
                        break;
                    case 3:
                        findCompetitor();
                        break;
                    case 4:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (SQLException e) {
                System.err.println("Database error: " + e.getMessage());
            }
            
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }
    
    private void displayMenu() {
        System.out.println("\nCOMPETITOR MANAGEMENT SYSTEM");
        System.out.println("1. View Full Report");
        System.out.println("2. Add New Competitor");
        System.out.println("3. Find Competitor by ID");
        System.out.println("4. Exit");
        System.out.print("Enter your choice (1-4): ");
    }
    
    private int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid number.");
            scanner.next();
        }
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return choice;
    }
    
    private void displayFullReport() {
        System.out.println("\n" + competitorList.generateFullReport());
    }
    
    private void findCompetitor() {
        System.out.print("Enter Competitor ID: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid number.");
            scanner.next();
        }
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        competitorList.getCompetitorById(id)
            .ifPresentOrElse(
                comp -> System.out.println(comp.getShortDetails()),
                () -> System.out.println("No competitor found with ID: " + id)
            );
    }
    
    private void addNewCompetitor() throws SQLException {
        System.out.println("\nADDING NEW COMPETITOR");
        System.out.println("----------------------");
        
        // Get competitor details
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        
        System.out.print("Enter Country: ");
        String country = scanner.nextLine();
        
        System.out.println("Select Level:");
        System.out.println("1. BEGINNER");
        System.out.println("2. INTERMEDIATE");
        System.out.println("3. ADVANCED");
        CompetitionLevel level = getLevel();
        
        int[] scores = getScores();
        
        // Generate new ID (you might want to improve this logic)
        int newId = competitorList.getCompetitorCount() + 300;
        
        // Create and add new competitor
        Competitor newCompetitor = new Competitor(
            newId,
            new Name(firstName, lastName),
            level,
            country,
            scores
        );
        
        competitorList.addCompetitor(newCompetitor);
        System.out.println("Competitor added successfully!");
    }
    
    private CompetitionLevel getLevel() {
        while (true) {
            int choice = getUserChoice();
            switch (choice) {
                case 1: return CompetitionLevel.BEGINNER;
                case 2: return CompetitionLevel.INTERMEDIATE;
                case 3: return CompetitionLevel.ADVANCED;
                default:
                    System.out.println("Invalid choice. Please select 1-3.");
            }
        }
    }
    
    private int[] getScores() {
        int[] scores = new int[5];
        System.out.println("Enter 5 scores (0-5 each):");
        
        for (int i = 0; i < 5; i++) {
            while (true) {
                System.out.printf("Score %d: ", i + 1);
                if (scanner.hasNextInt()) {
                    int score = scanner.nextInt();
                    if (score >= 0 && score <= 5) {
                        scores[i] = score;
                        break;
                    }
                }
                System.out.println("Please enter a valid score (0-5)");
                scanner.nextLine();
            }
        }
        scanner.nextLine(); // consume final newline
        return scores;
    }
    
    public static void main(String[] args) {
        Manager manager = new Manager();
        manager.start();
    }
}