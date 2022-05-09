plugins {
    id ("org.jetbrains.kotlin.jvm")
}

group "by.daryazaleuskaya"
version "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":persistence"))
    implementation ("org.jetbrains.kotlin:kotlin-stdlib")
    implementation ("org.springframework.data:spring-data-mongodb")
    implementation("org.springframework.security:spring-security-core:5.6.3")
}
