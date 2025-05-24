package project.Client.Model;

public enum Months {
    JANUARY(0),
    FEBRUARY(1),
    MARCH(2),
    APRIL(3),
    MAY(4),
    JUNE(5),
    JULY(6),
    AUGUST(7),
    SEPTEMBER(8),
    OCTOBER(9),
    NOVEMBER(10),
    DECEMBER(11),
    ALL_EVENTS(-1); // optional special case

    private final int index;

    Months(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public static int getIndexByName(String name) {
        for (Months m : values()) {
            if (m.name().equalsIgnoreCase(name)) {
                return m.index;
            }
        }
        // default fallback
        return 0;
    }

    private String formatEnumName(String enumName) {
        String formatted = enumName.substring(0, 1).toUpperCase() + enumName.substring(1).toLowerCase();
        return formatted + " Events";
    }

}
