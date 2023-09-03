package br.com.fiap.tech.challenge.adapter.gateway.product;

import br.com.fiap.tech.challenge.adapter.dto.ComboDTO;
import br.com.fiap.tech.challenge.adapter.dto.ProductDTO;
import br.com.fiap.tech.challenge.adapter.mapping.BeverageMapper;
import br.com.fiap.tech.challenge.adapter.mapping.ComboMapper;
import br.com.fiap.tech.challenge.adapter.mapping.DessertMapper;
import br.com.fiap.tech.challenge.adapter.mapping.SandwichMapper;
import br.com.fiap.tech.challenge.adapter.mapping.SideDishMapper;
import br.com.fiap.tech.challenge.adapter.repository.ProductReaderRepository;
import br.com.fiap.tech.challenge.enterprise.entity.Product;
import br.com.fiap.tech.challenge.enterprise.enums.ProductCategory;
import br.com.fiap.tech.challenge.gateway.product.ProductReaderGateway;
import br.com.fiap.tech.challenge.util.Page;
import br.com.fiap.tech.challenge.util.ResponseList;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
class ProductReaderGatewayImpl implements ProductReaderGateway {

    private final ProductReaderRepository repository;

    @Override
    public ResponseList<Product> readAll(Page page) {
        return ResponseList.from(repository.readAll(page), this::toDomain);
    }

    @Override
    public ResponseList<Product> readAllByCategory(ProductCategory category, Page page) {
        return ResponseList.from(repository.readAllByCategory(category, page), this::toDomain);
    }

    @Override
    public Product readById(UUID id) {
        return toDomain(repository.readById(id.toString()));
    }

    private Product toDomain(ProductDTO dto) {
        return switch (dto.getCategory()){
            case SANDWICH -> SandwichMapper.INSTANCE.toDomain(dto);
            case BEVERAGE -> BeverageMapper.INSTANCE.toDomain(dto);
            case SIDE_DISH -> SideDishMapper.INSTANCE.toDomain(dto);
            case DESSERT -> DessertMapper.INSTANCE.toDomain(dto);
            case COMBO -> ComboMapper.INSTANCE.toDomain((ComboDTO) dto);
        };
    }
}
