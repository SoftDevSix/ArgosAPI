plugins {
    java
}

allprojects {
    group = "com.softdevsix.argos"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "application")

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(17)
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
