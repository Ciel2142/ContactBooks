package contacts;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Initialization {
    public static ContactsUI initializeWithFilename(String filename, Scanner sc) throws IOException, ClassNotFoundException {
        ContactBook contactBook;
        File file = new File(filename);
        if (file.isFile()) {
            contactBook = (ContactBook) SerializableUtility.deserializeContacts(filename);
        } else {
            contactBook = new ContactBook();
        }
        return new ContactsUI(new Scanner(System.in), contactBook, filename);
    }

    public static ContactsUI initializeWithOutFilename(Scanner sc) {
        return new ContactsUI(sc, new ContactBook());
    }
}
