// Main.java
import java.sql.SQLException;
import java.util.List;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Competition Management System - Task 2 Test\n");
        
        try {
            // Test 1: Database Connection
            testDatabaseConnection();
            
            // Test 2: Create and Save New Competitor
            testCreateAndSaveCompetitor();
            
            // Test 3: Retrieve All Competitors
            testRetrieveAllCompetitors();
            
        } catch (SQLException e) {
            System.out.println("Database error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void testDatabaseConnection() throws SQLException {
        System.out.println("Test 1: Testing Database Connection");
        System.out.println("-".repeat(50));
        DatabaseManager.testConnection();
        System.out.println("Database connection successful!\n");
    }
    
    private static void testCreateAndSaveCompetitor() throws SQLException {
        System.out.println("Test 2: Creating and Saving New Competitor");
        System.out.println("-".repeat(50));
        
        Name name = new Name("John", "Doe");
        int[] scores = {4, 5, 3, 5, 4};
        Competitor newComp = new Competitor(205, name, CompetitionLevel.INTERMEDIATE, "France", scores);
        
        newComp.saveToDatabase();
        System.out.println("New competitor saved successfully:");
        System.out.println(newComp.getFullDetails());
        System.out.println();
    }
    
    private static void testRetrieveAllCompetitors() throws SQLException {
        System.out.println("Test 3: Retrieving All Competitors");
        System.out.println("-".repeat(50));
        
        List<Competitor> competitors = DatabaseManager.getAllCompetitors();
        
        // Print header
        System.out.printf("%-5s %-20s %-15s %-20s %-10s%n", 
            "ID", "Name", "Level", "Scores", "Overall");
        System.out.println("-".repeat(75));
        
        // Print each competitor
        for (Competitor comp : competitors) {
            String scoresStr = Arrays.toString(comp.getScoreArray())
                                   .replaceAll("[\\[\\]]", "");
            
            System.out.printf("%-5d %-20s %-15s %-20s %-10.1f%n",
                comp.getCompetitorId(),
                comp.getName().getFullName(),
                comp.getLevel(),
                scoresStr,
                comp.getOverallScore());
        }
        System.out.println();
    }
}