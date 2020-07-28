package contacts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ContactBook implements Serializable {

    private static final long serialVersionUID = 1L;
    private final ArrayList<Record> contacts;

    public ContactBook() {
        this.contacts = new ArrayList<>();
    }

    public void add(Record record) {
        this.contacts.add(record);
    }

    private String shortInfo(int index) {
        return this.contacts.get(index).shortInfo();
    }

    public ArrayList<Integer> search(String part) {
        int i = 0;
        ArrayList<Integer> indexes = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        Pattern pattern = Pattern.compile(part, Pattern.CASE_INSENSITIVE);
        for (int i1 = 0; i1 < this.contacts.size(); i1++) {
            if (checkInfo(pattern, i1)) {
                i++;
                indexes.add(i1);
                result.append(String.format("%d. %s%n", i,
                        this.contacts.get(i1).shortInfo()));
            }
        }
        if (i == 1) {
            System.out.println("Found " + i + " result:");
        } else {
            System.out.println("Found " + i + " results:");
        }
        System.out.println(result.toString());
        return indexes;
    }

    private boolean checkInfo(Pattern pattern, int index) {
        return pattern.matcher(this.shortInfo(index)).find() ||
                pattern.matcher(this.contacts.get(index).getNumber()).find();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int index = 1; index <= this.contacts.size(); index++) {
            str.append(String.format("%d. %s%n",
                    index, this.contacts.get(index - 1).shortInfo()));
        }
        return str.toString();
    }

    public void printContact(int index) {
        System.out.println(this.contacts.get(index - 1));
    }

    public void count() {
        System.out.printf("The Phone Book has %d records%n", this.contacts.size());
    }


    public boolean isEmpty() {
        return this.contacts.isEmpty();
    }

    public void remove(int index) {
        this.contacts.remove(index - 1);
    }

    public String getFields(int index) {
        return this.contacts.get(index - 1).getFields();
    }

    public void editUI(Scanner sc, int index) {
        this.contacts.get(index - 1).editUI(sc);
    }

    public int size() {
        return this.contacts.size();
    }

}
