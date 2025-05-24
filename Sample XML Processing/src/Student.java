public record Student(String id, String name, String course, int yr) {
    public String toString() {
        return String.format("ID #  : %s%nName  : %s%nCourse: %s%nYear: %d", id, name, course, yr);
    }
}
