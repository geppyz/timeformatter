# timeformatter
The timeformatter library formats durations in milliseconds into readable time strings (such as hours, minutes, and seconds) with configurable formatting styles and zero-unit display options.

## Dependency Configuration
Read [Working with GithubPackages with Maven](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry)

Read [Working with GithubPackages with Gradle](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-gradle-registry)

### With Maven:
```Maven
<dependency>
  <groupId>nl.geppyz.timeformatter</groupId>
  <artifactId>timeformatter</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```
### With Gradle:
```Gradle
implementation 'nl.geppyz.timeformatter:timeformatter:1.0-SNAPSHOT'
```

### Usage Example 
```JAVA
import nl.geppyz.timeformatter.TimeFormatter;
import nl.geppyz.timeformatter.configuration.TimeFormatConfiguration;
import nl.geppyz.timeformatter.configuration.TimeFormatStyle;
import nl.geppyz.timeformatter.configuration.TimeSelection;

public class Main {
    public static void main(String[] args) {
        TimeFormatter formatter = new TimeFormatter();
        long millis = 7414000L; // 2 hours, 3 minutes, 34 seconds

        // Example 1: Full format with hours, minutes, and seconds
        TimeFormatConfiguration fullConfig = new TimeFormatConfiguration(
                TimeSelection.HOURS,
                TimeFormatStyle.FULL,
                true // showZeroUnits
        );
        String fullResult = formatter.format(millis, fullConfig);
        System.out.println(fullResult);
        // Output: "2 hours 3 minutes 34 seconds"

        // Example 2: Compact / Short format hiding zero-value units
        TimeFormatConfiguration shortConfig = new TimeFormatConfiguration(
                TimeSelection.HOURS,
                TimeFormatStyle.SHORT,
                false // showZeroUnits
        );
        String shortResult = formatter.format(millis, shortConfig);
        System.out.println(shortResult);
        // Output: "2h 3m 34s"

        // Example 3: Minutes-only selection hiding zero units
        long shortDurationMillis = 214000L; // 3 minutes, 34 seconds
        TimeFormatConfiguration minutesConfig = new TimeFormatConfiguration(
                TimeSelection.MINUTES,
                TimeFormatStyle.FULL,
                false // showZeroUnits
        );
        String minutesResult = formatter.format(shortDurationMillis, minutesConfig);
        System.out.println(minutesResult);
        // Output: "3 minutes 34 seconds"
    }
}
```

#### Configuration Parameters
When instantiating `TimeFormatConfiguration(TimeSelection timeSelection, TimeFormatStyle timeFormatStyle, boolean showZeroUnits)`:

1. `TimeSelection`
    - `TimeSelection.HOURS`: Formats hours, minutes, and seconds.
    - `TimeSelection.MINUTES`: Formats minutes and seconds.
    - `TimeSelection.SECONDS`: Formats seconds only. Any remaining sub-second milliseconds are truncated.

2. `TimeFormatStyle`
    - `TimeFormatStyle.FULL`: Uses full unit names (e.g., `2 hours 3 minutes 34 seconds`).
    - `TimeFormatStyle.SHORT`: Uses abbreviated units (e.g., `2h 3m 34s`).

3. `showZeroUnits` (`boolean`)
    - `true`: Keeps units that have a value of `0` (e.g., `0 hours 3 minutes 34 seconds` or `5 minutes 0 seconds`).
    - `false`: Omits units that have a value of `0` (e.g., `3 minutes 34 seconds` or `1 hours 5 minutes`).

## Prerequisites
- Java 21 JDK
- Github access
- An IDE of choice

## Local development
- Clone the repository
- Open the project in your IDE
- Run the tests with gradle `./gradlew clean test`
- Use a Test Driver Development approach when developing new features

## Github Workflows
- Dependabot is configured to automatically update dependencies
- Github Actions is configured to run tests on every push
- On push into main a new release is created to [GitHub Packages](https://github.com/geppyz/timeformatter/packages)
- On push into main JavaDoc will be generated and published to [GitHub Pages](https://geppyz.github.io/timeformatter/)