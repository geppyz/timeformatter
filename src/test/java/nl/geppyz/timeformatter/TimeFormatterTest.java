package nl.geppyz.timeformatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class TimeFormatterTest {

  /**
   * Test method for {@link nl.geppyz.timeformatter.TimeFormatter#format(long)}. Given a number of milliseconds (as an
   * input), which represents the time a certain task took to complete, return a String representation that converts
   * milliseconds into seconds in the given format: 3 seconds. Any leftover milliseconds should be truncated.
   */
  @Nested
  class Task1 {

    @Test
    void format_test10Seconds() {
      TimeFormatter formatter = new TimeFormatter();
      assertEquals("10 seconds", formatter.format(10000));
    }

    @Test
    void format_test10Seconds_withLeftoverMillis() {
      TimeFormatter formatter = new TimeFormatter();
      assertEquals("10 seconds", formatter.format(10145));
    }
  }
}
