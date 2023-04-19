package jafa;

import java.time.Duration;
import java.time.Instant;

public class Stopwatch {
    private static Instant startTime = null;

    public static void start() {
        if (startTime == null) {
            startTime = Instant.now();
            System.out.println("Stoparica tece, pohitite!");
        } else {
            System.out.println("Stoparice ze tece!");
        }
    }

    public static void stop() {
        if (startTime != null) {
            Instant endTime = Instant.now();
            Duration elapsed = Duration.between(startTime, endTime);
            long seconds = elapsed.getSeconds();
            long minutes = seconds / 60;
            seconds %= 60;
            System.out.println(
                    "Stoparica se je ustavila! Vas cas je bil: " + minutes + " minutes, " + seconds + " seconds");
            startTime = null;
        } else {
            System.out.println("Pozor stopraica sploh ne tece, najprej jo zacnite!");
        }
    }

    public static void main(String[] args) {
        start();
        stop();
    }
}
