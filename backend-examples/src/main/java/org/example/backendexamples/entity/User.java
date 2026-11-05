package org.example.backendexamples.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backendexamples.component.SnowflakeGenerator;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("user_x")
public class User {

    public static final String USER= "3Rgt";
    public static final String ADMIN = "fGi7";

    @Id(keyType = KeyType.Generator, value = SnowflakeGenerator.MINI_SNOWFLAKE)
    private Long id;
    private String account;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String name;
    private String role;
    @Column(onInsertValue = "current_timestamp")
    private LocalDateTime createTime;
    @Column(onInsertValue = "current_timestamp", onUpdateValue = "current_timestamp")
    private LocalDateTime updateTime;
}
