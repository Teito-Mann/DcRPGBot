package util;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Constants {
    private final static PrintStream RUNTIME_STREAM = System.out;
    private final static String LOG_FILE_PATH = "src/assembled/log/logfile.log";
    private final static PrintStream LOG_STREAM = checkFile(LOG_FILE_PATH);

    public static void logToFile(String message) {
        LOG_STREAM.append(String.format("[%s]: %s", getCurrentTimeKey(), message));
    }
    public static void logToStdOut(String message) {
        RUNTIME_STREAM.println(String.format("[%s]: %s", getCurrentTimeKey(), message));
    }
    public static void logToAll(String message) {
        logToFile(message);
        logToFile(message);
    }
    /*
     * Util methods
     */
    public static String getExcAsString(Exception exc) {
        return String.format("%s\n%s", exc.getMessage(), Arrays.toString(exc.getStackTrace()));
    }
    private static String getCurrentTimeKey() {
        LocalDateTime now = LocalDateTime.now();
        return  String.format("%s-%s-%s.%s:%s:%s.%s",
                StringUtils.leftPad(String.valueOf(now.getYear()), 4, '0'),
                StringUtils.leftPad(String.valueOf(now.getMonth()), 2, '0'),
                StringUtils.leftPad(String.valueOf(now.getDayOfMonth()), 2, '0'),
                StringUtils.leftPad(String.valueOf(now.getHour()), 2, '0'),
                StringUtils.leftPad(String.valueOf(now.getMinute()), 2, '0'),
                StringUtils.leftPad(String.valueOf(now.getSecond()), 2, '0'),
                StringUtils.leftPad(String.valueOf(now.getNano()).substring(0, 3), 3, '0'));
    }
    private static PrintStream checkFile(String filePath) {
        File newFile = new File(filePath);
        RUNTIME_STREAM.println("Full FP=" + newFile.getAbsolutePath());
        if(newFile.exists()) {
            try {
                PrintStream newPrintStream = new PrintStream(new FileOutputStream(newFile, true));
                RUNTIME_STREAM.println(String.format("Printstream loaded from %s.", filePath));
                return newPrintStream;
            } catch(FileNotFoundException fnfExc) {
                // Unable to read file
            }
        }
        String errorMessage = String.format("Unable to read %s as a file for a PrintStream.", filePath);
        throw new IllegalArgumentException(errorMessage);
    }
}
