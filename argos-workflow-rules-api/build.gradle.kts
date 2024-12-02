plugins {
    jacoco
    alias(libs.plugins.springboot) apply true
    alias(libs.plugins.dependency.management) apply true
    alias(libs.plugins.sonarqube) apply true
    alias(libs.plugins.lombok) apply true
}

dependencies {
    implementation(libs.springboot.starter.web.jpa)
    implementation(libs.springboot.web)
    implementation(libs.springdoc.openapi)
    developmentOnly(libs.springboot.devtools)
    runtimeOnly(libs.postgresql)
    testImplementation(libs.springboot.starter.test)
    testImplementation(libs.h2database)
    testRuntimeOnly(libs.junit.launcher)
}

application {
    mainClass.set("com.softdevsix.argos.ArgosRulesApplication")
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required = true
        csv.required = false
        html.required = true
    }
}

sonar {
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
