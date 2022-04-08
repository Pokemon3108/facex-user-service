plugins {
    id ("org.jetbrains.kotlin.jvm")
    id ("org.jetbrains.kotlin.plugin.jpa") version "1.5.21"
}

group "by.daryazaleuskaya"
version "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation ("org.jetbrains.kotlin:kotlin-stdlib")
    implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation ("org.springframework.data:spring-data-mongodb")
}
