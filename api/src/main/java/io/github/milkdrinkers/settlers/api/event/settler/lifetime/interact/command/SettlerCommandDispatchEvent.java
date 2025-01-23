package io.github.milkdrinkers.settlers.api.event.settler.lifetime.interact.command;

import io.github.milkdrinkers.settlers.api.enums.DespawnReason;
import io.github.milkdrinkers.settlers.api.event.settler.AbstractSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.Settler;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

public class SettlerCommandDispatchEvent extends AbstractSettlerEvent implements Cancellable { // TODO Mirror citizens event
    private boolean cancelled;

    private final Player player;

    public SettlerCommandDispatchEvent(Settler settler, Player player) {
        super(settler);
        this.player = player;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public Player getPlayer() {
        return player;
    }
}
