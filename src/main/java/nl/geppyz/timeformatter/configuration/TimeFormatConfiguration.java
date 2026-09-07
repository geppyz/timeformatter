package nl.geppyz.timeformatter.configuration;

import java.util.Objects;

/**
 * A configuration for formatting time into human-readable strings.
 * <p>
 * This record encapsulates the following properties:
 * <p>
 * - {@code timeSelection}: Specifies which time units (hours, minutes, seconds) should be included in the formatted
 * output. - {@code timeFormatStyle}: Defines the style (e.g., full labels or short abbreviations) used when
 * representing time units. - {@code showZeroUnits}: Determines whether time units with a value of zero (e.g., 0
 * seconds) should be included in the output.
 * <p>
 * This class is utilized by time formatting utilities, such as the {@code TimeFormatter}, to provide flexible and
 * customizable formatting options. Users can either use predefined configurations (e.g., {@link #DEFAULT}) or define
 * their own by specifying the desired options.
 */
public record TimeFormatConfiguration(TimeSelection timeSelection, TimeFormatStyle timeFormatStyle,
                                      boolean showZeroUnits) {

  /**
   * Compact constructor enforcing non-null requirements.
   */
  public TimeFormatConfiguration {
    Objects.requireNonNull(timeSelection, "Time selection settings cannot be null.");
    Objects.requireNonNull(timeFormatStyle, "Time format style cannot be null.");
  }

  /**
   * The default configuration for formatting time.
   * <p>
   * This configuration specifies the use of {@link TimeSelection#HOURS_MINUTES_AND_SECONDS}, the
   * {@link TimeFormatStyle#FULL} format style, and disables the display of zero-value units within the formatted
   * output.
   * <p>
   * - {@link TimeSelection#HOURS_MINUTES_AND_SECONDS}: Includes hours, minutes, and seconds in the output. -
   * {@link TimeFormatStyle#FULL}: Uses verbose labels for time units (e.g., "2 hours 3 minutes"). - Zero-value units
   * are not displayed (e.g., "0 seconds" will be omitted).
   */
  public static final TimeFormatConfiguration DEFAULT =
      new TimeFormatConfiguration(TimeSelection.HOURS_MINUTES_AND_SECONDS, TimeFormatStyle.FULL, false);
}
