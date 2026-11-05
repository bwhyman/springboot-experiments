package org.example.mybatisflexexamples.mapper;

import lombok.extern.slf4j.Slf4j;
import org.example.mybatisflexexamples.entity.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class AddressMapperTest {

    @Autowired
    private AddressMapper addressMapper;


    @Test
    public void insert() {
        var address = Address.builder()
                .detail("1004")
                .userId(46106974361664L)
                .build();

        addressMapper.insert(address);

    }

    @Test
    void findUserAddressById() {
        var ua = addressMapper.findUserAddressById(31078024548004L);
        log.debug("{}", ua);
    }
}