package org.example.mybatisflexexamples.mapper;

import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.mybatisflexexamples.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
