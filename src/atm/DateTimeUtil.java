package atm;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Date/Time utility class for ATM operations
 * Provides date and time formatting and calculations
 * @author ATM Machine Simulation
 * @version 2.0
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
        return LocalTime.now().format(TIME_FORMATTER);
    }
    
    /**
     * Gets current date and time in formatted string
     * @return Formatted datetime string
     */
    public static String getCurrentDateTime() {
        return LocalDateTime.now().format(DATETIME_FORMATTER);
    }
    
    /**
     * Checks if current time is within business hours
     * @return true if within business hours (9 AM - 9 PM)
     */
    public static boolean isBusinessHours() {
        int hour = LocalTime.now().getHour();
        return hour >= 9 && hour < 21;
    }
    
    /**
     * Checks if today is a weekday
     * @return true if Monday-Friday
     */
    public static boolean isWeekday() {
        LocalDate today = LocalDate.now();
        int dayOfWeek = today.getDayOfWeek().getValue();
        return dayOfWeek >= 1 && dayOfWeek <= 5;
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
     * Gets current timestamp in milliseconds
     * @return Current timestamp
     */
    public static long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }
    
    /**
     * Parses date string to LocalDate
     * @param dateString Date string in dd-MM-yyyy format
     * @return LocalDate object or null if parsing fails
     */
    public static LocalDate parseDate(String dateString) {
        try {
            return LocalDate.parse(dateString, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
    
    /**
     * Checks if a given date is a weekend
     * @param date Date to check
     * @return true if Saturday or Sunday
     */
    public static boolean isWeekend(LocalDate date) {
        int dayOfWeek = date.getDayOfWeek().getValue();
        return dayOfWeek == 6 || dayOfWeek == 7;
    }
    
    /**
     * Gets the number of days between two dates
     * @param startDate Start date
     * @param endDate End date
     * @return Number of days between dates
     */
    public static long getDaysBetween(LocalDate startDate, LocalDate endDate) {
        return java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate);
    }
    
    /**
     * Private constructor to prevent instantiation
     */
    private DateTimeUtil() {
        throw new AssertionError("Cannot instantiate datetime utility class");
    }
}
