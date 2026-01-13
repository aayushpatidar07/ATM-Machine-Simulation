package atm;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Date/Time utility class for ATM operations
 * Provides date and time formatting and calculations
 * @author ATM Machine Simulation
 * @version 1.0
 */
public class DateTimeUtil {
    
    private static final DateTimeFormatter DATE_FORMATTER = 
        DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter TIME_FORMATTER = 
        DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final DateTimeFormatter DATETIME_FORMATTER = 
        DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    
    /**
     * Gets current date in formatted string
     * @return Formatted date string
     */
    public static String getCurrentDate() {
        return LocalDate.now().format(DATE_FORMATTER);
    }
    
    /**
     * Gets current time in formatted string
     * @return Formatted time string
     */
    public static String getCurrentTime() {
        return java.time.LocalTime.now().format(TIME_FORMATTER);
    }
    
    /**
     * Gets current date and time in formatted string
     * @return Formatted datetime string
     */
    public static String getCurrentDateTime() {
        return java.time.LocalDateTime.now().format(DATETIME_FORMATTER);
    }
    
    /**
     * Checks if current time is within business hours
     * @return true if within business hours (9 AM - 9 PM)
     */
    public static boolean isBusinessHours() {
        int hour = java.time.LocalTime.now().getHour();
        return hour >= 9 && hour < 21;
    }
    
    /**
     * Checks if today is a weekday
     * @return true if Monday-Friday
     */
    public static boolean isWeekday() {
        java.time.DayOfWeek day = LocalDate.now().getDayOfWeek();
        return day != java.time.DayOfWeek.SATURDAY && 
               day != java.time.DayOfWeek.SUNDAY;
    }
    
    /**
     * Gets day of week
     * @return Day name
     */
    public static String getDayOfWeek() {
        return LocalDate.now().getDayOfWeek().toString();
    }
    
    /**
     * Gets month name
     * @return Month name
     */
    public static String getMonthName() {
        return LocalDate.now().getMonth().toString();
    }
    
    /**
     * Formats milliseconds to readable duration
     * @param millis Milliseconds
     * @return Formatted duration string
     */
    public static String formatDuration(long millis) {
        long seconds = millis / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        
        seconds = seconds % 60;
        minutes = minutes % 60;
        
        if (hours > 0) {
            return String.format("%dh %dm %ds", hours, minutes, seconds);
        } else if (minutes > 0) {
            return String.format("%dm %ds", minutes, seconds);
        } else {
            return String.format("%ds", seconds);
        }
    }
    
    /**
     * Private constructor to prevent instantiation
     */
    private DateTimeUtil() {
        throw new AssertionError("Cannot instantiate datetime utility class");
    }
}
