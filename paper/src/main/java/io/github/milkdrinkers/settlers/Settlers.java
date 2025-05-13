package io.github.milkdrinkers.settlers;

import io.github.milkdrinkers.colorparser.ColorParser;
import io.github.milkdrinkers.settlers.api.SettlersAPI;
import io.github.milkdrinkers.settlers.api.enums.SettlerType;
import io.github.milkdrinkers.settlers.command.CommandHandler;
import io.github.milkdrinkers.settlers.listener.ListenerHandler;
import io.github.milkdrinkers.settlers.lookup.ILookupHandler;
import io.github.milkdrinkers.settlers.lookup.LookupHandler;
import io.github.milkdrinkers.settlers.registry.IRegistryHandler;
import io.github.milkdrinkers.settlers.registry.RegistryHandler;
import io.github.milkdrinkers.settlers.trait.ITraitRegistryHandler;
import io.github.milkdrinkers.settlers.trait.TraitRegistryHandler;
import io.github.milkdrinkers.settlers.utility.APILifecycle;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;

public class Settlers extends JavaPlugin implements ISettlersPlugin {
    private static Settlers instance;

    // Handlers
    private final ITraitRegistryHandler traitHandler = new TraitRegistryHandler();
    private final IRegistryHandler IRegistryHandler = new RegistryHandler();
    private final ILookupHandler ILookupHandler = new LookupHandler();
    private final ListenerHandler listenerHandler = new ListenerHandler();
    private final CommandHandler commandHandler = new CommandHandler();

    // Used to decrease code repetition (Order matters, onDisable is reversed)
    private final List<? extends Reloadable> handlers = List.of(
        traitHandler,
        IRegistryHandler,
        ILookupHandler,
        commandHandler,
        listenerHandler
    );

    @Override
    public void onLoad() {
        instance = this;
        try {
            final File pluginDir = getInstance().getDataFolder();
            final File pluginDataDir = pluginDir.toPath().resolve(DIR_DATA).toFile();

            if (!pluginDir.exists() && !pluginDir.mkdirs())
                throw new RuntimeException("Failed to create plugin directory!");

            if (!pluginDataDir.exists() && !pluginDataDir.mkdirs())
                throw new RuntimeException("Failed to create plugin data directory!");

        } catch (RuntimeException e) {
            getInstance().getComponentLogger().error(ColorParser.of("Failed to create plugin data directory: ").build(), e);
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

        this.getServer().getScheduler().runTaskTimerAsynchronously(this, this::saveAllSettlers, 12000, 12000);
    }

    @Override
    public void onDisable() {
        APILifecycle.shutdown(getInstance());

        for (Reloadable handler : handlers.reversed())
            handler.onDisable(getInstance());

        SettlersAPI.setImplementation(null);
    }

    public static Settlers getInstance() {
        return instance;
    }

    @Override
    public ITraitRegistryHandler getTraitHandler() {
        return traitHandler;
    }

    @Override
    public IRegistryHandler getRegistryHandler() {
        return IRegistryHandler;
    }

    @Override
    public ILookupHandler getLookupHandler() {
        return ILookupHandler;
    }

    @Override
    public void setAPIState(boolean enabled) {
        SettlersAPI.setEnabled(enabled);
    }

    private void saveAllSettlers() {
        SettlersAPI.getSettlersRegistries().getRegistry(SettlerType.COMPANION).getDataStore().getDataStore()
            .storeAll(SettlersAPI.getSettlersRegistries().getRegistry(SettlerType.COMPANION).getPersistentRegistry());
        SettlersAPI.getSettlersRegistries().getRegistry(SettlerType.GUARD).getDataStore().getDataStore()
            .storeAll(SettlersAPI.getSettlersRegistries().getRegistry(SettlerType.GUARD).getPersistentRegistry());
        SettlersAPI.getSettlersRegistries().getRegistry(SettlerType.NATION).getDataStore().getDataStore()
            .storeAll(SettlersAPI.getSettlersRegistries().getRegistry(SettlerType.NATION).getPersistentRegistry());
        SettlersAPI.getSettlersRegistries().getRegistry(SettlerType.TOWN).getDataStore().getDataStore()
            .storeAll(SettlersAPI.getSettlersRegistries().getRegistry(SettlerType.TOWN).getPersistentRegistry());


        SettlersAPI.getSettlersRegistries().getRegistry(SettlerType.COMPANION).getDataStore().getDataStore().saveToDiskImmediate();
        SettlersAPI.getSettlersRegistries().getRegistry(SettlerType.GUARD).getDataStore().getDataStore().saveToDiskImmediate();
        SettlersAPI.getSettlersRegistries().getRegistry(SettlerType.NATION).getDataStore().getDataStore().saveToDiskImmediate();
        SettlersAPI.getSettlersRegistries().getRegistry(SettlerType.TOWN).getDataStore().getDataStore().saveToDiskImmediate();
    }
}
