package dev.jeffersonfreitas.order.domain.model;

import dev.jeffersonfreitas.order.application.exception.BusinessException;

public enum OrderStatus {
    CREATED,
    PAYMENT_PENDING,
    PAID,
    PAYMENT_FAILED,
    CANCELLED;

    public static OrderStatus from(String status) {
        try{
            return valueOf(status);
        }catch (Exception e){
           throw new BusinessException("Erro ao obter o status a partir do valor: " + status);
        }
    }
}
