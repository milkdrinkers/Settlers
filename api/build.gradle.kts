import java.time.Instant

plugins {
    `java-library`
    `maven-publish`
    eclipse
    idea
}

group = rootProject.group
version = rootProject.version
description = rootProject.description

val mainPackage = "${project.group}.${project.name.lowercase()}"
applyCustomVersion()

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21)) // Configure the java toolchain. This allows gradle to auto-provision JDK 17 on systems that only have JDK 8 installed for example.
    withJavadocJar()
    withSourcesJar()
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly(libs.annotations)
    annotationProcessor(libs.annotations)

    compileOnly(libs.paper.api)
}

tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.compilerArgs.addAll(arrayListOf("-Xlint:all", "-Xlint:-processing", "-Xdiags:verbose"))
    }

    javadoc {
        isFailOnError = false
        val options = options as StandardJavadocDocletOptions
        options.encoding = Charsets.UTF_8.name()
        options.overview = "src/main/javadoc/overview.html"
        options.windowTitle = "${rootProject.name} Javadoc"
        options.tags("apiNote:a:API Note:", "implNote:a:Implementation Note:", "implSpec:a:Implementation Requirements:")
        options.addStringOption("Xdoclint:none", "-quiet")
        options.use()
    }

    processResources {
        filteringCharset = Charsets.UTF_8.name()
    }
}

fun applyCustomVersion() {
    // Apply custom version arg or append snapshot version
    val ver = properties["altVer"]?.toString() ?: "${rootProject.version}-SNAPSHOT-${Instant.now().epochSecond}"

    // Strip prefixed "v" from version tag
    rootProject.version = (if (ver.first().equals('v', true)) ver.substring(1) else ver.uppercase()).uppercase()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "${rootProject.group}"
            artifactId = "settlers-api"
            version = "${rootProject.version}"

            pom {
                name.set("Settlers")
                description.set(rootProject.description.orEmpty())
                url.set("https://github.com/milkdrinkers/Settlers")
                licenses {
                    license {
                        name.set("GPL-v3.0")
                        url.set("http://www.gnu.org/licenses/gpl-3.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("darksaid98")
                        name.set("darksaid98")
                        organization.set("Milkdrinkers")
                        organizationUrl.set("https://github.com/milkdrinkers")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/milkdrinkers/Settlers.git")
                    developerConnection.set("scm:git:ssh://github.com:milkdrinkers/Settlers.git")
                    url.set("https://github.com/milkdrinkers/Settlers")
                }
            }

            from(components["java"])
        }
    }

    repositories {
        maven {
            name = "releases"
            url = uri("https://maven.athyrium.eu/releases")
            credentials {
                username = System.getenv("MAVEN_USERNAME")
                password = System.getenv("MAVEN_PASSWORD")
            }
        }
        maven {
            name = "snapshots"
            url = uri("https://maven.athyrium.eu/snapshots")
            credentials {
                username = System.getenv("MAVEN_USERNAME")
                password = System.getenv("MAVEN_PASSWORD")
            }
        }
    }
}