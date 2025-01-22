package io.github.milkdrinkers.settlers.api.event.settler.lifecycle;

import io.github.milkdrinkers.settlers.api.enums.CreateReason;
import io.github.milkdrinkers.settlers.api.event.settler.AbstractSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.Settler;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

public class PlayerCreateSettlerEvent extends AbstractSettlerEvent implements Cancellable {
    private boolean cancelled;
    private CreateReason reason;
    private final Player player;

    public PlayerCreateSettlerEvent(Settler settler, Player player, CreateReason reason) { // TODO call when a player creates a settler
        super(settler);
        this.reason = reason;
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public CreateReason getReason() {
        return reason;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
