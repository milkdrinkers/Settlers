package io.github.milkdrinkers.settlers.trait.persistence.java.time;

import io.github.milkdrinkers.settlers.trait.persistence.PersistenceHandler;
import net.citizensnpcs.api.persistence.Persister;
import net.citizensnpcs.api.util.DataKey;

import java.time.Instant;

/**
 * Allow persisting class.
 *
 * @see PersistenceHandler
 */
public class InstantTypePersister implements Persister<Instant> {
    @Override
    public Instant create(DataKey dataKey) {
        return Instant.ofEpochMilli(dataKey.getLong("", Instant.now().toEpochMilli()));
    }

    @Override
    public void save(Instant instant, DataKey dataKey) {
        dataKey.setLong("", instant.toEpochMilli());
    }
}
