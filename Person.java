package contacts;

import java.util.Scanner;

public class Person extends Record {

    private static final long serialVersionUID = 1L;
    private String gender;
    private String birthDate;

    public Person(String name, String surname, String number, String gender,
                  String birthDate) {
        super(name, surname, number);
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public void setDate(String date) {
        this.birthDate = ValidateInput.checkBirthDateValidity(date);
        this.infoEdited();
    }

    public void setGender(String gender) {
        this.gender = ValidateInput.checkGender(gender);
        this.infoEdited();
    }

    //@Override
    //public void validateInfo() {
    //    super.validateInfo();
    //    this.birthDate = ValidateInput.checkBirthDateValidity(this.birthDate);
    //    this.gender = ValidateInput.checkGender(this.gender);
    //}

    @Override
    public String shortInfo() {
        return String.format("%s %s", this.getName(), this.getSecondaryInfo());
    }

    @Override
    public String toString() {
        return String.format("Name: %s%n" +
                        "Surname: %s%n" +
                        "Birth date: %s%n" +
                        "Gender: %s%n" +
                        "Number: %s%n" +
                        "Time created: %s%n" +
                        "Time last edit: %s",
                this.getName(), this.getSecondaryInfo(), this.birthDate,
                this.gender, this.getNumber(), this.getDateCreated(),
                this.getDateEdited());
    }

    @Override
    public void editUI(Scanner sc) {
        String field = sc.nextLine();

        switch (field) {
            case "name":
                System.out.println("Enter name:");
                this.setFirstClassInfo(sc.nextLine());
                break;
            case "surname":
                System.out.println("Enter surname:");
                this.setSecondClassInfo(sc.nextLine());
                break;
            case "number":
                System.out.println("Enter number:");
                this.setNumber(sc.nextLine());
                break;
            case "birth":
                System.out.println("Enter birth date:");
                this.setDate(sc.nextLine());
                break;
            case "gender":
                System.out.println("Enter gender (M, F):");
                this.setGender(sc.nextLine());
                break;
            default:
                System.out.println("Error");
        }
    }

    @Override
    public String getFields() {
        return "name, surname, birth, gender, number";
    }

}
