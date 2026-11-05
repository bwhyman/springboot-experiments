package org.example.mybatisflexexamples.mapper;

import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.query.QueryChain;
import org.apache.ibatis.annotations.Mapper;
import org.example.mybatisflexexamples.dto.UserAddress;
import org.example.mybatisflexexamples.entity.Address;
import org.example.mybatisflexexamples.entity.table.AddressTableDef;
import org.example.mybatisflexexamples.entity.table.UserTableDef;

@Mapper
public interface AddressMapper extends BaseMapper<Address> {
    default UserAddress findUserAddressById(long aid) {
        return QueryChain.of(this)
                .select(AddressTableDef.ADDRESS.ID.as("addressId"))
                .select(AddressTableDef.ADDRESS.DETAIL)
                .select(UserTableDef.USER.ID.as("userId"))
                .select(UserTableDef.USER.NAME)
                .from(AddressTableDef.ADDRESS)
                .join(UserTableDef.USER)
                .on(AddressTableDef.ADDRESS.USER_ID.eq(UserTableDef.USER.ID))
                .where(AddressTableDef.ADDRESS.ID.eq(aid))
                .oneAs(UserAddress.class);
    }
}
