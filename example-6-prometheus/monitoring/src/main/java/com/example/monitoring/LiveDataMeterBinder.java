package com.example.monitoring;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;

public class LiveDataMeterBinder implements MeterBinder {

    private LiveDataService liveDataService;

    public LiveDataMeterBinder(LiveDataService liveDataService) {
        this.liveDataService = liveDataService;
    }

    @Override
    public void bindTo(MeterRegistry meterRegistry) {
        Gauge.builder("live_data", this, value -> value.getLiveData())
                .description("Live Data")
                .register(meterRegistry);
    }

    private Double getLiveData() {
        return liveDataService.getData();
    }

}
