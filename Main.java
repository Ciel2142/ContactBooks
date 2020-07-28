package contacts;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);

        ContactsUI contactsUI;
        if (args.length != 0) {
            contactsUI = Initialization.initializeWithFilename(args[0], sc);
        } else {
            contactsUI = Initialization.initializeWithOutFilename(sc);
        }

        contactsUI.start();

    }
}
