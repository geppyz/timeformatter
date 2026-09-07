package nl.geppyz.timeformatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import nl.geppyz.timeformatter.configuration.TimeFormatConfiguration;
import nl.geppyz.timeformatter.configuration.TimeFormatStyle;
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
      TimeFormatConfiguration config = new TimeFormatConfiguration(TimeSelection.ONLY_SECONDS, TimeFormatStyle.FULL,
          true);
      assertEquals("10 seconds", formatter.format(10000, config));
    }

    @Test
    void format_test10Seconds_withLeftoverMillis() {
      TimeFormatter formatter = new TimeFormatter();
      TimeFormatConfiguration config = new TimeFormatConfiguration(TimeSelection.ONLY_SECONDS, TimeFormatStyle.FULL,
          true);
      assertEquals("10 seconds", formatter.format(10145, config));
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
      TimeFormatConfiguration config = new TimeFormatConfiguration(TimeSelection.MINUTES_AND_SECONDS,
          TimeFormatStyle.FULL, true);
      assertEquals("3 minutes 34 seconds", formatter.format(214000, config));
      assertEquals("5 minutes 0 seconds", formatter.format(300000, config));
      assertEquals("0 minutes 59 seconds", formatter.format(59000, config));
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
      TimeFormatConfiguration config = new TimeFormatConfiguration(TimeSelection.HOURS_MINUTES_AND_SECONDS,
          TimeFormatStyle.FULL, true);
      assertEquals("0 hours 3 minutes 34 seconds", formatter.format(214000, config));
      assertEquals("1 hours 5 minutes 0 seconds", formatter.format(3900000, config));
      assertEquals("3 hours 0 minutes 59 seconds", formatter.format(10859000, config));
      assertEquals("5 hours 3 minutes 34 seconds", formatter.format(18214000, config));
    }
  }

  /**
   * Extend the previous code to show/hide the unit if the value is zero. 3 minutes 34 seconds, 1 hours 5 minutes, 3
   * hours 59 seconds, 3 hours 2 minutes 59 seconds
   */
  @Nested
  class Task4 {

    @Test
    void format_testHideUnitIfZero() {
      TimeFormatter formatter = new TimeFormatter();
      TimeFormatConfiguration config = new TimeFormatConfiguration(TimeSelection.HOURS_MINUTES_AND_SECONDS,
          TimeFormatStyle.FULL, false);
      assertEquals("3 minutes 34 seconds", formatter.format(214000, config));
      assertEquals("1 hours 5 minutes", formatter.format(3900000, config));
      assertEquals("3 hours 59 seconds", formatter.format(10859000, config));
      assertEquals("3 hours 2 minutes 59 seconds", formatter.format(10979000, config));
    }
  }

  /**
   * Modify the previous code to be able to format the output in two different ways. 1. As previously: 2 hours 3 minutes
   * 34 seconds 2. Short form: 2h 3m 34s
   */
  @Nested
  class Task5 {

    @Test
    void format_testFullFormat() {
      TimeFormatter formatter = new TimeFormatter();
      TimeFormatConfiguration config = new TimeFormatConfiguration(TimeSelection.HOURS_MINUTES_AND_SECONDS,
          TimeFormatStyle.FULL, true);
      assertEquals("2 hours 3 minutes 34 seconds", formatter.format(7414000, config));
    }

    @Test
    void format_testShortFormat() {
      TimeFormatter formatter = new TimeFormatter();
      TimeFormatConfiguration config = new TimeFormatConfiguration(TimeSelection.HOURS_MINUTES_AND_SECONDS,
          TimeFormatStyle.SHORT, false);
      assertEquals("2h 3m 34s", formatter.format(7414000, config));
    }
  }

  @Nested
  class DefaultConfiguration {

    @Test
    void format_defaultFull() {
      TimeFormatter formatter = new TimeFormatter();
      assertEquals("1 hours 5 minutes", formatter.format(3900000));
      assertEquals("2 hours 3 minutes 34 seconds", formatter.format(7414000, TimeFormatConfiguration.DEFAULT));
    }
  }

  /**
   * Tests for edge cases that have no clear requirements yet.
   */
  @Nested
  class UnsolvedEdgeCases {

    private static final long TWO_DAYS = 172800000;

    @Test
    void format_testZeroFullFormat() {
      TimeFormatter formatter = new TimeFormatter();
      TimeFormatConfiguration config = new TimeFormatConfiguration(TimeSelection.HOURS_MINUTES_AND_SECONDS,
          TimeFormatStyle.FULL, true);
      assertEquals("0 hours 0 minutes 0 seconds", formatter.format(0, config));
    }

    @Test
    void format_testZeroShortFormat() {
      TimeFormatter formatter = new TimeFormatter();
      TimeFormatConfiguration config = new TimeFormatConfiguration(TimeSelection.HOURS_MINUTES_AND_SECONDS,
          TimeFormatStyle.SHORT, true);
      assertEquals("0h 0m 0s", formatter.format(0, config));
    }

    @Test
    void format_testZeroFullFormat_noShowZero() {
      TimeFormatter formatter = new TimeFormatter();
      TimeFormatConfiguration config = new TimeFormatConfiguration(TimeSelection.HOURS_MINUTES_AND_SECONDS,
          TimeFormatStyle.FULL, false);
      assertEquals("", formatter.format(0, config));
    }

    @Test
    void format_testZeroShortFormat_noShowZero() {
      TimeFormatter formatter = new TimeFormatter();
      TimeFormatConfiguration config = new TimeFormatConfiguration(TimeSelection.HOURS_MINUTES_AND_SECONDS,
          TimeFormatStyle.SHORT, false);
      assertEquals("", formatter.format(0, config));
    }

    @Test
    void format_testNegativeFullFormat() {
      TimeFormatter formatter = new TimeFormatter();
      TimeFormatConfiguration config = new TimeFormatConfiguration(TimeSelection.HOURS_MINUTES_AND_SECONDS,
          TimeFormatStyle.FULL, true);
      assertEquals("0 hours 0 minutes -1 seconds", formatter.format(-1, config));
    }

    @Test
    void format_testNagativeShortFormat() {
      TimeFormatter formatter = new TimeFormatter();
      TimeFormatConfiguration config = new TimeFormatConfiguration(TimeSelection.HOURS_MINUTES_AND_SECONDS,
          TimeFormatStyle.SHORT, true);
      assertEquals("-1h -1m -1s", formatter.format(-3661000, config));
    }

    @Test
    void format_testOnlySeconds_moreThanAMinute() {
      TimeFormatter formatter = new TimeFormatter();
      TimeFormatConfiguration config = new TimeFormatConfiguration(TimeSelection.ONLY_SECONDS,
          TimeFormatStyle.SHORT, false);
      assertEquals("172800s", formatter.format(TWO_DAYS, config));
    }

    @Test
    void format_testMinutesAndSeconds_moreThanAHour() {
      TimeFormatter formatter = new TimeFormatter();
      TimeFormatConfiguration config = new TimeFormatConfiguration(TimeSelection.MINUTES_AND_SECONDS,
          TimeFormatStyle.SHORT, false);
      assertEquals("2880m", formatter.format(TWO_DAYS, config));
    }

    @Test
    void format_testHoursMinutesAndSeconds_moreThanADay() {
      TimeFormatter formatter = new TimeFormatter();
      TimeFormatConfiguration config = new TimeFormatConfiguration(TimeSelection.HOURS_MINUTES_AND_SECONDS,
          TimeFormatStyle.SHORT, false);
      assertEquals("48h", formatter.format(TWO_DAYS, config));
    }
  }

  /**
   * Tests for exception handling scenarios.
   */
  @Nested
  class ExceptionHandling {

    @Test
    void testFormatter_nullConfig() {
      TimeFormatter formatter = new TimeFormatter();
      NullPointerException exception = assertThrows(
          NullPointerException.class,
          () -> formatter.format(0, null)
      );
      assertEquals("Time format configuration is required.", exception.getMessage());
    }
  }
}
