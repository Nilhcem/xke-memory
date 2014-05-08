package com.nilhcem.xebia.memory.client.rest;

import com.nilhcem.xebia.memory.client.Config;
import retrofit.RestAdapter;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class RetrofitService {

    private RestAdapter restAdapter;
    final Map<Class<?>, Object> retrofitServiceMap = new HashMap<Class<?>, Object>();

    @Inject
    public RetrofitService(Config config) {
        RestAdapter.Builder builder = new RestAdapter.Builder();
        restAdapter = builder
                .setConverter(new JacksonConverter())
                .setEndpoint(config.getMemoryServerEndpoint())
                .build();
    }

    @SuppressWarnings("unchecked")
    public <T> T get(Class<T> serviceClass) {
        synchronized (retrofitServiceMap) {
            T service = (T) retrofitServiceMap.get(serviceClass);
            if (service == null) {
                service = restAdapter.create(serviceClass);
                retrofitServiceMap.put(serviceClass, service);
            }
            return service;
        }
    }
}
