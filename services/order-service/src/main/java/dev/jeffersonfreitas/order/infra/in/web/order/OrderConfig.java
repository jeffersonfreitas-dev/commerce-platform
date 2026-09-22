package dev.jeffersonfreitas.order.infra.in.web.order;

import dev.jeffersonfreitas.order.application.port.in.order.CreateOrderUseCase;
import dev.jeffersonfreitas.order.application.port.in.order.DeleteOrderUseCase;
import dev.jeffersonfreitas.order.application.port.in.order.GetAllOrderUseCase;
import dev.jeffersonfreitas.order.application.port.in.order.GetOrderUseCase;
import dev.jeffersonfreitas.order.application.port.out.order.OrderRepository;
import dev.jeffersonfreitas.order.application.port.out.product.ProductRepository;
import dev.jeffersonfreitas.order.application.service.order.CreateOrderService;
import dev.jeffersonfreitas.order.application.service.order.DeleteOrderService;
import dev.jeffersonfreitas.order.application.service.order.GetAllOrderService;
import dev.jeffersonfreitas.order.application.service.order.GetOrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration 
public class OrderConfig {

    @Bean
    CreateOrderUseCase createOrderUseCase(OrderRepository repository, ProductRepository productRepository){
        return new CreateOrderService(repository, productRepository);
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
