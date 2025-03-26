package io.github.milkdrinkers.settlers.api.event.settler.lifetime.selection;

import io.github.milkdrinkers.settlers.api.event.settler.AbstractSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.AbstractSettler;
import org.bukkit.entity.Player;

public class SettlerLinkToPlayerEvent extends AbstractSettlerEvent {
    private final Player player;

    public SettlerLinkToPlayerEvent(AbstractSettler settler, Player player, boolean async) {
        super(settler, async);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
