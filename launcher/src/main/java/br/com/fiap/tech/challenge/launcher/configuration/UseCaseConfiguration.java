package br.com.fiap.tech.challenge.launcher.configuration;

import br.com.fiap.tech.challenge.gateway.CustomerReaderGateway;
import br.com.fiap.tech.challenge.gateway.CustomerWriterGateway;
import br.com.fiap.tech.challenge.gateway.ProductReaderGateway;
import br.com.fiap.tech.challenge.gateway.ProductWriterGateway;
import br.com.fiap.tech.challenge.gateway.CartReaderGateway;
import br.com.fiap.tech.challenge.gateway.CartWriterGateway;
import br.com.fiap.tech.challenge.usecase.cart.AddCartItemUseCase;
import br.com.fiap.tech.challenge.usecase.cart.CartUseCaseFactory;
import br.com.fiap.tech.challenge.usecase.cart.CreateCartUseCase;
import br.com.fiap.tech.challenge.usecase.cart.FindCartByUUIDUseCase;
import br.com.fiap.tech.challenge.usecase.cart.RemoveCartItemUseCase;
import br.com.fiap.tech.challenge.usecase.cart.UpdateCartItemUseCase;
import br.com.fiap.tech.challenge.usecase.customer.CreateCustomerUseCase;
import br.com.fiap.tech.challenge.usecase.customer.CustomerUseCaseFactory;
import br.com.fiap.tech.challenge.usecase.customer.FindCustomerByDocumentUseCase;
import br.com.fiap.tech.challenge.usecase.customer.FindCustomerByUUIDUseCase;
import br.com.fiap.tech.challenge.usecase.customer.UpgradeCustomerUseCase;
import br.com.fiap.tech.challenge.usecase.product.CreateProductUseCase;
import br.com.fiap.tech.challenge.usecase.product.DisableProductUseCase;
import br.com.fiap.tech.challenge.usecase.product.EnableProductUseCase;
import br.com.fiap.tech.challenge.usecase.product.FindAllAvailableProductByCategoryUseCase;
import br.com.fiap.tech.challenge.usecase.product.FindAllAvailableProductUseCase;
import br.com.fiap.tech.challenge.usecase.product.FindProductByUUIDUseCase;
import br.com.fiap.tech.challenge.usecase.product.ProductUseCaseFactory;
import br.com.fiap.tech.challenge.usecase.product.UpdateProductUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {
    @Bean
    public FindAllAvailableProductUseCase findAllAvailableProductService(ProductReaderGateway reader) {
        return ProductUseCaseFactory.findAllAvailableProductService(reader);
    }

    @Bean
    public FindAllAvailableProductByCategoryUseCase findAllAvailableProductByCategory(ProductReaderGateway reader) {
        return ProductUseCaseFactory.findAllAvailableProductByCategory(reader);
    }

    @Bean
    public FindProductByUUIDUseCase findProductByUUIDService(ProductReaderGateway reader) {
        return ProductUseCaseFactory.findProductByUUIDService(reader);
    }

    @Bean
    public CreateProductUseCase createProductService(ProductWriterGateway writerGateway, ProductReaderGateway readerGateway) {
        return ProductUseCaseFactory.createProductService(writerGateway, readerGateway);
    }

    @Bean
    public UpdateProductUseCase updateProductService(ProductWriterGateway writer, ProductReaderGateway reader) {
        return ProductUseCaseFactory.updateProductService(reader, writer);
    }

    @Bean
    public EnableProductUseCase enableProductService(ProductWriterGateway writer, ProductReaderGateway reader) {
        return ProductUseCaseFactory.enableProductService(writer, reader);
    }

    @Bean
    public DisableProductUseCase disableProductService(ProductWriterGateway writer, ProductReaderGateway reader) {
        return ProductUseCaseFactory.disableProductService(writer, reader);
    }

    @Bean
    public CreateCustomerUseCase createCustomerService(CustomerWriterGateway writer, CustomerReaderGateway reader) {
        return CustomerUseCaseFactory.createCustomerService(writer, reader);
    }

    @Bean
    public UpgradeCustomerUseCase upgradeCustomerService(CustomerWriterGateway writer, CustomerReaderGateway reader) {
        return CustomerUseCaseFactory.upgradeCustomerService(writer, reader);
    }

    @Bean
    public FindCustomerByDocumentUseCase findCustomerByDocumentService(CustomerReaderGateway reader) {
        return CustomerUseCaseFactory.findCustomerByDocumentService(reader);
    }

    @Bean
    public FindCustomerByUUIDUseCase findCustomerByUUIDService(CustomerReaderGateway reader) {
        return CustomerUseCaseFactory.findFindCustomerByUUIDService(reader);
    }

    @Bean
    public FindCartByUUIDUseCase findCartByUUIDService(CartReaderGateway reader) {
        return CartUseCaseFactory.findCartByUUIDService(reader);
    }

    @Bean
    public CreateCartUseCase createCartService(FindCustomerByUUIDUseCase useCase, CartWriterGateway writer) {
        return CartUseCaseFactory.createCartService(useCase, writer);
    }

    @Bean
    public AddCartItemUseCase addCartItemService(CartReaderGateway reader, CartWriterGateway writer, FindProductByUUIDUseCase findProductByUUIDUseCase) {
        return CartUseCaseFactory.addCartItemService(reader, writer, findProductByUUIDUseCase);
    }

    @Bean
    public UpdateCartItemUseCase updateCartItemService(CartReaderGateway reader, CartWriterGateway writer, FindProductByUUIDUseCase findProductByUUIDUseCase) {
        return CartUseCaseFactory.updateCartItemService(reader, writer, findProductByUUIDUseCase);
    }

    @Bean
    public RemoveCartItemUseCase removeCartItemUseCase(CartReaderGateway useCase, CartWriterGateway presenter) {
        return CartUseCaseFactory.removeCartItemUseCase(useCase, presenter);
    }
}