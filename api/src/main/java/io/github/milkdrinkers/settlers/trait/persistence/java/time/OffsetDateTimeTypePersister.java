package io.github.milkdrinkers.settlers.trait.persistence.java.time;

import net.citizensnpcs.api.persistence.Persister;
import net.citizensnpcs.api.util.DataKey;

import java.time.OffsetDateTime;

public class OffsetDateTimeTypePersister implements Persister<OffsetDateTime> {
    @Override
    public OffsetDateTime create(DataKey dataKey) {
        return OffsetDateTime.parse(dataKey.getString("", OffsetDateTime.now().toString()));
    }

    @Override
    public void save(OffsetDateTime offsetDateTime, DataKey dataKey) {
        dataKey.setString("", offsetDateTime.toString());
    }
}