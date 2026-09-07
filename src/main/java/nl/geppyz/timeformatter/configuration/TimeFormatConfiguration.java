package nl.geppyz.timeformatter.configuration;

/**
 * A configuration for formatting time into human-readable strings.
 *
 * This record encapsulates the following properties:
 *
 * - {@code timeSelection}: Specifies which time units (hours, minutes, seconds) should be
 *   included in the formatted output.
 * - {@code timeFormatStyle}: Defines the style (e.g., full labels or short abbreviations)
 *   used when representing time units.
 * - {@code showZeroUnits}: Determines whether time units with a value of zero
 *   (e.g., 0 seconds) should be included in the output.
 *
 * This class is utilized by time formatting utilities, such as the {@code TimeFormatter},
 * to provide flexible and customizable formatting options. Users can either use predefined
 * configurations (e.g., {@link #DEFAULT}) or define their own by specifying the desired
 * options.
 */
public record TimeFormatConfiguration(TimeSelection timeSelection, TimeFormatStyle timeFormatStyle,
                                      boolean showZeroUnits) {

  /**
   * The default configuration for formatting time.
   *
   * This configuration specifies the use of {@link TimeSelection#HOURS},
   * the {@link TimeFormatStyle#FULL} format style, and disables the
   * display of zero-value units within the formatted output.
   *
   * - {@link TimeSelection#HOURS}: Includes hours, minutes, and seconds in the output.
   * - {@link TimeFormatStyle#FULL}: Uses verbose labels for time units (e.g., "2 hours 3 minutes").
   * - Zero-value units are not displayed (e.g., "0 seconds" will be omitted).
   */
  public static final TimeFormatConfiguration DEFAULT =
      new TimeFormatConfiguration(TimeSelection.HOURS, TimeFormatStyle.FULL, false);
}
