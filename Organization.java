package contacts;

import java.util.Scanner;

public class Organization extends Record {

    private static final long serialVersionUID = 1L;

    public Organization(String name, String address, String number) {
        super(name, address, number);
    }

    @Override
    public String shortInfo() {
        return this.getName();
    }

    @Override
    public String toString() {
        return String.format("Organization name: %s%n" +
                        "Address: %s%n" +
                        "Number: %s%n" +
                        "Time created: %s%n" +
                        "Time last edit: %s",
                this.getName(), this.getSecondaryInfo(), this.getNumber(),
                this.getDateCreated(), this.getDateEdited());
    }

    public String getFields() {
        return "name, address, number";
    }

    public void editUI(Scanner sc) {
        String field = sc.nextLine();

        switch (field) {
            case "name":
                System.out.println("Enter name:");
                this.setFirstClassInfo(sc.nextLine());
                break;
            case "address":
                System.out.println("Enter address:");
                this.setSecondClassInfo(sc.nextLine());
                break;
            case "number":
                System.out.println("Enter number:");
                this.setNumber(sc.nextLine());
                break;
            default:
                System.out.println("Error");
        }
    }
}
