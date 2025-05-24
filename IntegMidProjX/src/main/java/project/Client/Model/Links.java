package project.Client.Model;

public enum Links {
    // Json Files
    CUSTOMER_JSON("src/main/resources/data/customer.json"),
    EVENT_JSON("src/main/resources/data/event.json"),

    // Images
    APP_LOGO_ICON("src/main/resources/images/App Logo (ICON).png"),
    APP_LOGO_WOBG("src/main/resources/images/App Logo (WO BG).png"),
    CHECK_PNG("src/main/resources/images/check.png"),
    FEMALE_PROFILE("src/main/resources/images/female_profile.png"),
    MALE_PROFILE("src/main/resources/images/male_profile.png"),
    GIANT_STEPS("src/main/resources/images/GiantSteps.png"),
    STAGE_CONTENT("src/main/resources/images/stage3.png");

    private final String filePath;

    Links(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
