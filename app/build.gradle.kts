plugins {
    // Поддержка запуска из командной строки
    application
}

repositories {
    // Подключаем автоматическую работу с репозиторием Maven Central
    mavenCentral()
}

dependencies {
    implementation("info.picocli:picocli:4.7.7")
    implementation("tools.jackson.core:jackson-databind:3.2.4")
    testImplementation("org.junit.jupiter:junit-jupiter-api:6.1.3")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:6.1.3")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    annotationProcessor("info.picocli:picocli-codegen:4.7.7")
}

tasks.test {
    useJUnitPlatform()
    workingDir = file("src/test/resources")
}

// Для плагина application указываем главный класс приложения
application {
    // Входная точка
    mainClass.set("hexlet.code.App")
}