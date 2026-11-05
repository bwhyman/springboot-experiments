package org.example.backendexamples.mapper;

import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.core.row.Row;
import org.apache.ibatis.annotations.Mapper;
import org.example.backendexamples.dto.UserAddress;
import org.example.backendexamples.entity.Address;
import org.example.backendexamples.entity.User;
import org.example.backendexamples.entity.table.AddressTableDef;
import org.example.backendexamples.entity.table.UserTableDef;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {


    // select u.id as uid, a.id as aid,  from user_x u inner join address a on u.id=a.user_id
    // where u.id=54605495961664;

    default UserAddress findUserAddressesByUserId(long uid) {
        List<Row> rows = QueryChain.of(this)
                .select("user_x.id as uid")
                .select("address.id as aid")
                .select(UserTableDef.USER.DEFAULT_COLUMNS)
                .select(AddressTableDef.ADDRESS.DEFAULT_COLUMNS)
                .from(UserTableDef.USER)
                .innerJoin(AddressTableDef.ADDRESS)
                .on(UserTableDef.USER.ID.eq(AddressTableDef.ADDRESS.USER_ID))
                .where(UserTableDef.USER.ID.eq(uid))
                .listAs(Row.class);
        User user = rows.stream()
                .findFirst()
                .map(r -> User.builder()
                        .id(r.getLong("uid"))
                        .name(r.getString("name"))
                        .account(r.getString("account"))
                        .role(r.getString("role"))
                        .build())
                .orElse(null);

        List<Address> addresses = rows.stream()
                .map(r -> Address.builder()
                        .id(r.getLong("aid"))
                        .detail(r.getString("detail"))
                        .build())
                .toList();


        return UserAddress.builder()
                .user(user)
                .addresses(addresses)
                .build();

    }

    default User findByAccount(String account) {
        // select * from user where account=?
        return QueryChain.of(this)
                .select(UserTableDef.USER.DEFAULT_COLUMNS)
                .from(UserTableDef.USER)
                .where(UserTableDef.USER.ACCOUNT.eq(account))
                .one();
    }

    default long getCount() {
        // select count(*) from user_x;
        return  QueryChain.of(this)
                .select("count(*)")
                .oneAs(Long.class);
    }
}
