package dev.mmieckowski.algorithm.execution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;

@ExtendWith(MockitoExtension.class)
class TimerTest {
    @InjectMocks
    private Timer timer;
    @Mock
    private Clock clock;

    @Test
    void timerReturnsDurationBetweenRestartCallsTest() {
        long delay = 100;
        String startTime = "2014-12-22T10:15:30Z";
        Clock firstRestartClock = Clock.fixed(Instant.parse(startTime), ZoneId.of("UTC"));
        Clock secondRestartClock = Clock.offset(firstRestartClock, Duration.ofMillis(delay));
        when(clock.millis()).thenReturn(firstRestartClock.millis(), secondRestartClock.millis());

        Duration restartOne = timer.restart();
        Duration restartTwo = timer.restart();

        verify(clock, times(3)).millis();
        assertNotNull(restartOne);
        assertNotNull(restartTwo);
        assertEquals(delay, restartTwo.toMillis());
    }
}