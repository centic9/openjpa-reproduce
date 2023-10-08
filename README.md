Reproducer project for https://issues.apache.org/jira/browse/OPENJPA-2915

Run tests to verify:
    ./gradlew check

The project now shows the workaround by using a different property in 
persistence.xml

# Original reproducer

In order to reproduce the problem, change `MaxWaitMillis` in `persistence.yml`
back to the original `MaxWait`

To make it work then, use commons-dbcp2 `2.9.0` in `build.gradle`

To show the issue, use commons-dbcp2 `2.10.0` in `build.gradle`
