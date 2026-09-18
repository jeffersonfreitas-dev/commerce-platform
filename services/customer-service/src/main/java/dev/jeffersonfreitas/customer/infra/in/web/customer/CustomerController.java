package dev.jeffersonfreitas.customer.infra.in.web.customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev.jeffersonfreitas.customer.application.port.in.customer.*;
import dev.jeffersonfreitas.customer.application.port.in.customer.create.InputCreateCustomer;
import dev.jeffersonfreitas.customer.application.port.in.customer.create.InputCreateDeliverAddress;
import dev.jeffersonfreitas.customer.application.port.in.customer.create.UCCreateCustomer;
import dev.jeffersonfreitas.customer.application.port.in.customer.create.UCCreateDeliverAddress;
import dev.jeffersonfreitas.customer.application.port.in.customer.get.UCGetCustomer;
import dev.jeffersonfreitas.customer.application.port.in.customer.update.InputUpdateDeliverAddress;
import dev.jeffersonfreitas.customer.application.port.in.customer.update.UCUpdateDeliverAddress;


@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final UCCreateCustomer createCustomerUseCase;
    private final UCGetCustomer getCustomerUseCase;
    private final UCUpdateDeliverAddress updateDeliverAddressUseCase;
    private final UCCreateDeliverAddress createDeliverAddress;

    public CustomerController(UCCreateCustomer createCustomerUseCase, UCGetCustomer getCustomerUseCase,
                              UCUpdateDeliverAddress updateDeliverAddressUseCase, UCCreateDeliverAddress createDeliverAddress) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.getCustomerUseCase = getCustomerUseCase;
        this.updateDeliverAddressUseCase = updateDeliverAddressUseCase;
        this.createDeliverAddress = createDeliverAddress;
    }

    @PostMapping
    public ResponseEntity<ResponseCustomer> create(@RequestBody RequestCreateCustomer request){
        InputCreateCustomer input = RequestCreateCustomer.toInput(request);
        OutputCustomer output = createCustomerUseCase.execute(input);
        ResponseCustomer response = ResponseCustomer.from(output);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseCustomer> get(@RequestParam String email){
        OutputCustomer output = getCustomerUseCase.execute(email);
        ResponseCustomer response = ResponseCustomer.from(output);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/address")
    public ResponseEntity<ResponseDeliverAddress> update(@RequestParam String email, @RequestBody RequestUpdateDeliverAddress request){
        InputUpdateDeliverAddress input = RequestUpdateDeliverAddress.toInput(request);
        OutputDeliverAddress output = updateDeliverAddressUseCase.execute(email, input);
        ResponseDeliverAddress response = ResponseDeliverAddress.from(output);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/address")
    public ResponseEntity<ResponseDeliverAddress> createAddress(@RequestParam String email, @RequestBody RequestCreateDeliverAddress request){
        InputCreateDeliverAddress input = RequestCreateDeliverAddress.toInput(request);
        OutputDeliverAddress output = createDeliverAddress.execute(email, input);
        ResponseDeliverAddress response = ResponseDeliverAddress.from(output);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
