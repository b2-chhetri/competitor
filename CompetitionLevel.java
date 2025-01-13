// CompetitionLevel.java
/**
 * Defines the possible competition levels for competitors
 * An enum is used to ensure only valid levels can be assigned
 */
public enum CompetitionLevel {
    // The three possible competition levels with their display names
    BEGINNER("Beginner"),       // For new competitors
    INTERMEDIATE("Intermediate"), // For somewhat experienced competitors
    ADVANCED("Advanced");       // For highly skilled competitors

    // Instance variable to store the display name
    private final String displayName;

    /**
     * Constructor for the enum values
     * @param displayName The formatted name for display
     */
    CompetitionLevel(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Gets the formatted display name of the level
     * @return The level name in proper case (e.g., "Beginner" instead of "BEGINNER")
     */
    @Override
    public String toString() {
        return displayName;
    }

    /**
     * Converts a string to a CompetitionLevel
     * Useful for reading levels from user input or files
     * 
     * @param str The string to convert
     * @return The corresponding CompetitionLevel, or BEGINNER if invalid
     */
    public static CompetitionLevel fromString(String str) {
        try {
            return valueOf(str.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            // Return BEGINNER as default if the string doesn't match any level
            return BEGINNER;
        }
    }
}