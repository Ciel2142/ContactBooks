package contacts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ContactsUI {
    private final Scanner sc;
    private final ContactBook cb;
    private final String fileName;

    public ContactsUI(Scanner sc, ContactBook contactBook) {
        this(sc, contactBook, "EmptyLine.txt");
    }

    public ContactsUI(Scanner sc, ContactBook contactBook, String fileName) {
        this.sc = sc;
        this.cb = contactBook;
        this.fileName = fileName;
    }

    public void start() throws IOException {
        String command;

        do {
            System.out.println("[menu] Enter action (add, list, search, count, exit):");
            command = sc.nextLine();
        } while (delegateCommand(command));
    }

    public ContactBook getContactBook() {
        return this.cb;
    }

    private void searchLoop() throws IOException {
        String command;

        while (true) {
            System.out.println("Enter search query:");
            ArrayList<Integer> found = this.cb.search(sc.nextLine());
            System.out.println("[search] Enter action ([number], back, again):");
            command = sc.nextLine();
            if ("back".equals(command)) {
                break;
            } else if (command.matches("\\d+")) {
                int index = found.get(Integer.parseInt(command) - 1) + 1;
                this.cb.printContact(index);
                this.specificRecordMenu(index);
                break;
            } else if (!"again".equals(command)) {
                System.out.println("Something wrong");
                break;
            }
        }
    }

    private void save() throws IOException {
        SerializableUtility.SerializeContacts(this.getContactBook(),
                this.fileName);
        System.out.println("Saved");
    }

    private void specificRecordMenu(int index) throws IOException {
        String command;
        while (true) {
            System.out.println("\n[record] Enter action (edit, delete, menu):");
            command = sc.nextLine();
            switch (command) {
                case "edit":
                    this.edit(index);
                    this.cb.printContact(index);
                    break;
                case "delete":
                    this.remove(index);
                    return;
                case "menu":
                    return;
                default:
                    System.out.println("Something is wrong!");
            }
        }
    }

    private boolean delegateCommand(String command) throws IOException {

        switch (command) {
            case "add":
                System.out.println("Enter the type (person, organization):");
                this.add("person".equals(sc.nextLine()));
                this.save();
                break;
            case "list":
                this.list();
                break;
            case "search":
                this.searchLoop();
                break;
            case "count":
                this.cb.count();
                break;
            case "exit":
                return false;
            default:
                System.out.println("Wrong command:");
        }

        return true;
    }

    private void list() throws IOException {
        if (this.cb.isEmpty()) {
            System.out.println("No records!");
            return;
        }
        System.out.println(cb);
        System.out.println("[list] Enter action ([number], back:)");
        String input = sc.nextLine();
        if (input.matches("\\d+")) {
            int num = Integer.parseInt(input);
            if (num > cb.size()) {
                System.out.println("Out of range!");
            } else {
                this.cb.printContact(num);
                specificRecordMenu(num);
            }
        } else if (!"back".equals(input)) {
            System.out.println("Wrong input!");
        }
    }

    private void add(boolean isPerson) {
        if (isPerson) {
            personAdd();
        } else {
            organizationAdd();
        }

        System.out.println("The record added!");
        System.out.println();
    }

    private void organizationAdd() {
        System.out.println("Enter the organization name:");
        String orgName = sc.nextLine();
        System.out.println("Enter the address:");
        String address = sc.nextLine();
        System.out.println("Enter the number:");
        this.cb.add(RecordFactory.createOrganization(orgName, address,
                sc.nextLine()));
    }

    private void personAdd() {
        System.out.println("Enter the name:");
        String name = sc.nextLine();
        System.out.println("Enter the surname:");
        String surname = sc.nextLine();
        System.out.println("Enter the birth date:");
        String birthDate = ValidateInput.checkBirthDateValidity(sc.nextLine());
        System.out.println("Enter the gender (M, F):");
        String gender = ValidateInput.checkGender(sc.nextLine());
        System.out.println("Enter the number:");
        String number = sc.nextLine();
        this.cb.add(RecordFactory.createPerson(name, surname, number, gender,
                birthDate));
    }

    private void edit(int index) throws IOException {
        System.out.printf("Select a field (%s):%n", this.cb.getFields(index));
        this.cb.editUI(this.sc, index);
        this.save();
    }

    private void remove(int index) throws IOException {
        this.cb.remove(index);
        System.out.println("The record removed!");
        this.save();
    }

}
