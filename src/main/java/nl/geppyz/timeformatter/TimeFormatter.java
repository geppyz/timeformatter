package nl.geppyz.timeformatter;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import nl.geppyz.timeformatter.configuration.TimeFormatConfiguration;
import nl.geppyz.timeformatter.configuration.TimeUnit;

public class TimeFormatter {

  /**
   * Formats a given duration in milliseconds into a human-readable string representation based on the provided
   * configuration. The format is determined by the configured time unit selection (HOURS, MINUTES, or SECONDS)
   * and whether zero-value units should be displayed.
   *
   * @param millis the duration in milliseconds to be formatted
   * @param configuration the configuration specifying the time unit selection and whether to include zero-value units
   * @return a string representation of the formatted duration based on the given configuration
   */
  protected String format(long millis, TimeFormatConfiguration configuration) {
    Duration duration = Duration.ofMillis(millis);
    List<String> timeStringParts = new ArrayList<>();
    switch (configuration.timeSelection()) {
      case HOURS -> {
        addTimePart(timeStringParts, duration.toHours(), TimeUnit.HOURS, configuration.showZeroUnits());
        addTimePart(timeStringParts, duration.toMinutesPart(), TimeUnit.MINUTES, configuration.showZeroUnits());
        addTimePart(timeStringParts, duration.toSecondsPart(), TimeUnit.SECONDS, configuration.showZeroUnits());
      }
      case MINUTES -> {
        addTimePart(timeStringParts, duration.toMinutes(), TimeUnit.MINUTES, configuration.showZeroUnits());
        addTimePart(timeStringParts, duration.toSecondsPart(), TimeUnit.SECONDS, configuration.showZeroUnits());
      }
      case SECONDS -> addTimePart(timeStringParts, duration.toSecondsPart(), TimeUnit.SECONDS, configuration.showZeroUnits());
    }
    return String.join(" ", timeStringParts);
  }

  /**
   * Adds a formatted time part to the provided list based on the given value, time unit,
   * and whether zero-value units should be displayed.
   *
   * @param timeStringParts the list to which the formatted time part will be added
   * @param value the numerical value of the time unit
   * @param unit the {@link TimeUnit} representing the time unit (e.g., seconds, minutes, hours)
   * @param showZeroUnits a flag indicating whether to include units with a value of zero
   */
  private void addTimePart(List<String> timeStringParts, long value, TimeUnit unit, boolean showZeroUnits) {
    if (value != 0 || showZeroUnits) {
      timeStringParts.add(String.format("%d %s", value, unit.getLabel()));
    }
  }
}
