package org.example.redisexamples.service;

import lombok.extern.slf4j.Slf4j;
import org.example.redisexamples.component.ULID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class ExpireServiceTest {

    @Autowired
    private ULID ulid;
    @Autowired
    private ExpireService expireService;
    @Test
    void expire() throws InterruptedException {
        var id = ulid.nextULID();
        var amount = 15;
        CountDownLatch latch = new CountDownLatch(amount);
        for (int i = 0; i< amount; i++) {
            Thread.startVirtualThread(() -> {
                var result = expireService.expire(id, 2, 5);
                latch.countDown();
                log.debug("{}",result);
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        latch.await();
        log.debug("end");
    }
}