// Name.java
/**
 * The Name class represents a person's full name by storing their first and last names.
 * This class is used as part of the Competitor Management System.
 * 
 * Features:
 * - Stores first name and last name separately
 * - Provides methods to get the full name
 * - Generates initials from the name
 * - Includes input validation
 */
public class Name {
    // Instance variables for storing the name components
    private String firstName;    // Stores the first name of the person
    private String lastName;     // Stores the last name of the person

    /**
     * Constructor to create a new Name object
     * Includes basic validation to ensure names aren't empty or null
     * 
     * @param firstName The person's first name
     * @param lastName The person's last name
     */
    public Name(String firstName, String lastName) {
        // Validate and clean the input names
        this.firstName = cleanName(firstName);
        this.lastName = cleanName(lastName);
    }

    /**
     * Helper method to clean and validate a name
     * Removes extra spaces and checks for null/empty values
     * 
     * @param name The name to clean
     * @return The cleaned name
     */
    private String cleanName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return "Unknown"; // Default value for invalid names
        }
        // Trim spaces and convert multiple spaces to single spaces
        return name.trim().replaceAll("\\s+", " ");
    }

    // Getter for first name
    public String getFirstName() {
        return firstName;
    }

    // Getter for last name
    public String getLastName() {
        return lastName;
    }

    // Setter for first name with validation
    public void setFirstName(String firstName) {
        this.firstName = cleanName(firstName);
    }

    // Setter for last name with validation
    public void setLastName(String lastName) {
        this.lastName = cleanName(lastName);
    }

    /**
     * Gets the full name by combining first and last name
     * 
     * @return The complete name as a single string
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * Generates initials from the name
     * Takes the first letter of first name and first letter of last name
     * 
     * @return The initials in uppercase
     */
    public String getInitials() {
        // Get first letter of each name and convert to uppercase
        String firstInitial = firstName.length() > 0 ? 
            String.valueOf(firstName.charAt(0)) : "?";
        String lastInitial = lastName.length() > 0 ? 
            String.valueOf(lastName.charAt(0)) : "?";

        return (firstInitial + lastInitial).toUpperCase();
    }

    /**
     * Provides a string representation of the Name object
     * 
     * @return The full name as a string
     */
    @Override
    public String toString() {
        return getFullName();
    }
}