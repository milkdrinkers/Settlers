package io.github.milkdrinkers.settlers;

import io.github.milkdrinkers.settlers.listener.ListenerHandler;
import io.github.milkdrinkers.settlers.registry.RegistryHandler;
import io.github.milkdrinkers.settlers.trait.TraitRegistryHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;

public class SettlersPlugin extends JavaPlugin {
    public final static String DIR_DATA = "data";
    private static SettlersPlugin instance;

    // Handlers
    private final TraitRegistryHandler traitHandler = new TraitRegistryHandler();
    private final RegistryHandler registryHandler = new RegistryHandler();
    private final ListenerHandler listenerHandler = new ListenerHandler();

    // Used to decrease code repetition (Order matters, onDisable is reversed)
    private final List<? extends Reloadable> handlers = List.of(
        traitHandler,
        registryHandler,
        listenerHandler
    );

    @Override
    public void onLoad() {
        try {
            final File pluginDir = getInstance().getDataFolder();
            final File pluginDataDir = pluginDir.toPath().resolve(DIR_DATA).toFile();

            if (!pluginDir.exists() && !pluginDir.mkdirs())
                throw new RuntimeException("Failed to create plugin directory!");

            if (!pluginDataDir.exists() && !pluginDataDir.mkdirs())
                throw new RuntimeException("Failed to create plugin data directory!");

        } catch (RuntimeException e) {
            // TODO Log dir creation error before shutting down plugin
            getInstance().getServer().getPluginManager().disablePlugin(getInstance());
        }

        for (Reloadable handler : handlers)
            handler.onLoad(getInstance());
    }

    @Override
    public void onEnable() {
        SettlersAPI.setImplementation(getInstance());

        for (Reloadable handler : handlers)
            handler.onEnable(getInstance());
    }

    @Override
    public void onDisable() {
        for (Reloadable handler : handlers.reversed())
            handler.onDisable(getInstance());

        SettlersAPI.setImplementation(null);
    }

    public static SettlersPlugin getInstance() {
        return instance;
    }

    public TraitRegistryHandler getTraitHandler() {
        return traitHandler;
    }

    public RegistryHandler getRegistryHandler() {
        return registryHandler;
    }
}
