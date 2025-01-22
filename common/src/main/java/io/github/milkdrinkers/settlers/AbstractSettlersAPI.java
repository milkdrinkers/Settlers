package io.github.milkdrinkers.settlers;

public class AbstractSettlersAPI {
    private static volatile SettlersPlugin implementationInstance;

    protected static SettlersPlugin getImplementation() {
        if (!isImplemented())
            throw new IllegalStateException("The API has not been implemented by any plugin!");

        if (!isEnabled())
            throw new IllegalStateException("The API was accessed but it is not enabled yet!");

        return implementationInstance;
    }

    protected static void setImplementation(SettlersPlugin implementationInstance) {
        AbstractSettlersAPI.implementationInstance = implementationInstance;
    }

    protected static boolean isImplemented() {
        return implementationInstance != null;
    }

    private static volatile boolean enabled = false;

    protected static void setEnabled(boolean enabled) {
        AbstractSettlersAPI.enabled = enabled;
    }

    public static boolean isEnabled() {
        return enabled;
    }
}
