plugins {
    alias(libs.plugins.run.paper)
    alias(libs.plugins.plugin.yml.bukkit)
}

val mainPackage = "${rootProject.group}.${rootProject.name.lowercase()}"

dependencies {
    implementation(projects.api)

    implementation(libs.yaml)
    annotationProcessor(libs.configurate.`interface`.ap)
    implementation(libs.configurate.`interface`)
    implementation(libs.configurate.yaml)
    implementation(libs.colorparser) {
        exclude("net.kyori")
    }
    implementation(libs.command.api.bukkit)

    // Plugin Dependencies
    implementation(libs.bstats.bukkit)
}

tasks {
    build {
        dependsOn(shadowJar)
    }

    jar {
        archiveBaseName.set(rootProject.name)
    }

    shadowJar {
        dependsOn(":api:shadowJar")
        archiveBaseName.set(rootProject.name)
        archiveClassifier.set("")

        // Shadow classes
        fun reloc(originPkg: String, targetPkg: String) = relocate(originPkg, "${mainPackage}.lib.${targetPkg}")

        reloc("org.spongepowered.configurate", "configurate")
        reloc("org.yaml.snakeyaml", "snakeyaml") // Configurate dependency
        reloc("io.leangen.geantyref", "geantyref") // Configurate dependency
        reloc("io.github.milkdrinkers.colorparser", "colorparser")
        reloc("dev.jorel.commandapi", "commandapi")
        reloc("org.bstats", "bstats")
    }

    runServer {
        // Configure the Minecraft version for our task.
        minecraftVersion(libs.versions.paper.run.get())

        // IntelliJ IDEA debugger setup: https://docs.papermc.io/paper/dev/debugging#using-a-remote-debugger
        jvmArgs("-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-DPaper.IgnoreJavaVersion=true", "-Dcom.mojang.eula.agree=true", "-DIReallyKnowWhatIAmDoingISwear", "-Dpaper.playerconnection.keepalive=6000")
        systemProperty("terminal.jline", false)
        systemProperty("terminal.ansi", true)

        // Plugins
        downloadPlugins {
            hangar("ViaVersion", "5.3.2")
            hangar("ViaBackwards", "5.3.2")
            url("https://ci.citizensnpcs.co/job/Citizens2/lastStableBuild/artifact/dist/target/Citizens-2.0.38-b3791.jar")
        }
    }
}

bukkit { // Options: https://github.com/Minecrell/plugin-yml#bukkit
    main = "${mainPackage}.${rootProject.name}"

    // Plugin Information
    name = rootProject.name
    prefix = rootProject.name
    version = "${rootProject.version}"
    description = "${rootProject.description}"
    authors = listOf("darksaid98", "")
    contributors = listOf()
    apiVersion = libs.versions.paper.api.get().substringBefore("-R")

    // Misc properties
    load = net.minecrell.pluginyml.bukkit.BukkitPluginDescription.PluginLoadOrder.POSTWORLD // STARTUP or POSTWORLD
    depend = listOf("Citizens")
    softDepend = listOf("Towny")
}