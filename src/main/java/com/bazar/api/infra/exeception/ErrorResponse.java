package com.bazar.api.infra.exeception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private int status;
    private String erro;
    private String mensagem;
    private String caminho;
    private LocalDateTime timestamp;
    private List<CampoErro> campos;

    public ErrorResponse(int status, String erro, String mensagem, String caminho) {
        this.status = status;
        this.erro = erro;
        this.mensagem = mensagem;
        this.caminho = caminho;
        this.timestamp = LocalDateTime.now();
    }

    // -------  Getters e Setters  -------

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public String getErro() { return erro; }
    public void setErro(String erro) { this.erro = erro; }

    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }

    public String getCaminho() { return caminho; }
    public void setCaminho(String caminho) { this.caminho = caminho; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public List<CampoErro> getCampos() { return campos; }
    public void setCampos(List<CampoErro> campos) { this.campos = campos; }

    // -------  Classe interna para erros de validação por campo  -------

    public static class CampoErro {
        private String campo;
        private String mensagem;

        public CampoErro(String campo, String mensagem) {
            this.campo = campo;
            this.mensagem = mensagem;
        }

        public String getCampo() { return campo; }
        public void setCampo(String campo) { this.campo = campo; }

        public String getMensagem() { return mensagem; }
        public void setMensagem(String mensagem) { this.mensagem = mensagem; }
    }
}
