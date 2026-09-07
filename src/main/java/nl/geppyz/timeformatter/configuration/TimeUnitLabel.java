package nl.geppyz.timeformatter.configuration;

/**
 * Represents a unit of time with both full and short labels. This enum provides methods to retrieve the labels in
 * various formats for a given {@link TimeFormatStyle}.
 */
public enum TimeUnitLabel {

  SECONDS("seconds", "s"),
  MINUTES("minutes", "m"),
  HOURS("hours", "h");

  private final String fullLabel;
  private final String shortLabel;

  TimeUnitLabel(String fullLabel, String shortLabel) {
    this.fullLabel = fullLabel;
    this.shortLabel = shortLabel;
  }

  public String getLabel(TimeFormatStyle style) {
    return switch (style) {
      case FULL -> fullLabel;
      case SHORT -> shortLabel;
    };
  }
}
