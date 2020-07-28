package contacts;

import java.io.*;

public class SerializableUtility {

    public static void SerializeContacts(Object object, String filename)
            throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(object);
        oos.close();
    }

    public static Object deserializeContacts(String filename)
            throws IOException, ClassNotFoundException {
        System.out.println("Open " + filename);
        System.out.println();
        FileInputStream fis = new FileInputStream(filename);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);
        Object object = ois.readObject();
        ois.close();
        return object;
    }
}
