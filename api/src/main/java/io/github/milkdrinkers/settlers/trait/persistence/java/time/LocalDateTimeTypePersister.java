package io.github.milkdrinkers.settlers.trait.persistence.java.time;

import net.citizensnpcs.api.persistence.Persister;
import net.citizensnpcs.api.util.DataKey;

import java.time.LocalDateTime;

public class LocalDateTimeTypePersister implements Persister<LocalDateTime> {
    @Override
    public LocalDateTime create(DataKey dataKey) {
        return LocalDateTime.parse(dataKey.getString("", LocalDateTime.now().toString()));
    }

    @Override
    public void save(LocalDateTime localDateTime, DataKey dataKey) {
        dataKey.setString("", localDateTime.toString());
    }
}