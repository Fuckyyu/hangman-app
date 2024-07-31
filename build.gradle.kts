plugins {
    application
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java") // или любой другой плагин, который должен применяться к подмодулям

    dependencies {
        implementation("org.projectlombok:lombok:1.18.34")
        annotationProcessor("org.projectlombok:lombok:1.18.34")

        testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
        testImplementation("org.mockito:mockito-core:5.12.0")
        testImplementation("org.mockito:mockito-inline:3.4.0")
    }
}
