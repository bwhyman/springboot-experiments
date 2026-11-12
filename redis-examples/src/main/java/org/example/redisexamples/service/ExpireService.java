package org.example.redisexamples.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.redisexamples.component.ULID;
import org.redisson.api.FunctionMode;
import org.redisson.api.FunctionResult;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.IntegerCodec;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExpireService {
    private final RedissonClient redissonClient;
    private final String pre_key = "expires:agentids:";

    public boolean expire(String id, int count, int expireSec) {
        var key = pre_key + id;
        return redissonClient.getFunction(IntegerCodec.INSTANCE)
                .call(FunctionMode.WRITE,
                        "expire_" + 0,
                        FunctionResult.BOOLEAN,
                        List.of(key),
                        count, expireSec);

    }
}
