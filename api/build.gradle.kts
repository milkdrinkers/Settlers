plugins {
    alias(libs.plugins.maven.deployer)
}

val mainPackage = "${rootProject.group}.${rootProject.name.lowercase()}"

dependencies {
}

deployer {
    release {
        version.set("${rootProject.version}")
        description.set(rootProject.description.orEmpty())
    }

    projectInfo {
        groupId = "io.github.milkdrinkers"
        artifactId = "settlers"
        version = "${rootProject.version}"

        name = rootProject.name
        description = rootProject.description.orEmpty()
        url = "https://github.com/milkdrinkers/settlers"

        scm {
            connection = "scm:git:git://github.com/milkdrinkers/Settlers.git"
            developerConnection = "scm:git:ssh://github.com:milkdrinkers/Settlers.git"
            url = "https://github.com/milkdrinkers/Settlers"
        }

        license({
            name = "GNU General Public License Version 3"
            url = "https://www.gnu.org/licenses/gpl-3.0.en.html#license-text"
        })

        developer({
            name.set("darksaid98")
            email.set("darksaid9889@gmail.com")
            url.set("https://github.com/darksaid98")
            organization.set("Milkdrinkers")
        })
    }

    content {
        component {
            fromJava()
        }
    }

    centralPortalSpec {
        auth.user.set(secret("MAVEN_USERNAME"))
        auth.password.set(secret("MAVEN_PASSWORD"))
    }

    signing {
        key.set(secret("GPG_KEY"))
        password.set(secret("GPG_PASSWORD"))
    }
}

/*
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
}*/
