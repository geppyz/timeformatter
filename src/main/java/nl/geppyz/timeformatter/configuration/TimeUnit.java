package nl.geppyz.timeformatter.configuration;

public enum TimeUnit {

  SECONDS("seconds"),
  MINUTES("minutes"),
  HOURS("hours");

  private final String label;

  TimeUnit(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }
}
