package contacts;

import java.time.LocalDate;

public class ValidateInput {

    protected static String validateNumber(String number) {
        if (number.matches("(((\\+?\\d[- ])?" +
                "((\\([a-zA-Z0-9]{2,}\\)[- ]([a-zA-Z0-9]{2,}))|" +
                "([a-zA-Z0-9]{2,})))|" +
                "([a-zA-Z0-9]{2,}[- ]\\([a-zA-Z0-9]{2,}\\)))" +
                "([- ][a-zA-Z0-9]{2,})*|" +
                "\\+?((\\([a-zA-Z0-9]+\\))|([a-zA-Z0-9]+))")) {
            return number;
        }
        return "[no number]";
    }

    protected static String checkBirthDateValidity(String birthDate) {
        try {
            birthDate = LocalDate.parse(birthDate).toString();
        } catch (Exception e) {
            System.out.println("Bad birth date!");
            birthDate = "[no data]";
        }
        return birthDate;
    }

    protected static String checkGender(String gender) {
        if (Gender.contains(gender)) {
            gender = Gender.valueOf(gender).name();
        } else {
            System.out.println("Bad gender!");
            gender = "[no data]";
        }
        return gender;
    }

}
