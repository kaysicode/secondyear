@SuppressWarnings("ALL")
public class Tournament {

    /**
     * <h1>
     *     data variables
     * </h1>
     */
    private int tournamentCode;
    private String tournamentName;
    private String tier;
    private int startDate;
    private int endDate;
    private String patchCode;
    private String url;
    private int lastUpdated;
    private String csvName;

    /**
     * <h2>
     * Constructor
     * </h2>
     * @param data - this parameter is a array where this
     *             is the data that will store in our data variables
     */
    public Tournament(String[] data) {
        this.tournamentCode = Integer.parseInt(data[0]);
        this.tournamentName = data[1];
        this.tier = data[2];
        this.startDate = Integer.parseInt(data[3]);
        this.endDate = Integer.parseInt(data[4]);
        this.patchCode = data[5];
        this.url = data[6];
        this.lastUpdated = Integer.parseInt(data[7]);
        this.csvName = data[8];
    }

    /**
     *<h1>
     *     Getters methods
     *</h1>
     */
    public int getTournamentCode() {
        return tournamentCode;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public String getTier() {
        return tier;
    }

    public int getStartDate() {
        return startDate;
    }

    public int getEndDate() {
        return endDate;
    }

    public String getPatchCode() {
        return patchCode;
    }

    public String getUrl() {
        return url;
    }

    public int getLastUpdated() {
        return lastUpdated;
    }

    public String getCsvName() {
        return csvName;
    }

    @Override
    public String toString() {
        return String.format(
                "Tournament Details:\n" +
                        "- Name       : %s\n" +
                        "- Code       : %s\n" +
                        "- Tier       : %s\n" +
                        "- Start Date : %s\n" +
                        "- URL        : %s",
                tournamentName, tournamentCode, tier, formatStartDate(String.valueOf(startDate)), url
        );
    }


    /**
     * Helper method to format the date in much more formal and easy to read
     * @param date - this is the given by toString method,
     *             where the date is converted to String to be able to use the substring method.
     * @return - it returns the formattaed date
     * <h1>
     *     Format:
     *     MM/DD/YYYY
     * </h1>
     */
    private String formatStartDate(String date) {
        if (date.length() == 8) { // Format: MMDDYYYY
            String month = date.substring(0, 2);
            String day = date.substring(2, 4);
            String year = date.substring(4);
            return String.format("%s/%s/%s", month, day, year);
        }
        return date; // Return as is if the format doesn't match
    }
}
