package br.com.fiap.tech.challenge.launcher.configuration;

import br.com.fiap.tech.challenge.adapter.controller.cart.AddCartItemController;
import br.com.fiap.tech.challenge.adapter.controller.cart.CartControllerFactory;
import br.com.fiap.tech.challenge.adapter.controller.cart.CreateCartController;
import br.com.fiap.tech.challenge.adapter.controller.cart.FindCartByUUIDController;
import br.com.fiap.tech.challenge.adapter.controller.cart.RemoveCartItemController;
import br.com.fiap.tech.challenge.adapter.controller.cart.UpdateCartItemController;
import br.com.fiap.tech.challenge.adapter.controller.customer.CreateCustomerController;
import br.com.fiap.tech.challenge.adapter.controller.customer.CustomerControllerFactory;
import br.com.fiap.tech.challenge.adapter.controller.customer.FindCustomerByDocumentController;
import br.com.fiap.tech.challenge.adapter.controller.customer.FindCustomerByUUIDController;
import br.com.fiap.tech.challenge.adapter.controller.customer.UpgradeCustomerController;
import br.com.fiap.tech.challenge.adapter.controller.product.CreateProductController;
import br.com.fiap.tech.challenge.adapter.controller.product.FindAllAvailableProductByCategoryController;
import br.com.fiap.tech.challenge.adapter.controller.product.FindAllAvailableProductController;
import br.com.fiap.tech.challenge.adapter.controller.product.FindProductByUUIDController;
import br.com.fiap.tech.challenge.adapter.controller.product.ManageProductController;
import br.com.fiap.tech.challenge.adapter.controller.product.ProductControllerFactory;
import br.com.fiap.tech.challenge.adapter.controller.product.UpdateProductController;
import br.com.fiap.tech.challenge.adapter.controller.purchase.CheckoutController;
import br.com.fiap.tech.challenge.adapter.controller.purchase.PurchaseControllerFactory;
import br.com.fiap.tech.challenge.adapter.presenter.CartPresenter;
import br.com.fiap.tech.challenge.adapter.presenter.CustomerPresenter;
import br.com.fiap.tech.challenge.adapter.presenter.ProductPresenter;
import br.com.fiap.tech.challenge.adapter.presenter.PurchasePresenter;
import br.com.fiap.tech.challenge.usecase.cart.AddCartItemUseCase;
import br.com.fiap.tech.challenge.usecase.cart.CreateCartUseCase;
import br.com.fiap.tech.challenge.usecase.cart.FindCartByUUIDUseCase;
import br.com.fiap.tech.challenge.usecase.cart.RemoveCartItemUseCase;
import br.com.fiap.tech.challenge.usecase.cart.UpdateCartItemUseCase;
import br.com.fiap.tech.challenge.usecase.customer.CreateCustomerUseCase;
import br.com.fiap.tech.challenge.usecase.customer.FindCustomerByDocumentUseCase;
import br.com.fiap.tech.challenge.usecase.customer.FindCustomerByUUIDUseCase;
import br.com.fiap.tech.challenge.usecase.customer.UpgradeCustomerUseCase;
import br.com.fiap.tech.challenge.usecase.product.CreateProductUseCase;
import br.com.fiap.tech.challenge.usecase.product.DisableProductUseCase;
import br.com.fiap.tech.challenge.usecase.product.EnableProductUseCase;
import br.com.fiap.tech.challenge.usecase.product.FindAllAvailableProductByCategoryUseCase;
import br.com.fiap.tech.challenge.usecase.product.FindAllAvailableProductUseCase;
import br.com.fiap.tech.challenge.usecase.product.FindProductByUUIDUseCase;
import br.com.fiap.tech.challenge.usecase.product.UpdateProductUseCase;
import br.com.fiap.tech.challenge.usecase.purchase.CheckoutUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfiguration {

    @Bean
    public CreateProductController createProductController(CreateProductUseCase useCase, ProductPresenter presenter){
        return ProductControllerFactory.createProductController(useCase, presenter);
    }

    @Bean
    public UpdateProductController updateProductController(UpdateProductUseCase useCase, ProductPresenter presenter) {
        return ProductControllerFactory.updateProductController(useCase, presenter);
    }

    @Bean
    public ManageProductController manageProductController(EnableProductUseCase enableUseCase, DisableProductUseCase disableUseCase, ProductPresenter presenter) {
        return ProductControllerFactory.manageProductController(enableUseCase, disableUseCase, presenter);
    }

    @Bean
    public FindAllAvailableProductByCategoryController findAllAvailableProductByCategoryController(FindAllAvailableProductByCategoryUseCase useCase, ProductPresenter presenter) {
        return ProductControllerFactory.findAllAvailableProductByCategoryController(useCase, presenter);
    }

    @Bean
    public FindAllAvailableProductController findAllAvailableProductController(FindAllAvailableProductUseCase useCase, ProductPresenter presenter){
        return ProductControllerFactory.findAllAvailableProductController(useCase, presenter);
    }

    @Bean
    public FindProductByUUIDController findProductByUUIDController(FindProductByUUIDUseCase useCase, ProductPresenter presenter) {
        return ProductControllerFactory.findProductByUUIDController(useCase, presenter);
    }

    @Bean
    public CreateCustomerController createCustomerController(CreateCustomerUseCase useCase, CustomerPresenter presenter) {
        return CustomerControllerFactory.createCustomerController(useCase, presenter);
    }

    @Bean
    public FindCustomerByDocumentController findCustomerByDocumentController(FindCustomerByDocumentUseCase useCase, CustomerPresenter presenter) {
        return CustomerControllerFactory.findCustomerByDocumentController(useCase, presenter);
    }

    @Bean
    public FindCustomerByUUIDController findCustomerByUUIDController(FindCustomerByUUIDUseCase useCase, CustomerPresenter presenter) {
        return CustomerControllerFactory.findCustomerByUUIDController(useCase, presenter);
    }

    @Bean
    public UpgradeCustomerController upgradeCustomerController(UpgradeCustomerUseCase useCase, CustomerPresenter presenter) {
        return CustomerControllerFactory.upgradeCustomerController(useCase, presenter);
    }

    @Bean
    public CreateCartController createCartController(CreateCartUseCase useCase, CartPresenter presenter) {
        return CartControllerFactory.createCartController(useCase, presenter);
    }

    @Bean
    public FindCartByUUIDController findCartByUUIDController(FindCartByUUIDUseCase useCase, CartPresenter presenter) {
        return CartControllerFactory.findCartByUUIDController(useCase, presenter);
    }

    @Bean
    public AddCartItemController addCartItemController(AddCartItemUseCase useCase, CartPresenter presenter) {
        return CartControllerFactory.addCartItemController(useCase, presenter);
    }

    @Bean
    public UpdateCartItemController updateCartItemController(UpdateCartItemUseCase useCase, CartPresenter presenter) {
        return CartControllerFactory.updateCartItemController(useCase, presenter);
    }

    @Bean
    public RemoveCartItemController removeCartItemController(RemoveCartItemUseCase useCase, CartPresenter presenter) {
        return CartControllerFactory.removeCartItemController(useCase, presenter);
    }

    @Bean
    public CheckoutController checkoutController(CheckoutUseCase useCase, PurchasePresenter presenter) {
        return PurchaseControllerFactory.checkoutController(useCase, presenter);
    }
}
