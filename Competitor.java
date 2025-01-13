// Competitor.java
import java.sql.*;
import java.util.Arrays;
import java.sql.*;
import java.util.Arrays;

public class Competitor {
    private int competitorId;
    private Name name;
    private CompetitionLevel level;
    private String country;
    private int[] scores;
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/CompetitionDB?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String USER = "root";  // Change if different
    private static final String PASS = "Sadhana@0112";      // Add your password
    
    // Constructor
    public Competitor(int competitorId, Name name, CompetitionLevel level, String country, int[] scores) {
        this.competitorId = competitorId;
        this.name = name;
        this.level = level;
        this.country = country;
        setScores(scores);
    }
    
    // Getter methods
    public int getCompetitorId() {
        return competitorId;
    }
    
    public Name getName() {
        return name;
    }
    
    public CompetitionLevel getLevel() {
        return level;
    }
    
    public String getCountry() {
        return country;
    }
    
    public int[] getScoreArray() {
        return Arrays.copyOf(scores, scores.length);
    }
    
    // Setter methods
    public void setCompetitorId(int competitorId) {
        this.competitorId = competitorId;
    }
    
    public void setName(Name name) {
        this.name = name;
    }
    
    public void setLevel(CompetitionLevel level) {
        this.level = level;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public void setScores(int[] scores) {
        this.scores = new int[5];
        if (scores != null && scores.length == 5) {
            for (int i = 0; i < 5; i++) {
                this.scores[i] = validateScore(scores[i]);
            }
        }
    }
    
    private int validateScore(int score) {
        if (score < 0) return 0;
        if (score > 5) return 5;
        return score;
    }
    
    public double getOverallScore() {
        if (scores == null) return 0.0;
        
        Arrays.sort(scores);
        double sum = 0;
        int scoresToConsider;
        
        switch (level) {
            case BEGINNER:
                scoresToConsider = 3;
                for (int i = scores.length - scoresToConsider; i < scores.length; i++) {
                    sum += scores[i];
                }
                return sum / scoresToConsider;
                
            case INTERMEDIATE:
                scoresToConsider = 4;
                for (int i = scores.length - scoresToConsider; i < scores.length; i++) {
                    sum += scores[i];
                }
                return sum / scoresToConsider;
                
            case ADVANCED:
                for (int score : scores) {
                    sum += score;
                }
                return sum / scores.length;
                
            default:
                return 0.0;
        }
    }
    
    public String getFullDetails() {
        return String.format("Competitor number %d, name %s, country %s.\n" +
                           "%s is a %s and received these scores: %s\n" +
                           "This gives an overall score of %.1f",
                           competitorId,
                           name.getFullName(),
                           country,
                           name.getFirstName(),
                           level.toString(),
                           Arrays.toString(scores).replaceAll("[\\[\\]]", ""),
                           getOverallScore());
    }
    
    public String getShortDetails() {
        return String.format("CN %d (%s) has overall score %.1f",
                           competitorId,
                           name.getInitials(),
                           getOverallScore());
    }
    
    public void saveToDatabase() throws SQLException {
        String sql = "INSERT INTO Competitors (competitorId, firstName, lastName, level, country, " +
                    "score1, score2, score3, score4, score5) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, competitorId);
            pstmt.setString(2, name.getFirstName());
            pstmt.setString(3, name.getLastName());
            pstmt.setString(4, level.toString());
            pstmt.setString(5, country);
            for (int i = 0; i < scores.length; i++) {
                pstmt.setInt(i + 6, scores[i]);
            }
            
            pstmt.executeUpdate();
        }
    }
    
    public static Competitor loadFromDatabase(int id) throws SQLException {
        String sql = "SELECT * FROM Competitors WHERE competitorId = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Name name = new Name(rs.getString("firstName"), rs.getString("lastName"));
                    CompetitionLevel level = CompetitionLevel.valueOf(rs.getString("level"));
                    String country = rs.getString("country");
                    int[] scores = new int[5];
                    for (int i = 1; i <= 5; i++) {
                        scores[i-1] = rs.getInt("score" + i);
                    }
                    return new Competitor(id, name, level, country, scores);
                }
            }
        }
        return null;
    }
}