package io.github.milkdrinkers.settlers.trait.persistence.java.time;

import net.citizensnpcs.api.persistence.Persister;
import net.citizensnpcs.api.util.DataKey;

import java.time.ZonedDateTime;

public class ZonedDateTimeTypePersister implements Persister<ZonedDateTime> {
    @Override
    public ZonedDateTime create(DataKey dataKey) {
        return ZonedDateTime.parse(dataKey.getString("", ZonedDateTime.now().toString()));
    }

    @Override
    public void save(ZonedDateTime zonedDateTime, DataKey dataKey) {
        dataKey.setString("", zonedDateTime.toString());
    }
}