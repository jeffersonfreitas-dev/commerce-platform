package dev.jeffersonfreitas.ecom_api.infra.in.web.order;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.jeffersonfreitas.ecom_api.application.port.in.order.CreateOrderUseCase;
import dev.jeffersonfreitas.ecom_api.application.port.in.order.DeleteOrderUseCase;
import dev.jeffersonfreitas.ecom_api.application.port.in.order.GetAllOrderUseCase;
import dev.jeffersonfreitas.ecom_api.application.port.in.order.GetOrderUseCase;
import dev.jeffersonfreitas.ecom_api.application.port.out.customer.CustomerRepository;
import dev.jeffersonfreitas.ecom_api.application.port.out.order.OrderRepository;
import dev.jeffersonfreitas.ecom_api.application.port.out.product.ProductRepository;
import dev.jeffersonfreitas.ecom_api.application.service.order.CreateOrderService;
import dev.jeffersonfreitas.ecom_api.application.service.order.DeleteOrderService;
import dev.jeffersonfreitas.ecom_api.application.service.order.GetAllOrderService;
import dev.jeffersonfreitas.ecom_api.application.service.order.GetOrderService;

@Configuration 
public class OrderConfig {

    @Bean 
    CreateOrderUseCase createOrderUseCase(OrderRepository repository, CustomerRepository customerRepository, ProductRepository productRepository){
        return new CreateOrderService(repository, customerRepository, productRepository);
    }

    @Bean 
    GetOrderUseCase getOrderUseCase(OrderRepository repository){
        return new GetOrderService(repository);
    }

    @Bean 
    DeleteOrderUseCase deleteOrderUseCase(OrderRepository repository){
        return new DeleteOrderService(repository);
    }

    @Bean 
    GetAllOrderUseCase getAllOrderService(OrderRepository repository){
        return new GetAllOrderService(repository);
    }
}
