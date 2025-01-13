// DatabaseManager.java
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/CompetitionDB?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String USER = "root";  // Change if different
    private static final String PASS = "Sadhana@0112";      // Add your password
    
    /**
     * Tests the database connection
     */
    public static void testConnection() throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            System.out.println("Database connection test successful");
        }
    }
    
    /**
     * Retrieves all competitors from the database
     */
    public static List<Competitor> getAllCompetitors() throws SQLException {
        List<Competitor> competitors = new ArrayList<>();
        String sql = "SELECT * FROM Competitors ORDER BY competitorId";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Name name = new Name(rs.getString("firstName"), rs.getString("lastName"));
                CompetitionLevel level = CompetitionLevel.valueOf(rs.getString("level").toUpperCase());
                String country = rs.getString("country");
                int[] scores = new int[5];
                for (int i = 1; i <= 5; i++) {
                    scores[i-1] = rs.getInt("score" + i);
                }
                competitors.add(new Competitor(
                    rs.getInt("competitorId"),
                    name,
                    level,
                    country,
                    scores
                ));
            }
        }
        return competitors;
    }
    
    /**
     * Updates a competitor's scores in the database
     */
    public static void updateScores(int competitorId, int[] scores) throws SQLException {
        String sql = "UPDATE Competitors SET score1=?, score2=?, score3=?, score4=?, score5=? " +
                    "WHERE competitorId=?";
                    
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            for (int i = 0; i < scores.length; i++) {
                pstmt.setInt(i + 1, scores[i]);
            }
            pstmt.setInt(6, competitorId);
            pstmt.executeUpdate();
        }
    }
}