public class Guest {

    private String firstName;
    private String lastName;
    private Date birthDate;
    private String personalNumber;
    private String country;
    private int roomNumber;

    public Guest(String firstName, String lastName, Date birthDate, String personalNumber, String country, int roomNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.personalNumber = personalNumber;
        this.country = country;
        this.roomNumber = roomNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getEgn() {
        return personalNumber;
    }

    public String getCountry(){
        return country;
    }

    public int getRoomNumber(){
        return roomNumber;
    }
}
