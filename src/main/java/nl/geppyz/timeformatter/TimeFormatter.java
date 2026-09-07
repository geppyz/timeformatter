package nl.geppyz.timeformatter;

import java.time.Duration;
import nl.geppyz.timeformatter.configuration.TimeSelection;

public class TimeFormatter {

  private static final String HOURS_FORMAT = "%d hours %d minutes %d seconds";
  private static final String MINUTES_FORMAT = "%d minutes %d seconds";
  private static final String SECONDS_FORMAT = "%d seconds";

  /**
   * Format the given number of milliseconds into a String representation of minutes and seconds.
   *
   * @param millis the number of milliseconds
   * @return String representation of minutes and seconds (example: '5 minutes 10 seconds')
   */
  protected String format(long millis, TimeSelection timeSelection) {
    Duration duration = Duration.ofMillis(millis);
    return switch (timeSelection) {
      case SECONDS -> String.format(SECONDS_FORMAT, duration.getSeconds());
      case MINUTES -> String.format(MINUTES_FORMAT, duration.toMinutes(), duration.toSecondsPart());
      case HOURS -> String.format(HOURS_FORMAT, duration.toHours(), duration.toMinutesPart(), duration.toSecondsPart());
    };
  }
}
