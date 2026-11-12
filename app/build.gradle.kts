plugins {
    // Поддержка запуска из командной строки
    application
    id("com.diffplug.spotless") version "8.3.0"
    jacoco
}

repositories {
    // Подключаем автоматическую работу с репозиторием Maven Central
    mavenCentral()
}

spotless {
    java {
        // Сортировка импортов
        importOrder()
        // Удаление неиспользуемых импортов
        removeUnusedImports()
        // Форматирование по стандарту Google (AOSP variant - Android Open Source Project, часто используется как более строгий вариант)
        googleJavaFormat("1.35.0").aosp()
        // Исправление аннотаций
        formatAnnotations()
        // Замена табуляции на 4 пробела (на всякий случай, хотя google-java-format обычно сам справляется)
    }
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
    // После тестов автоматически запускаем проверку покрытия
    finalizedBy(tasks.jacocoTestCoverageVerification)
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = "0.80".toBigDecimal()
            }
        }
    }
}

// Для плагина application указываем главный класс приложения
application {
    // Входная точка
    mainClass.set("hexlet.code.App")
}