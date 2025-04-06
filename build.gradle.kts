import java.time.Instant

plugins {
    `java-library`
    alias(libs.plugins.shadow) apply false

    eclipse
    idea
}

applyCustomVersion()

tasks {
    jar {
        enabled = false
    }
}

subprojects {
    apply(plugin = "java-library")
    apply(plugin = rootProject.libs.plugins.shadow.get().pluginId)

    project.version = rootProject.version
    project.description = rootProject.description

    repositories {
        maven("https://repo.papermc.io/repository/maven-public/")
        maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        maven("https://maven.citizensnpcs.co/repo")
        maven("https://repo.opencollab.dev/main/")
        mavenCentral()
    }

    dependencies {
        compileOnly(rootProject.libs.annotations)
        annotationProcessor(rootProject.libs.annotations)

        compileOnly(rootProject.libs.paper.api)
        compileOnly(rootProject.libs.citizens.plugin) {
            exclude ("*", "*")
        }
    }

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    }

    tasks {
        compileJava {
            options.release.set(21)
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
}

fun applyCustomVersion() {
    // Apply custom version arg or append snapshot version
    val ver = properties["altVer"]?.toString() ?: "${rootProject.version}-SNAPSHOT.${Instant.now().epochSecond}"

    // Strip prefixed "v" from version tag
    rootProject.version = (if (ver.first().equals('v', true)) ver.substring(1) else ver.uppercase()).uppercase()
}