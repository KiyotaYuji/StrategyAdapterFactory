package model;

import java.time.LocalDateTime;

public class Publicacao {
    private String id;
    private String plataforma;
    private Conteudo conteudo;
    private StatusPublicacao status;
    private LocalDateTime dataPublicacao;
    private String mensagemErro;

    public Publicacao(String id, String plataforma, Conteudo conteudo, StatusPublicacao status) {
        this.id = id;
        this.plataforma = plataforma;
        this.conteudo = conteudo;
        this.status = status;
        this.dataPublicacao = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public Conteudo getConteudo() {
        return conteudo;
    }

    public StatusPublicacao getStatus() {
        return status;
    }

    public void setStatus(StatusPublicacao status) {
        this.status = status;
    }

    public LocalDateTime getDataPublicacao() {
        return dataPublicacao;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }

    @Override
    public String toString() {
        return "Publicacao{" +
                "id='" + id + '\'' +
                ", plataforma='" + plataforma + '\'' +
                ", status=" + status +
                ", dataPublicacao=" + dataPublicacao +
                (mensagemErro != null ? ", erro='" + mensagemErro + '\'' : "") +
                '}';
    }
}

