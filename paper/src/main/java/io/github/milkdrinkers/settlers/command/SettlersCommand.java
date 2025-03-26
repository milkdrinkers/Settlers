package io.github.milkdrinkers.settlers.command;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.executors.CommandArguments;
import io.github.milkdrinkers.colorparser.ColorParser;
import io.github.milkdrinkers.settlers.api.settler.Companion;
import io.github.milkdrinkers.settlers.api.settler.SettlerBuilder;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;

import static io.github.milkdrinkers.settlers.command.CommandHandler.BASE_PERM;

public class SettlersCommand implements Command {
    @Override
    public void register() {
        new CommandAPICommand("settlers")
            .withFullDescription("Example command.")
            .withShortDescription("Example command.")
            .withPermission(BASE_PERM)
            .withSubcommands(
                spawn()
            )
            .executes(this::executorExample)
            .register();
    }

    private void executorExample(CommandSender sender, CommandArguments args) {
        sender.sendMessage(
            ColorParser.of("<white>Read more about CommandAPI &9<click:open_url:'https://commandapi.jorel.dev/9.0.3/'>here</click><white>.")
                .parseLegacy() // Parse legacy color codes
                .build()
        );
    }

    private CommandAPICommand spawn() {
        return new CommandAPICommand("spawn")
            .withSubcommands(
                spawnCompanion()
            );
    }

    private CommandAPICommand spawnCompanion() {
        return new CommandAPICommand("companion")
            .executesPlayer((sender, args) -> {

                final Block block = sender.getTargetBlockExact(50, FluidCollisionMode.ALWAYS);
                assert block != null;
                final Location spawnLocation = block.getLocation().toHighestLocation();

                final Companion settler = new SettlerBuilder()
                    .setName("Lydia")
                    .setLocation(spawnLocation)
                    .createCompanion();

                settler.isSpawned();

                settler.spawn(spawnLocation);
            });
    }
}
