package io.github.milkdrinkers.settlers.trait.persistence.java.number;

import net.citizensnpcs.api.persistence.Persister;
import net.citizensnpcs.api.util.DataKey;

import java.math.BigDecimal;

public class BigDecimalTypePersister implements Persister<BigDecimal> {
    @Override
    public BigDecimal create(DataKey dataKey) {
        return new BigDecimal(dataKey.getString("", "0"));
    }

    @Override
    public void save(BigDecimal bigDecimal, DataKey dataKey) {
        dataKey.setString("", bigDecimal.toString());
    }
}