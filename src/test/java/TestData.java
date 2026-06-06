public final class TestData {
    public static final String BASE_URL = "https://www.automationexercise.com";
    public static final String USER_EMAIL = readEnvironmentVariable("AE_USER_EMAIL", "reham@gmail.com");
    public static final String USER_PASSWORD = readEnvironmentVariable("AE_USER_PASSWORD", "pass123456");
    public static final String INVALID_EMAIL = "reham.invalid@example.com";
    public static final String INVALID_PASSWORD = "wrong-password";
    public static final String USER_DISPLAY_NAME = readEnvironmentVariable("AE_USER_DISPLAY_NAME", "reham");

    private TestData() {
    }

    public static String uniqueSignupEmail() {
        return "selenium.assignment." + System.currentTimeMillis() + "@example.com";
    }

    private static String readEnvironmentVariable(String name, String defaultValue) {
        String value = System.getenv(name);
        if (value == null || value.trim().isEmpty()) {
            return defaultValue;
        }
        return value;
    }
}
