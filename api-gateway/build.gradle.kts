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

extra["springCloudVersion"] = "2023.0.3"

dependencies {
//    // Spring Boot and Spring Cloud Gateway
//    implementation("org.springframework.cloud:spring-cloud-starter-gateway-mvc")
//   // implementation("org.springframework.boot:spring-boot-starter-web") // Add for MVC support
//    implementation("org.springframework.boot:spring-boot-oauth2-resource-server:3.3.3")
//    // Optional: Eureka for Service Discovery
////    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
//    implementation("org.springframework.boot:spring-boot-starter-security")
//
//   // implementation("org.springframework.cloud:spring-cloud-starter-gateway-mvc")
//    implementation("org.springframework.boot:spring-boot-starter-web") // Use Spring MVC
//    // Lombok
//    compileOnly("org.projectlombok:lombok")
//    annotationProcessor("org.projectlombok:lombok")
//
//    // Testing
//    testImplementation("org.springframework.boot:spring-boot-starter-test")
//   // testImplementation("org.springframework.cloud:spring-cloud-starter-test") // Spring Cloud-specific tests
//    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    // https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-starter-webflux-ui
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")
// https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-starter-webmvc-api
    testImplementation("org.springdoc:springdoc-openapi-starter-webmvc-api:2.6.0")
    implementation("org.springframework.cloud:spring-cloud-starter-gateway-mvc")
    compileOnly("org.projectlombok:lombok")
    compileOnly("jakarta.servlet:jakarta.servlet-api:6.1.0")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server:3.3.3")
    implementation("org.springframework.boot:spring-boot-starter-security:3.3.3")


}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}


tasks.withType<Test> {
    useJUnitPlatform()
}
