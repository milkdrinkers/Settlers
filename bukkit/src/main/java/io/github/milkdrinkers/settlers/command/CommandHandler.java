package io.github.milkdrinkers.settlers.command;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import io.github.milkdrinkers.settlers.ISettlersPlugin;
import io.github.milkdrinkers.settlers.Reloadable;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler implements Reloadable {
    public static final String BASE_PERM = "settlers.command";

    private final List<Command> commands = new ArrayList<>();

    @Override
    public void onLoad(ISettlersPlugin plugin) {
        CommandAPI.onLoad(
            new CommandAPIBukkitConfig((JavaPlugin) plugin)
                .shouldHookPaperReload(true)
                .silentLogs(true)
                .setNamespace(plugin.getName().toLowerCase())
                .beLenientForMinorVersions(true)
        );
        commands.add(
            new SettlersCommand()
        );
        commands.forEach(Command::register);
    }

    @Override
    public void onEnable(ISettlersPlugin plugin) {
        CommandAPI.onEnable();
    }

    @Override
    public void onDisable(ISettlersPlugin plugin) {
        CommandAPI.getRegisteredCommands().forEach(registeredCommand -> CommandAPI.unregister(registeredCommand.namespace() + ':' + registeredCommand.commandName(), true));
        CommandAPI.onDisable();
        commands.clear();
    }
}
