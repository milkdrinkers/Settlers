package io.github.milkdrinkers.settlers.api.event.settlersapi.lifecycle;

import io.github.milkdrinkers.settlers.api.event.settlersapi.AbstractSettlersAPIEvent;

/**
 * An event that is fired when the Settlers API begins to shut down.
 * @implSpec This is not fired on server shutdown! Listen for spigot native plugin shutdown event instead.
 */
public class SettlersAPIUnloadedEvent extends AbstractSettlersAPIEvent {
    public SettlersAPIUnloadedEvent() {
        super();
    }
}
