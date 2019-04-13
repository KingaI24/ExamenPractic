package Domain;

public class Pacient extends Entity {
    private String CNP, firstName, lastName, birthDate, regDate;

    public Pacient(int id, String CNP, String firstName, String lastName, String birthDate, String regDate) {
        super(id);
        this.CNP = CNP;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.regDate = regDate;
    }

    public String getCNP() {
        return CNP;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getRegDate() {
        return regDate;
    }


    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "Pacient{" +
                "id=" + super.getId() +
                ", CNP='" + CNP + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", regDate='" + regDate + '\'' +
                '}';
    }
}
