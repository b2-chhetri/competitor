package assestment1;

//Class to represent a Name
class Name {
 private String firstName;
 private String lastName;

 // Constructor
 public Name(String firstName, String lastName) {
     this.firstName = firstName;
     this.lastName = lastName;
 }

 // Method to get the full name
 public String getFullName() {
     return firstName + " " + lastName;
 }

 // Method to get the initials
 public String getInitials() {
     return (firstName.charAt(0) + "" + lastName.charAt(0)).toUpperCase();
 }
}
