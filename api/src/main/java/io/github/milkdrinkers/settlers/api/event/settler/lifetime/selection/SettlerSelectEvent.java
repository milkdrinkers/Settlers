package io.github.milkdrinkers.settlers.api.event.settler.lifetime.selection;

import io.github.milkdrinkers.settlers.api.event.settler.AbstractSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.AbstractSettler;
import org.bukkit.command.CommandSender;

public class SettlerSelectEvent extends AbstractSettlerEvent {
    private final CommandSender sender;

    public SettlerSelectEvent(AbstractSettler settler, CommandSender sender) {
        super(settler);
        this.sender = sender;
    }

    public CommandSender getSelector() {
        return sender;
    }
}
