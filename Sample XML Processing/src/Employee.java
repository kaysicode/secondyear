public class Employee {
    private String id;
    private String type;
    private String name;
    private String department;
    private String position;

    public Employee(String id, String type, String name, String position,
                    String department) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.position = position;
        this.department = department;
    }

    public String toString() {
        return String.format("ID #      : %s%n" +
                "Type      : %s%n" +
                "Name      : %s%n" +
                "Position  : %s%n" +
                "Department: %s", id, type, name, position, department);
    }
}
