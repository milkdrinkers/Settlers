package io.github.milkdrinkers.settlers.event.settlersapi;

import org.bukkit.event.Event;


public abstract class SettlersAPIEvent extends Event {

    protected SettlersAPIEvent() {

    }

    protected SettlersAPIEvent(boolean async) {
        super(async);
    }
}
