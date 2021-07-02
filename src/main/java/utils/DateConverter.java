package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateConverter {
    public static LocalDate convertStringToLocalDate(String date) {
        String[] array = date.split("-");
        LocalDate result = LocalDate.of(Integer.parseInt(array[0]), Integer.parseInt(array[1]),
                Integer.parseInt(array[2]));
        return result;
    }

    public static LocalTime convertStringToLocalTime(String time) {
        String[] array = time.split(":");
        LocalTime result = LocalTime.of(Integer.parseInt(array[0]), Integer.parseInt(array[1]));
        return result;
    }

    public static LocalDateTime convertStringToLocalDateTime(String dateTime) {
        String[] array = dateTime.split(" ");
        LocalDate localDate = convertStringToLocalDate(array[0]);
        LocalTime localTime = convertStringToLocalTime(array[1]);
        return LocalDateTime.of(localDate, localTime);
    }

    public static String formatLocalDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(dateTimeFormatter);
    }
}
