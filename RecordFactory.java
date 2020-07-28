package contacts;

public class RecordFactory {

    public static Record createPerson(String name, String surname,
                                      String number, String gender,
                                      String birthDate) {
        return new Person(name, surname, number, gender, birthDate);
    }

    public static Record createOrganization(String name, String address,
                                            String number) {
        return new Organization(name, address, number);
    }
}
