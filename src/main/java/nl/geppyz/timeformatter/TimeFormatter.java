package nl.geppyz.timeformatter;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import nl.geppyz.timeformatter.configuration.TimeFormatConfiguration;
import nl.geppyz.timeformatter.configuration.TimeFormatStyle;
import nl.geppyz.timeformatter.configuration.TimeUnitLabel;

/**
 * Provides methods for formatting milliseconds into human-readable strings based on specified configurations.
 */
public class TimeFormatter {

  /**
   * Formats a given duration in milliseconds into a human-readable string representation using the default time format
   * configuration.
   * <p>
   * The method leverages the predefined {@code TimeFormatConfiguration.DEFAULT} to determine the time unit selection,
   * whether zero-value units should be displayed, and the format style.
   *
   * @param millis the duration in milliseconds to be formatted
   * @return a string representation of the formatted duration based on the default configuration
   */
  protected String format(long millis) {
    return format(millis, TimeFormatConfiguration.DEFAULT);
  }

  /**
   * Formats a given duration in milliseconds into a human-readable string representation based on the provided
   * configuration. The format is determined by the configured time unit selection (HOURS, MINUTES, or SECONDS) and
   * whether zero-value units should be displayed.
   *
   * @param millis        the duration in milliseconds to be formatted
   * @param configuration the configuration specifying the time unit selection, whether to include zero-value units and
   *                      what format style to use
   * @return a string representation of the formatted duration based on the given configuration
   */
  protected String format(long millis, TimeFormatConfiguration configuration) {
    Objects.requireNonNull(configuration, "Time format configuration is required.");
    Duration duration = Duration.ofMillis(millis);
    List<String> timeStringParts = new ArrayList<>();
    switch (configuration.timeSelection()) {
      case HOURS_MINUTES_AND_SECONDS -> {
        addTimePart(timeStringParts, duration.toHours(), TimeUnitLabel.HOURS, configuration);
        addTimePart(timeStringParts, duration.toMinutesPart(), TimeUnitLabel.MINUTES, configuration);
        addTimePart(timeStringParts, duration.toSecondsPart(), TimeUnitLabel.SECONDS, configuration);
      }
      case MINUTES_AND_SECONDS -> {
        addTimePart(timeStringParts, duration.toMinutes(), TimeUnitLabel.MINUTES, configuration);
        addTimePart(timeStringParts, duration.toSecondsPart(), TimeUnitLabel.SECONDS, configuration);
      }
      case ONLY_SECONDS -> addTimePart(timeStringParts, duration.toSeconds(), TimeUnitLabel.SECONDS, configuration);
    }
    return String.join(" ", timeStringParts);
  }

  /**
   * Determines whether a time part should be included in the formatted output based on its value and the configuration
   * provided.
   *
   * @param value  the numerical value of the time unit
   * @param config the configuration specifying whether zero-value units should be displayed
   * @return true if the time part should be shown (non-zero value or configured to show zero-value units), otherwise
   * false
   */
  private boolean shouldShowTimePart(long value, TimeFormatConfiguration config) {
    return value != 0 || config.showZeroUnits();
  }

  /**
   * Adds a formatted time part to the provided list based on the given value, time unit, and whether zero-value units
   * should be displayed.
   *
   * @param timeStringParts the list to which the formatted time part will be added
   * @param value           the numerical value of the time unit
   * @param unitLabel       the {@link TimeUnitLabel} representing the time unit (e.g., seconds, minutes, hours)
   * @param configuration   the configuration specifying whether zero-value units should be displayed and what format
   *                        style to use
   */
  private void addTimePart(List<String> timeStringParts, long value, TimeUnitLabel unitLabel,
      TimeFormatConfiguration configuration) {
    if (shouldShowTimePart(value, configuration)) {
      TimeFormatStyle formatStyle = configuration.timeFormatStyle();
      timeStringParts.add(String.format(formatStyle.getPattern(), value, unitLabel.getLabel(formatStyle)));
    }
  }
}
