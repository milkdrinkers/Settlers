package io.github.milkdrinkers.settlers.trait.persistence.java.time;

import net.citizensnpcs.api.persistence.Persister;
import net.citizensnpcs.api.util.DataKey;

import java.time.LocalTime;

public class LocalTimeTypePersister implements Persister<LocalTime> {
    @Override
    public LocalTime create(DataKey dataKey) {
        return LocalTime.parse(dataKey.getString("", LocalTime.now().toString()));
    }

    @Override
    public void save(LocalTime localTime, DataKey dataKey) {
        dataKey.setString("", localTime.toString());
    }
}