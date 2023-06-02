package com.menu.pubganalyzer.event.publisher;

import com.menu.pubganalyzer.domain.Telemetry;
import com.menu.pubganalyzer.event.SaveTelemetryEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TelemetryEventPublisher {
    private final ApplicationEventPublisher eventPublisher;

    public void saveTelemetry(Telemetry telemetry) {
        eventPublisher.publishEvent(SaveTelemetryEvent.of(
                telemetry.getLogPlayerKills(),
                telemetry.getLogPlayerTakeDamages()
        ));
    }
}
