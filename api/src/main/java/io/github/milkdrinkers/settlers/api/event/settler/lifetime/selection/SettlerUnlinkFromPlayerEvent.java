package io.github.milkdrinkers.settlers.api.event.settler.lifetime.selection;

import io.github.milkdrinkers.settlers.api.event.settler.AbstractSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.Settler;
import org.bukkit.entity.Player;

public class SettlerUnlinkFromPlayerEvent extends AbstractSettlerEvent {
    private final Player player;

    public SettlerUnlinkFromPlayerEvent(Settler settler, Player player) {
        super(settler);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
