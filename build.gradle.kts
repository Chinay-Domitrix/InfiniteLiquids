import org.gradle.api.JavaVersion.VERSION_17
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	java
	`maven-publish`
	kotlin("jvm") version "1.6.20-RC"
}
repositories {
	mavenLocal()
	maven { url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") }
	maven { url = uri("https://oss.sonatype.org/content/groups/public/") }
	maven { url = uri("https://repo.maven.apache.org/maven2/") }
	mavenCentral()
}
dependencies {
	compileOnly("org.spigotmc:spigot-api:1.18.1-R0.1-SNAPSHOT")
	implementation("org.jetbrains.kotlin:kotlin-stdlib:1.6.0")
}
group = "net.badbird5907"
version = "1.0-SNAPSHOT"
description = "InfiniteLiquids"
java.sourceCompatibility = VERSION_17
publishing { publications.create<MavenPublication>("maven") { from(components["java"]) } }
tasks.withType<JavaCompile>() { options.encoding = "UTF-8" }
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions { jvmTarget = "1.8" }
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions { jvmTarget = "1.8" }
