package dev.jeffersonfreitas.order.infra.in.web.order;

import dev.jeffersonfreitas.order.application.port.in.order.*;
import dev.jeffersonfreitas.order.application.port.out.customerservice.CustomerGateway;
import dev.jeffersonfreitas.order.application.port.out.order.OrderRepository;
import dev.jeffersonfreitas.order.application.port.out.product.ProductRepository;
import dev.jeffersonfreitas.order.application.service.order.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration 
public class OrderConfig {

    @Bean
    CreateOrderUseCase createOrderUseCase(OrderRepository repository, ProductRepository productRepository, CustomerGateway customerGateway){
        return new CreateOrderService(repository, productRepository, customerGateway);
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

    @Bean
    CancelOrderUseCase cancelOrderUseCase(OrderRepository repository){
        return new CancelOrderService(repository);
    }
}
