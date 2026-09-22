package dev.jeffersonfreitas.order.infra.config;

import dev.jeffersonfreitas.order.application.exception.BusinessException;
import dev.jeffersonfreitas.order.application.exception.OrderNotFoundException;
import dev.jeffersonfreitas.order.application.exception.ProductNotBeDeletedException;
import dev.jeffersonfreitas.order.application.exception.ProductNotFoundException;
import dev.jeffersonfreitas.order.domain.exception.InvalidValueObjectException;
import dev.jeffersonfreitas.order.domain.exception.ProductAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ProblemDetail handleProductNotFoundException(ProductNotFoundException e){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setTitle("Produto não encontrado");
        return problemDetail;
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ProblemDetail handleOrderNotFoundException(OrderNotFoundException e){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setTitle("Pedido não encontrado");
        return problemDetail;
    }    

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ProblemDetail handleProductAlreadyExistsException(ProductAlreadyExistsException e){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        problemDetail.setTitle("Produto já cadastrado");
        return problemDetail;
    }

    @ExceptionHandler(InvalidValueObjectException.class)
    public ProblemDetail handleInvalidValueObjectException(InvalidValueObjectException e){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        problemDetail.setTitle("Valores inválidos foram informados");
        return problemDetail;
    }

    @ExceptionHandler(BusinessException.class)
    public ProblemDetail handleBusinessException(BusinessException e){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        problemDetail.setTitle("Houve um problema com a sua requisição");
        return problemDetail;
    }

    @ExceptionHandler(ProductNotBeDeletedException.class)
    public ProblemDetail handleProductNotBeDeletedException(ProductNotBeDeletedException e){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        problemDetail.setTitle("Produto não pode ser deletado");
        return problemDetail;
    }

}
