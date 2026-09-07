package nl.geppyz.timeformatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TimeFormatterTest {

  /**
   * Modify the previous code to format the milliseconds into minutes and seconds. 3 minutes 34 seconds, 5 minutes 0
   * seconds, 0 minutes 59 seconds.
   */
  @Nested
  class Task2 {

    @Test
    void format_testMinutesAndSeconds() {
      TimeFormatter formatter = new TimeFormatter();
      assertEquals("3 minutes 34 seconds", formatter.format(214000));
      assertEquals("5 minutes 0 seconds", formatter.format(300000));
      assertEquals("0 minutes 59 seconds", formatter.format(59000));
    }
  }
}
