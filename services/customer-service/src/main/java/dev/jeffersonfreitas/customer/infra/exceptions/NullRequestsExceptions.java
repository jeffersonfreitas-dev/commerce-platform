package dev.jeffersonfreitas.customer.infra.exceptions;

public class NullRequestsExceptions extends RuntimeException{

    public NullRequestsExceptions(String msg){
        super(msg);
    }

}
