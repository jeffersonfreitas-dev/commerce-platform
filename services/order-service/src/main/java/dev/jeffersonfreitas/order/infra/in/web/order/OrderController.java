package dev.jeffersonfreitas.order.infra.in.web.order;


import dev.jeffersonfreitas.order.application.dto.PageGeneric;
import dev.jeffersonfreitas.order.application.dto.PageableRequest;
import dev.jeffersonfreitas.order.application.dto.SortOrder;
import dev.jeffersonfreitas.order.application.port.in.order.*;
import dev.jeffersonfreitas.order.application.port.in.order.dto.CreateOrderInput;
import dev.jeffersonfreitas.order.application.port.in.order.dto.OrderFilter;
import dev.jeffersonfreitas.order.application.port.in.order.dto.OrderOutput;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController 
@RequestMapping ("/orders")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final GetOrderUseCase getOrderUseCase;
    private final DeleteOrderUseCase deleteOrderUseCase;
    private final CancelOrderUseCase cancelOrderUseCase;
    private final GetAllOrderUseCase getAllOrderUseCase;

    public OrderController(CreateOrderUseCase createOrderUseCase, GetOrderUseCase getOrderUseCase,
                           DeleteOrderUseCase deleteOrderUseCase, CancelOrderUseCase cancelOrderUseCase,
                           GetAllOrderUseCase getAllOrderUseCase){
        this.createOrderUseCase = createOrderUseCase;
        this.getOrderUseCase = getOrderUseCase;
        this.deleteOrderUseCase = deleteOrderUseCase;
        this.cancelOrderUseCase = cancelOrderUseCase;
        this.getAllOrderUseCase = getAllOrderUseCase;
    }


    @PostMapping 
    public ResponseEntity<OrderResponse> create(@RequestBody CreateOrderRequest request){
        CreateOrderInput input = CreateOrderInput.from(request);
        OrderOutput output = createOrderUseCase.execute(input);
        OrderResponse response = OrderResponse.from(output);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderResponse> get(@PathVariable(name = "id") String id){
        OrderOutput output = getOrderUseCase.execute(id);
        OrderResponse response = OrderResponse.from(output);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") String id){
        deleteOrderUseCase.execute(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<Void> cancel(@PathVariable(name = "id") String id){
        cancelOrderUseCase.execute(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<PageGeneric<OrderResponse>> getAll(@PageableDefault(size = 20, sort = "date", direction = Sort.Direction.ASC)
                                                             Pageable pageable, OrderFilter filter){
        List<SortOrder> sort = pageable.getSort().stream().map(o -> new SortOrder(o.getProperty(), o.getDirection().name())).toList();
        PageableRequest pageableRequest = PageableRequest.create(pageable.getPageNumber(), pageable.getPageSize(), sort);
        PageGeneric<OrderOutput> orderOutput = getAllOrderUseCase.execute(filter, pageableRequest);
        PageGeneric<OrderResponse> response = orderOutput.map(OrderResponse::from);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
