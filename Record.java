package contacts;

//import java.io.IOException;
//import java.io.ObjectInputStream;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Scanner;

public abstract class Record implements Serializable {

    private static final long serialVersionUID = 1L;
    private String number;
    private String name;
    private String secondaryInfo;
    private final LocalDateTime dateCreated;
    private LocalDateTime dateEdited;

    public Record(String name, String secondaryInfo, String number) {
        this.name = name;
        this.secondaryInfo = secondaryInfo;
        this.setNumber(number);
        this.dateCreated = LocalDateTime.now();
        this.dateEdited = LocalDateTime.now();
    }

    //private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
    //    ois.defaultReadObject();
    //    validateInfo();
    //}

    public String getDateCreated() {
        return this.dateCreated.withNano(0).withSecond(0).toString();
    }

    public String getDateEdited() {
        return this.dateEdited.withSecond(0).withNano(0).toString();
    }

    public void setFirstClassInfo(String name) {
        this.name = name;
        infoEdited();
    }

    public void setSecondClassInfo(String info) {
        this.secondaryInfo = info;
        infoEdited();
    }

    public void setNumber(String number) {
        this.number = ValidateInput.validateNumber(number);
        infoEdited();
    }

    public void infoEdited() {
        this.dateEdited = LocalDateTime.now();
    }

    public String getName() {
        return this.name;
    }

    public String getNumber() {
        return this.number;
    }

    public String getSecondaryInfo() {
        return this.secondaryInfo;
    }

    abstract String shortInfo();

    //public void validateInfo() {
    //    this.number = ValidateInput.validateNumber(this.number);
    //}

    abstract public void editUI(Scanner sc);

    abstract public String getFields();
}
