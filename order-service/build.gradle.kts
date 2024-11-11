plugins {
    java
    id("org.springframework.boot") version "3.3.3"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "ca.gbc"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
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
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-database-postgresql")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("org.postgresql:postgresql")
    annotationProcessor("org.projectlombok:lombok")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation("org.springframework.cloud:spring-cloud-starter-contract-stub-runner")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql")
    testImplementation("io.rest-assured:rest-assured:5.3.0") // RestAssured dependency for testing
    runtimeOnly("org.postgresql:postgresql")
    testImplementation ("org.springframework.boot:spring-boot-starter-test")
    testImplementation ("org.testcontainers:junit-jupiter")
    testImplementation ("org.testcontainers:postgresql")
    testImplementation ("io.rest-assured:rest-assured")
    testImplementation ("jakarta.servlet:jakarta.servlet-api:5.0.0")
    testImplementation ("com.github.tomakehurst:wiremock-jre8-standalone:2.35.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation ("org.springframework.cloud:spring-cloud-starter-contract-stub-runner")
    testImplementation ("com.github.tomakehurst:wiremock-jre8:2.35.0")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:2023.0.3")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}