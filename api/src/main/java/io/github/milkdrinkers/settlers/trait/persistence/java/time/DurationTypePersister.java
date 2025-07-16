package io.github.milkdrinkers.settlers.trait.persistence.java.time;

import net.citizensnpcs.api.persistence.Persister;
import net.citizensnpcs.api.util.DataKey;

import java.time.Duration;

public class DurationTypePersister implements Persister<Duration> {
    @Override
    public Duration create(DataKey dataKey) {
        return Duration.ofNanos(dataKey.getLong("", 0L));
    }

    @Override
    public void save(Duration duration, DataKey dataKey) {
        dataKey.setLong("", duration.toNanos());
    }
}