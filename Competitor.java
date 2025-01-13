package assestment1;

//Class to represent a Competitor
class Competitor {
 private int competitorID;
 private Name name;
 private String competitionLevel;
 private String country; // Extra attribute

 // Constructor
 public Competitor(int competitorID, Name name, String competitionLevel, String country) {
     this.competitorID = competitorID;
     this.name = name;
     this.competitionLevel = competitionLevel;
     this.country = country;
 }

 // Getters and setters
 public int getCompetitorID() {
     return competitorID;
 }

 public void setCompetitorID(int competitorID) {
     this.competitorID = competitorID;
 }

 public Name getName() {
     return name;
 }

 public void setName(Name name) {
     this.name = name;
 }

 public String getCompetitionLevel() {
     return competitionLevel;
 }

 public void setCompetitionLevel(String competitionLevel) {
     this.competitionLevel = competitionLevel;
 }

 public String getCountry() {
     return country;
 }

 public void setCountry(String country) {
     this.country = country;
 }

 // Placeholder method for overall score
 public double getOverallScore() {
     return 5.0; // Placeholder value
 }

 // Method to get full details
 public String getFullDetails() {
     return "Competitor number " + competitorID + ", name " + name.getFullName() + ", country " + country + ".\n" +
            name.getFullName().split(" ")[0] + " is a " + competitionLevel + " and has an overall score of " + getOverallScore() + ".";
 }

 // Method to get short details
 public String getShortDetails() {
     return "CN " + competitorID + " (" + name.getInitials() + ") has overall score " + getOverallScore();
 }
}
