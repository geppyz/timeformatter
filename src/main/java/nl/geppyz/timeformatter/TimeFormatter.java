package nl.geppyz.timeformatter;

import java.time.Duration;

public class TimeFormatter {

  private static final String MINUTES_FORMAT = "%d minutes";
  private static final String SECONDS_FORMAT = "%d seconds";
  private static final String SEPARATOR = " ";

  /**
   * Format the given number of milliseconds into a String representation of minutes and seconds.
   *
   * @param millis the number of milliseconds
   * @return String representation of minutes and seconds (example: '5 minutes 10 seconds')
   */
  protected String format(long millis) {
    Duration duration = Duration.ofMillis(millis);
    long minutes = duration.toMinutesPart();
    long seconds = duration.toSecondsPart();
    return String.format(MINUTES_FORMAT, minutes) + SEPARATOR + String.format(SECONDS_FORMAT, seconds);
  }
}
