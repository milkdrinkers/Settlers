package io.github.milkdrinkers.settlers.trait.persistence.java.number;

import net.citizensnpcs.api.persistence.Persister;
import net.citizensnpcs.api.util.DataKey;

import java.math.BigInteger;

public class BigIntegerTypePersister implements Persister<BigInteger> {
    @Override
    public BigInteger create(DataKey dataKey) {
        return new BigInteger(dataKey.getString("", "0"));
    }

    @Override
    public void save(BigInteger bigInteger, DataKey dataKey) {
        dataKey.setString("", bigInteger.toString());
    }
}
