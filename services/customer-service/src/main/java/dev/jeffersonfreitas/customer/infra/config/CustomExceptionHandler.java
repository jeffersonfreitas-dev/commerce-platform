package dev.jeffersonfreitas.customer.infra.config;

import dev.jeffersonfreitas.customer.application.exception.DeliverAddressNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import dev.jeffersonfreitas.customer.application.exception.AddressNotBelongException;
import dev.jeffersonfreitas.customer.application.exception.BusinessException;
import dev.jeffersonfreitas.customer.application.exception.CustomerNotFoundException;
import dev.jeffersonfreitas.customer.domain.exception.CustomerAlreadyExistsException;
import dev.jeffersonfreitas.customer.domain.exception.InvalidValueObjectException;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ProblemDetail handleCustomerNotFoundException(CustomerNotFoundException e){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setTitle("Cliente não encontrado");
        return problemDetail;
    }

    @ExceptionHandler(DeliverAddressNotFoundException.class)
    public ProblemDetail handleDeliverAddressNotFoundException(DeliverAddressNotFoundException e){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setTitle("Endereço de entrega não encontrado");
        return problemDetail;
    }

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ProblemDetail handleCustomerAlreadyExistsException(CustomerAlreadyExistsException e){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        problemDetail.setTitle("Cliente já cadastrado");
        return problemDetail;
    }

    @ExceptionHandler(InvalidValueObjectException.class)
    public ProblemDetail handleInvalidValueObjectException(InvalidValueObjectException e){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        problemDetail.setTitle("Valores inválidos foram informados");
        return problemDetail;
    }

    @ExceptionHandler(NullPointerException.class)
    public ProblemDetail handleNullPointerException(NullPointerException e){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        problemDetail.setTitle("Objeto nulo");
        return problemDetail;
    }    

    @ExceptionHandler(BusinessException.class)
    public ProblemDetail handleBusinessException(BusinessException e){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        problemDetail.setTitle("Houve um problema com a sua requisição");
        return problemDetail;
    }

    @ExceptionHandler(AddressNotBelongException.class)
    public ProblemDetail handleAddressNotBelongException(AddressNotBelongException e){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
        problemDetail.setTitle("Problema ao buscar endereço do cliente");
        return problemDetail;
    }

}
