package br.com.fiap.tech.challenge.adapter.driven.redis.mapping;

import br.com.fiap.tech.challenge.adapter.driven.redis.config.RedisTypeMapConfiguration;
import br.com.fiap.tech.challenge.adapter.driven.redis.model.CustomerEntity;
import br.com.fiap.tech.challenge.domain.entity.Customer;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;

import java.util.UUID;

@Mapper
public class RedisCustomerEntityToCustomerMapping implements RedisTypeMapConfiguration {

    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(CustomerEntity.class, Customer.class)
                .setProvider(customerProvider());
    }

    private static Provider<Customer> customerProvider() {
        return provision -> {
            var source = (CustomerEntity) provision.getSource();

            return Customer.builder()
                    .uuid(UUID.fromString(source.getId()))
                    .name(source.getName())
                    .document(source.getDocument())
                    .email(source.getEmail())
                    .enabled(source.getEnabled())
                    .build();
        };
    }
}