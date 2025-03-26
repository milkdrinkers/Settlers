package io.github.milkdrinkers.settlers.api;

import io.github.milkdrinkers.settlers.ISettlersPlugin;
import org.jetbrains.annotations.ApiStatus;

abstract class AbstractSettlersAPI {
    private static volatile ISettlersPlugin implementationInstance;

    @ApiStatus.Internal
    public static ISettlersPlugin getImplementation() {
        if (!isImplemented())
            throw new IllegalStateException("The API has not been implemented by any plugin!");

        if (!isEnabled())
            throw new IllegalStateException("The API was accessed but it is not enabled yet!");

        return AbstractSettlersAPI.implementationInstance;
    }

    @ApiStatus.Internal
    public static void setImplementation(ISettlersPlugin implementationInstance) {
        AbstractSettlersAPI.implementationInstance = implementationInstance;
    }

    public static boolean isImplemented() {
        return AbstractSettlersAPI.implementationInstance != null;
    }

    private static volatile boolean enabled = false;

    @ApiStatus.Internal
    public static void setEnabled(boolean enabled) {
        AbstractSettlersAPI.enabled = enabled;
    }

    public static boolean isEnabled() {
        return AbstractSettlersAPI.enabled;
    }
}
