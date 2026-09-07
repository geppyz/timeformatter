package nl.geppyz.timeformatter.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TimeFormatConfigurationTest {

  /**
   * Tests for exception handling scenarios.
   */
  @Nested
  class ExceptionHandling {

    @Test
    void testTimeFormatConfiguration_nullConfig() {
      NullPointerException exception = assertThrows(
          NullPointerException.class,
          () -> new TimeFormatConfiguration(null, null, false));
      assertEquals("Time selection settings cannot be null.", exception.getMessage());
    }

    @Test
    void testTimeFormatConfiguration_nullTimeSelection() {
      NullPointerException exception = assertThrows(
          NullPointerException.class,
          () -> new TimeFormatConfiguration(null, TimeFormatStyle.FULL, false));
      assertEquals("Time selection settings cannot be null.", exception.getMessage());
    }

    @Test
    void testTimeFormatConfiguration_nullFormatStyle() {
      NullPointerException exception = assertThrows(
          NullPointerException.class,
          () -> new TimeFormatConfiguration(TimeSelection.HOURS_MINUTES_AND_SECONDS, null, false));
      assertEquals("Time format style cannot be null.", exception.getMessage());
    }
  }

  /**
   * Tests for the default configuration settings.
   */
  @Nested
  class DefaultConfig {

    private static final TimeFormatConfiguration defaultConfig = TimeFormatConfiguration.DEFAULT;

    @Test
    void timeSelection() {
      assertEquals(TimeSelection.HOURS_MINUTES_AND_SECONDS, defaultConfig.timeSelection());
    }

    @Test
    void timeFormatStyle() {
      assertEquals(TimeFormatStyle.FULL, defaultConfig.timeFormatStyle());
    }

    @Test
    void showZeroUnits() {
      assertFalse(defaultConfig.showZeroUnits());
    }
  }

}