package io.github.milkdrinkers.settlers.trait.persistence.java.time;

import net.citizensnpcs.api.persistence.Persister;
import net.citizensnpcs.api.util.DataKey;

import java.time.LocalDate;

public class LocalDateTypePersister implements Persister<LocalDate> {
    @Override
    public LocalDate create(DataKey dataKey) {
        return LocalDate.parse(dataKey.getString("", LocalDate.now().toString()));
    }

    @Override
    public void save(LocalDate localDate, DataKey dataKey) {
        dataKey.setString("", localDate.toString());
    }
}