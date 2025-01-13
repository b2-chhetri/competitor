import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Comparator;

/**
 * CompetitorList class manages a collection of competitors and provides methods
 * for analyzing and reporting competitor data.
 * 
 * @author Your Name
 * @version 1.0
 */
public class CompetitorList {
    private List<Competitor> competitors;
    private final DatabaseManager dbManager;
    
    /**
     * Constructs a new CompetitorList object and initializes the database connection
     */
    public CompetitorList() {
        competitors = new ArrayList<>();
        dbManager = new DatabaseManager();
        loadCompetitors();
    }
    
    /**
     * Loads all competitors from the database
     */
    private void loadCompetitors() {
        try {
            competitors = DatabaseManager.getAllCompetitors();
        } catch (SQLException e) {
            System.err.println("Error loading competitors: " + e.getMessage());
        }
    }
    
    /**
     * Gets a competitor by their ID
     * 
     * @param id The competitor's ID
     * @return Optional containing the competitor if found
     */
    public Optional<Competitor> getCompetitorById(int id) {
        return competitors.stream()
                         .filter(c -> c.getCompetitorId() == id)
                         .findFirst();
    }
    
    /**
     * Gets the competitor with the highest overall score
     * 
     * @return Optional containing the top performer if any competitors exist
     */
    public Optional<Competitor> getTopPerformer() {
        return competitors.stream()
                         .max(Comparator.comparingDouble(Competitor::getOverallScore));
    }
    
    /**
     * Calculates the frequency of each possible score
     * 
     * @return Map containing score frequencies (score -> frequency)
     */
    public Map<Integer, Integer> getScoreFrequencies() {
        Map<Integer, Integer> frequencies = new HashMap<>();
        for (Competitor comp : competitors) {
            for (int score : comp.getScoreArray()) {
                frequencies.merge(score, 1, Integer::sum);
            }
        }
        return frequencies;
    }
    
    /**
     * Generates a table report of all competitors
     * 
     * @return Formatted string containing competitor table
     */
    public String generateCompetitorTable() {
        StringBuilder table = new StringBuilder();
        table.append(String.format("%-5s %-20s %-15s %-20s %-10s%n",
            "ID", "Name", "Level", "Scores", "Overall"));
        table.append("-".repeat(75)).append("\n");
        
        for (Competitor comp : competitors) {
            String scoresStr = Arrays.toString(comp.getScoreArray())
                                   .replaceAll("[\\[\\]]", "");
            table.append(String.format("%-5d %-20s %-15s %-20s %-10.1f%n",
                comp.getCompetitorId(),
                comp.getName().getFullName(),
                comp.getLevel(),
                scoresStr,
                comp.getOverallScore()));
        }
        return table.toString();
    }
    
    /**
     * Generates a complete report including all statistics
     * 
     * @return Formatted string containing the full report
     */
    public String generateFullReport() {
        StringBuilder report = new StringBuilder();
        report.append("COMPETITOR MANAGEMENT SYSTEM - FULL REPORT\n")
              .append("=".repeat(50)).append("\n\n");
        
        // Competitor Table
        report.append("COMPETITOR TABLE\n")
              .append(generateCompetitorTable())
              .append("\n");
        
        // Top Performer
        getTopPerformer().ifPresent(top -> {
            report.append("TOP PERFORMER\n")
                  .append("-".repeat(20)).append("\n")
                  .append(top.getFullDetails())
                  .append("\n\n");
        });
        
        // Score Frequencies
        report.append("SCORE FREQUENCIES\n")
              .append("-".repeat(20)).append("\n");
        Map<Integer, Integer> frequencies = getScoreFrequencies();
        report.append("Score:     ");
        for (int i = 0; i <= 5; i++) {
            report.append(String.format("%-8d", i));
        }
        report.append("\nFrequency: ");
        for (int i = 0; i <= 5; i++) {
            report.append(String.format("%-8d", frequencies.getOrDefault(i, 0)));
        }
        report.append("\n");
        
        return report.toString();
    }
    
    /**
     * Adds a new competitor to the list and database
     * 
     * @param competitor The competitor to add
     * @throws SQLException if database operation fails
     */
    public void addCompetitor(Competitor competitor) throws SQLException {
        competitor.saveToDatabase();
        competitors.add(competitor);
    }
    
    /**
     * Gets the total number of competitors
     * 
     * @return The number of competitors in the list
     */
    public int getCompetitorCount() {
        return competitors.size();
    }
}