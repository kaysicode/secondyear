package prelim;

public class Assignments {
    private String projectName;
    private int dateAssigned;
    private int dateSumbitted;

    public Assignments(String projectName, int dateAssigned, int dateSumbitted) {
        this.projectName = projectName;
        this.dateAssigned = dateAssigned;
        this.dateSumbitted = dateSumbitted;
    }

    public Assignments() {
    }

    public String getProjectName() {
        return projectName;
    }

    public int getDateAssigned() {
        return dateAssigned;
    }

    public int getDateSumbitted() {
        return dateSumbitted;
    }

    @Override
    public String toString() {
        return "Assignments{" +
                "projectName='" + getProjectName() + '\'' +
                ", dateAssigned=" + getDateAssigned() +
                ", dateSumbitted=" + getDateSumbitted() +
                '}';
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getProjectName() != getProjectName()) {
            return false;
        }
        Assignments assignments = (Assignments) obj;
        return projectName.equals(assignments.projectName);
    }

}
