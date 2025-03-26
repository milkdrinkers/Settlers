package io.github.milkdrinkers.settlers.api.event.settler.lifetime.selection;

import io.github.milkdrinkers.settlers.api.event.settler.AbstractSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.AbstractSettler;
import org.bukkit.entity.Player;

public class SettlerUnlinkFromPlayerEvent extends AbstractSettlerEvent {
    private final Player player;

    public SettlerUnlinkFromPlayerEvent(AbstractSettler settler, Player player) {
        super(settler);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
