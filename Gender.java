package contacts;

public enum Gender {
    F,
    M;

    public static boolean contains(String value) {
        return F.name().equals(value) || M.name().equals(value);
    }
}
