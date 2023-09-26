Reproducer project for https://issues.apache.org/jira/browse/OPENJPA-2915

Run tests to verify:
    ./gradlew check

To make it work, use commons-dbcp2 `2.9.0` in `build.gradle`

To show the issue, use commons-dbcp2 `2.10.0` in `build.gradle`
