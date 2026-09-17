package dev.jeffersonfreitas.customer.infra.in.web.customer;

import java.time.LocalDate;

import dev.jeffersonfreitas.customer.application.port.in.customer.create.InputCreateCustomer;
import dev.jeffersonfreitas.customer.application.port.in.customer.create.InputCreateDeliverAddress;
import dev.jeffersonfreitas.customer.infra.exceptions.NullRequestsExceptions;

public record RequestCreateCustomer(
        String name,
        String email,
        LocalDate birthdate,
        RequestCreateDeliverAddress address
) {

        public static InputCreateCustomer toInput(RequestCreateCustomer request) {
                if(request == null){
                        throw new NullRequestsExceptions("O objeto da requisição não pode ser nulo");
                }

                InputCreateDeliverAddress address = RequestCreateDeliverAddress.toInput(request.address);
                return new InputCreateCustomer(request.name, request.email, request.birthdate, address);
        }
}
