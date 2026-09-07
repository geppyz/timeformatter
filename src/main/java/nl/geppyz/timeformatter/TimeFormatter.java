package nl.geppyz.timeformatter;

import java.time.Duration;

public class TimeFormatter {

  /**
   * Format the given number of milliseconds into a String representation of seconds.
   *
   * @param millis the number of milliseconds
   * @return String representation of seconds (example: '10 seconds')
   */
  protected String format(long millis) {
    return Duration.ofMillis(millis).toSeconds() + " seconds";
  }
}
