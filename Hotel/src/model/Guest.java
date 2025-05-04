package model;

import java.util.Objects;

/**
 * Класът {@code Guest} представя гост на хотела с лични данни.
 */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guest guest = (Guest) o;
        return roomNumber == guest.roomNumber && Objects.equals(firstName, guest.firstName) && Objects.equals(lastName, guest.lastName) && Objects.equals(birthDate, guest.birthDate) && Objects.equals(personalNumber, guest.personalNumber) && Objects.equals(country, guest.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, birthDate, personalNumber, country, roomNumber);
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

    public String getPersonalNumber() {
        return personalNumber;
    }

    public String getCountry(){
        return country;
    }

    public int getRoomNumber(){
        return roomNumber;
    }

    /**
     * Връща текстова информация за госта.
     *
     * @return низ с лични данни
     */
    public String getInfo(){
        StringBuilder builder = new StringBuilder();
        builder.append("First name: "+firstName+"\n");
        builder.append("Last name: "+lastName+"\n");
        builder.append("Birth date: "+birthDate.getDateAsString()+"\n");
        builder.append("Personal number: "+personalNumber+"\n");
        builder.append("Country: "+country+"\n");
        builder.append("Room number: "+roomNumber+"\n");

        return  builder.toString();
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
}
