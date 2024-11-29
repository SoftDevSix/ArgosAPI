plugins {
    application
    jacoco
    alias(libs.plugins.sonarqube) apply true
    alias(libs.plugins.dependency.management) apply true
    alias(libs.plugins.springboot) apply true
    alias(libs.plugins.lombok) apply true
}

group = "com.softdevsix.argos"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

sonarqube {
    val sonarProjectKey = System.getenv("SONAR_PROJECT_KEY") ?: ""
    val sonarHostUrl = System.getenv("SONAR_HOST_URL") ?: ""
    val sonarToken = System.getenv("SONAR_TOKEN") ?: ""
    properties {
        property("sonar.projectKey", sonarProjectKey)
        property("sonar.host.url", sonarHostUrl)
        property("sonar.token", sonarToken)
        property("sonar.qualitygate.wait", "true")
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.springboot.starter.web.rest)
    implementation(libs.springboot.starter.web.jpa)
    implementation(libs.springboot.starter.web.security)
    implementation(libs.springboot.web)
    implementation(libs.springboot.json)
    implementation(libs.springboot.starter)
    implementation(libs.springdoc.openapi)
    implementation(libs.flyway.core)
    implementation(libs.flyway.database.postgresql)
    implementation(libs.jackson.core)
    implementation(libs.jackson.databind)
    implementation(libs.jackson.annotations)
    developmentOnly(libs.springboot.devtools)
    runtimeOnly(libs.postgresql)
    testImplementation(libs.springboot.starter.test)
    testImplementation (libs.mockito.core)
    testRuntimeOnly(libs.junit.launcher)
}

application {
    mainClass.set("com.softdevsix.api.ArgosApplication")
}

tasks.withType<Test> {
    useJUnitPlatform()
}


jacoco {
    toolVersion = "0.8.8"
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        csv.required.set(false)
        html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
    }
}

tasks.jacocoTestCoverageVerification {
    dependsOn(tasks.jacocoTestReport)
    violationRules {
        rule {
            limit {
                minimum = "0.8".toBigDecimal()
            }
        }
    }
}
