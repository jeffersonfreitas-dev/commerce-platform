package dev.jeffersonfreitas.customer.infra.in.web.customer;

import dev.jeffersonfreitas.customer.application.port.in.customer.active.UCDeliverAddressActive;
import dev.jeffersonfreitas.customer.application.port.in.customer.active.UCDeliverAddressDeactive;
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
    private final UCDeliverAddressActive ucDeliverAddressActive;
    private final UCDeliverAddressDeactive ucDeliverAddressDeactive;

    public CustomerController(UCCreateCustomer createCustomerUseCase, UCGetCustomer getCustomerUseCase,
                              UCUpdateDeliverAddress updateDeliverAddressUseCase, UCCreateDeliverAddress createDeliverAddress,
                              UCDeliverAddressActive ucDeliverAddressActive, UCDeliverAddressDeactive ucDeliverAddressDeactive) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.getCustomerUseCase = getCustomerUseCase;
        this.updateDeliverAddressUseCase = updateDeliverAddressUseCase;
        this.createDeliverAddress = createDeliverAddress;
        this.ucDeliverAddressActive = ucDeliverAddressActive;
        this.ucDeliverAddressDeactive = ucDeliverAddressDeactive;
    }

    @PostMapping
    public ResponseEntity<ResponseCustomer> create(@RequestBody RequestCreateCustomer request){
        InputCreateCustomer input = RequestCreateCustomer.toInput(request);
        OutputCustomer output = createCustomerUseCase.execute(input);
        ResponseCustomer response = ResponseCustomer.from(output);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseCustomer> get(@RequestParam(name = "email") String email){
        OutputCustomer output = getCustomerUseCase.execute(email);
        ResponseCustomer response = ResponseCustomer.from(output);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/address")
    public ResponseEntity<ResponseDeliverAddress> update(@RequestParam(name = "email") String email, @RequestBody RequestUpdateDeliverAddress request){
        InputUpdateDeliverAddress input = RequestUpdateDeliverAddress.toInput(request);
        OutputDeliverAddress output = updateDeliverAddressUseCase.execute(email, input);
        ResponseDeliverAddress response = ResponseDeliverAddress.from(output);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/address")
    public ResponseEntity<ResponseDeliverAddress> createAddress(@RequestParam(name = "email") String email, @RequestBody RequestCreateDeliverAddress request){
        InputCreateDeliverAddress input = RequestCreateDeliverAddress.toInput(request);
        OutputDeliverAddress output = createDeliverAddress.execute(email, input);
        ResponseDeliverAddress response = ResponseDeliverAddress.from(output);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/address/{addressId}/activate")
    public ResponseEntity<Void> active(@PathVariable(name = "addressId") String addressId,  @RequestParam(name = "email") String email){
        ucDeliverAddressActive.execute(email, addressId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/address/{addressId}/deactivate")
    public ResponseEntity<Void> deactive(@PathVariable(name = "addressId") String addressId, @RequestParam(name = "email") String email){
        ucDeliverAddressDeactive.execute(email, addressId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
