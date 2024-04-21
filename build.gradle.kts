import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	alias(libs.plugins.spring.boot)
	alias(libs.plugins.dependency.management)
	alias(libs.plugins.kotlin.jvm)
	alias(libs.plugins.kotlin.spring)
	alias(libs.plugins.detekt)
}

group = "nl.jaysh"
version = "0.0.1"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
}

detekt {
	buildUponDefaultConfig = true
	allRules = false
}

dependencies {
	implementation(libs.bundles.spring)

	implementation(libs.jackson)
	implementation(libs.reactor.kotlin.extensions)
	implementation(libs.flyway)
	implementation(libs.kotlin.reflect)
	implementation(libs.kotlinx.coroutines.reactor)

	developmentOnly(libs.spring.boot.devtools)
	developmentOnly(libs.spring.boot.docker.compose)

	runtimeOnly(libs.postgres)
	runtimeOnly(libs.r2dbc.postgresql)

	testImplementation(libs.bundles.test)
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "21"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<Detekt>().configureEach {
	reports {
		html.required.set(true)
		xml.required.set(true)
		txt.required.set(true)
		sarif.required.set(true)
		md.required.set(true)
	}
}

tasks.withType<Detekt>().configureEach {
	jvmTarget = "21"
}
tasks.withType<DetektCreateBaselineTask>().configureEach {
	jvmTarget = "21"
}
