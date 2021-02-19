plugins {
    java
    jacoco // For Test coverage
}

group = "de.dhbw.mosbach.se"
version = "1.0"

repositories {
    mavenCentral()
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)

    reports.csv.isEnabled = true
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.7.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
}

tasks.withType(Test::class.java) {
    useJUnitPlatform()
}

tasks.check {
    dependsOn(tasks.jacocoTestReport)
}
