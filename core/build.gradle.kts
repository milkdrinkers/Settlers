plugins {
    alias(libs.plugins.run.paper)
    alias(libs.plugins.plugin.yml)
}

val mainPackage = "${rootProject.group}.${rootProject.name.lowercase()}"

dependencies {
    api(projects.api)

    implementation(libs.crate.api)
    implementation(libs.crate.yaml)
    implementation(libs.colorparser) {
        exclude("net.kyori")
    }
    implementation(libs.command.api)

    // Plugin Dependencies
    implementation(libs.bstats.bukkit)
}

tasks {
    build {
        dependsOn(shadowJar)
    }

    shadowJar {
        archiveBaseName.set(rootProject.name)
        archiveClassifier.set("")

        // Shadow classes
        fun reloc(originPkg: String, targetPkg: String) = relocate(originPkg, "${mainPackage}.lib.${targetPkg}")

        reloc("com.github.milkdrinkers.crate", "crate")
        reloc("com.github.milkdrinkers.colorparser", "colorparser")
        reloc("dev.jorel.commandapi", "commandapi")
        reloc("org.bstats", "bstats")

        minimize()
    }

    runServer {
        // Configure the Minecraft version for our task.
        minecraftVersion("1.21")

        // IntelliJ IDEA debugger setup: https://docs.papermc.io/paper/dev/debugging#using-a-remote-debugger
        jvmArgs("-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-DPaper.IgnoreJavaVersion=true", "-Dcom.mojang.eula.agree=true", "-DIReallyKnowWhatIAmDoingISwear", "-Dpaper.playerconnection.keepalive=6000")
        systemProperty("terminal.jline", false)
        systemProperty("terminal.ansi", true)
    }
}

bukkit { // Options: https://github.com/Minecrell/plugin-yml#bukkit
    main = "${mainPackage}.${rootProject.name}"

    // Plugin Information
    name = rootProject.name
    prefix = rootProject.name
    version = "${rootProject.version}"
    description = "${rootProject.description}"
    authors = listOf("darksaid98")
    contributors = listOf()
    apiVersion = "1.21"

    // Misc properties
    load = net.minecrell.pluginyml.bukkit.BukkitPluginDescription.PluginLoadOrder.POSTWORLD // STARTUP or POSTWORLD
    depend = listOf("Citizens")
    softDepend = listOf("Towny")
}