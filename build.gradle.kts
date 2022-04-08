import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.5.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.5.21"
    kotlin("plugin.spring") version "1.5.21"
}

repositories {
    mavenCentral()
}

allprojects {
    group = "by.daryazaleuskaya"
    version =  "1.0-SNAPSHOT"

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

}

dependencies {
    implementation ("org.jetbrains.kotlin:kotlin-stdlib")
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation ("org.springframework.boot:spring-boot-starter-web")
        compileOnly ("org.projectlombok:lombok:1.18.22")
//        implementation ("org.springframework.data:spring-data-mongodb:3.3.1")
        implementation("org.springframework.boot:spring-boot-starter-data-mongodb")

        testImplementation ("org.springframework.boot:spring-boot-starter-test")
    }

}
