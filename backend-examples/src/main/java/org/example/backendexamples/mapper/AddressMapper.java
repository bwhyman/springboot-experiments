package org.example.backendexamples.mapper;

import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.backendexamples.entity.Address;

@Mapper
public interface AddressMapper extends BaseMapper<Address> {

}
