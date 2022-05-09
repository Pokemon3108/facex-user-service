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
    implementation(project(":service"))
    implementation ("org.jetbrains.kotlin:kotlin-stdlib")
    implementation ("com.squareup.okhttp3:okhttp:3.14.6")
    implementation ("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.+")
    implementation ("org.springframework.boot:spring-boot-starter-security")
    implementation ("io.jsonwebtoken:jjwt:0.9.1")

}
