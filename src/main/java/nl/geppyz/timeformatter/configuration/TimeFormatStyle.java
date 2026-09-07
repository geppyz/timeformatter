package nl.geppyz.timeformatter.configuration;

/**
 * Defines the styles for formatting time into human-readable strings. Each style determines how
 * time units are represented within the formatted output.
 *
 * The enum provides the following formatting styles:
 *
 * - FULL: Represents time in a verbose format, including the full label of each time unit (e.g., "2 hours 3 minutes 34 seconds").
 * - SHORT: Represents time in a compact format, utilizing abbreviations for time units (e.g., "2h 3m 34s").
 *
 * Each formatting style includes a predefined pattern that can be used to structure the generated
 * time string. The usage of these patterns allows for flexible and consistent formatting when
 * integrating with other time-related components and configurations.
 */
public enum TimeFormatStyle {
  /**
   * Full format: 2 hours 3 minutes 34 seconds
   */
  FULL("%d %s"),
  /**
   * Short format: 2h 3m 34s
   */
  SHORT("%d%s");

  private final String pattern;

  TimeFormatStyle(String pattern) {
    this.pattern = pattern;
  }

  public String getPattern() {
    return pattern;
  }
}
