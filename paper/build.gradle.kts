import net.minecrell.pluginyml.paper.PaperPluginDescription

plugins {
    alias(libs.plugins.run.paper)
    alias(libs.plugins.plugin.yml.paper)
}

val mainPackage = "${rootProject.group}.${rootProject.name.lowercase()}"

dependencies {
    implementation(projects.bukkit) {
        isTransitive = false
    }
    implementation(libs.command.api.paper)
}

tasks {
    build {
        dependsOn(shadowJar)
    }

    jar {
        archiveBaseName.set(rootProject.name)
    }

    shadowJar {
        dependsOn(":bukkit:shadowJar")
        archiveBaseName.set(rootProject.name)
        archiveClassifier.set("")

        // Shadow classes
        fun reloc(originPkg: String, targetPkg: String) = relocate(originPkg, "${mainPackage}.lib.${targetPkg}")
        reloc("dev.jorel.commandapi", "commandapi")
    }

    runServer {
        // Configure the Minecraft version for our task.
        minecraftVersion("1.21.5")

        // IntelliJ IDEA debugger setup: https://docs.papermc.io/paper/dev/debugging#using-a-remote-debugger
        jvmArgs("-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-DPaper.IgnoreJavaVersion=true", "-Dcom.mojang.eula.agree=true", "-DIReallyKnowWhatIAmDoingISwear", "-Dpaper.playerconnection.keepalive=6000")
        systemProperty("terminal.jline", false)
        systemProperty("terminal.ansi", true)

        // Plugins
        downloadPlugins {
            hangar("ViaVersion", "5.3.2")
            hangar("ViaBackwards", "5.3.2")
//            url("https://ci.citizensnpcs.co/job/Citizens2/lastStableBuild/artifact/dist/target/Citizens-2.0.38-b3791.jar")
        }
    }
}

paper { // Options: https://github.com/Minecrell/plugin-yml#bukkit
    main = "${mainPackage}.${rootProject.name}"
    loader = "${mainPackage}.${rootProject.name}PluginLoader"
    generateLibrariesJson = true

    // Plugin Information
    name = rootProject.name
    prefix = rootProject.name
    version = "${rootProject.version}"
    description = "${rootProject.description}"
    authors = listOf("darksaid98", "")
    contributors = listOf()
    apiVersion = "1.21"

    // Misc properties
    load = net.minecrell.pluginyml.bukkit.BukkitPluginDescription.PluginLoadOrder.POSTWORLD // STARTUP or POSTWORLD

    // Dependencies
    hasOpenClassloader = true
    bootstrapDependencies {}
    serverDependencies {
        // Hard depends
        register("Citizens") {
            load = PaperPluginDescription.RelativeLoadOrder.BEFORE
        }

        // Soft depends
        register("Towny") {
            load = PaperPluginDescription.RelativeLoadOrder.BEFORE
            required = false
        }
        register("Stewards") {
            load = PaperPluginDescription.RelativeLoadOrder.AFTER
            required = false
        }
    }
    provides = listOf()
}