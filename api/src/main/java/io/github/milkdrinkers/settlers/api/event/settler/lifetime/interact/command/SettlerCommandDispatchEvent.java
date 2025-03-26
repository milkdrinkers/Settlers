package io.github.milkdrinkers.settlers.api.event.settler.lifetime.interact.command;

import io.github.milkdrinkers.settlers.api.event.settler.AbstractCancellableSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.AbstractSettler;
import org.bukkit.entity.Player;

public class SettlerCommandDispatchEvent extends AbstractCancellableSettlerEvent {
    private final Player player;

    public SettlerCommandDispatchEvent(AbstractSettler settler, Player player) {
        super(settler);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
