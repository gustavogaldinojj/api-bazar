package com.bazar.api.infra.exeception;

public class ResourceNotFoundException extends RuntimeException{

    private final String recurso;
    private final Object identificador;

    public ResourceNotFoundException(String recurso, Object identificador) {
        super(String.format("%s com identificador '%s' não foi encontrado.", recurso, identificador));
        this.recurso = recurso;
        this.identificador = identificador;
    }

    public String getRecurso() {
        return recurso;
    }

    public Object getIdentificador() {
        return identificador;
    }
}
