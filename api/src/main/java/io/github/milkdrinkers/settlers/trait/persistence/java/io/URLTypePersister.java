package io.github.milkdrinkers.settlers.trait.persistence.java.io;

import net.citizensnpcs.api.persistence.Persister;
import net.citizensnpcs.api.util.DataKey;

import java.net.URL;
import java.net.MalformedURLException;

public class URLTypePersister implements Persister<URL> {
    @Override
    public URL create(DataKey dataKey) {
        try {
            return new URL(dataKey.getString("", "http://localhost"));
        } catch (MalformedURLException e) {
            try {
                return new URL("http://localhost");
            } catch (MalformedURLException ex) {
                return null;
            }
        }
    }

    @Override
    public void save(URL url, DataKey dataKey) {
        dataKey.setString("", url.toString());
    }
}