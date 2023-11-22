package utility;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * The time_zones class contains methods for converting between UTC and local timestamps. Also contains methods that find other important dates based on the current date and time such as start of next week, start of next month, etc.
 * @author Arun Rai
 */
public class time_zones {
    
    /**
     * Converts the given minutes, hours, and date into a UTC timestamp.
     * @param minutes Minutes
     * @param hours Hours
     * @param date Date
     * @return Timestamp in UTC
     */
    public static Timestamp convertToTimeStampDataTypeUTC(String minutes, String hours, LocalDate date){
        
        
        int hour = Integer.parseInt(hours);
        int minute = Integer.parseInt(minutes);
        LocalDateTime localDateTime = LocalDateTime.of(date, LocalTime.of(hour, minute, 0));
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        Instant instant = zonedDateTime.toInstant();
        java.sql.Timestamp utcTimestamp = java.sql.Timestamp.valueOf(instant.atOffset(java.time.ZoneOffset.UTC).toLocalDateTime());
        return utcTimestamp;

    }

    /**
     * Converts the given UTC timestamp into a local timestamp.
     * @param UTCtime Timestamp in UTC time.
     * @return Timestamp in local time
     */
    public static Timestamp convertToTimeStampDataTypeLOCAL(Timestamp UTCtime){
        LocalDateTime localDateTime = UTCtime.toLocalDateTime();
        ZoneId systemTimeZone = ZoneId.systemDefault();
        ZonedDateTime zonedUTC = localDateTime.atZone(ZoneId.of("UTC"));
        ZonedDateTime zonedLocal = zonedUTC.withZoneSameInstant(systemTimeZone);        
        Timestamp localTime = Timestamp.valueOf(zonedLocal.toLocalDateTime());        
        return localTime;
    }
    
    /**
     * Returns a timestamp for the start of the week for the given week timestamp.
     * @param timestamp Timestamp in local time.
     * @return Timestamp for the start of the week
     */
    public static Timestamp getStartOfWeekTimestamp(Timestamp timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp.getTime());
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }
    
    /**
     * Returns a timestamp for the start of the week following the week in the given timestamp.
     * @param timestamp Timestamp in local time.
     * @return a timestamp for the start of the next week
     */
    public static Timestamp getStartOfNextWeekTimestamp(Timestamp timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp.getTime());
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.add(Calendar.DATE, 7);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }
    
    /**
     * Returns a timestamp for the start of the week before the week in the given timestamp.
     * @param timestamp Timestamp in local time.
     * @return Time stamp of Start of week before the week in given Timestamp.
     */
    public static Timestamp getStartOfPreviousWeekTimestamp(Timestamp timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp.getTime());
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.add(Calendar.DATE, -7);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }
    
    /**
     * Returns a timestamp for the start of the month for the given month timestamp.
     * @param timestamp Timestamp in local time.
     * @return Timestamp for the start of the month.
     */
    public static Timestamp getStartOfMonthTimestamp(Timestamp timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp.getTime());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * Returns a timestamp for the start of the month before the month in the given timestamp.
     * @param timestamp Timestamp in local time.
     * @return Time stamp of Start of month before the month in given Timestamp.
     */
    public static Timestamp getStartOfPreviousMonthTimestamp(Timestamp timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp.getTime());
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * Returns a timestamp for the start of the month following the month in the given timestamp.
     * @param timestamp Timestamp in local time.
     * @return a timestamp for the start of the next month
     */
    public static Timestamp getStartOfNextMonthTimestamp(Timestamp timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp.getTime());
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }    
    
}
