package br.com.fiap.tech.challenge.adapter.driven.redis.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serial;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
@ToString
public class RedisEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 5294553644888153354L;

    @Id
    @Indexed
    private String id;

    @TimeToLive(unit = TimeUnit.MILLISECONDS)
    private Long ttl;
}