package nl.geppyz.timeformatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import nl.geppyz.timeformatter.configuration.TimeSelection;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TimeFormatterTest {

  /**
   * Given a number of milliseconds (as an input), which represents the time a certain task took to complete, return a
   * String representation that converts milliseconds into seconds in the given format: 3 seconds. Any leftover
   * milliseconds should be truncated.
   */
  @Nested
  class Task1 {

    @Test
    void format_test10Seconds() {
      TimeFormatter formatter = new TimeFormatter();
      assertEquals("10 seconds", formatter.format(10000, TimeSelection.SECONDS));
    }

    @Test
    void format_test10Seconds_withLeftoverMillis() {
      TimeFormatter formatter = new TimeFormatter();
      assertEquals("10 seconds", formatter.format(10145, TimeSelection.SECONDS));
    }
  }

  /**
   * Modify the previous code to format the milliseconds into minutes and seconds. 3 minutes 34 seconds, 5 minutes 0
   * seconds, 0 minutes 59 seconds.
   */
  @Nested
  class Task2 {

    @Test
    void format_testMinutesAndSeconds() {
      TimeFormatter formatter = new TimeFormatter();
      assertEquals("3 minutes 34 seconds", formatter.format(214000, TimeSelection.MINUTES));
      assertEquals("5 minutes 0 seconds", formatter.format(300000, TimeSelection.MINUTES));
      assertEquals("0 minutes 59 seconds", formatter.format(59000, TimeSelection.MINUTES));
    }
  }

  /**
   * Modify the previous code to format the milliseconds into hours. 0 hours 3 minutes 34 seconds, 1 hours 5 minutes 0
   * seconds, 3 hours 0 minutes 59 seconds, 5 hours 3 minutes 34 seconds
   */
  @Nested
  class Task3 {

    @Test
    void format_testMinutesAndSeconds() {
      TimeFormatter formatter = new TimeFormatter();
      assertEquals("0 hours 3 minutes 34 seconds", formatter.format(214000, TimeSelection.HOURS));
      assertEquals("1 hours 5 minutes 0 seconds", formatter.format(3900000, TimeSelection.HOURS));
      assertEquals("3 hours 0 minutes 59 seconds", formatter.format(10859000, TimeSelection.HOURS));
      assertEquals("5 hours 3 minutes 34 seconds", formatter.format(18214000, TimeSelection.HOURS));
    }
  }
}
