package model;

import java.time.LocalDateTime;
import java.util.List;

public class Conteudo {
    private String titulo;
    private String texto;
    private List<String> midias; // URLs de imagens/vídeos
    private List<String> hashtags;
    private LocalDateTime dataAgendamento;

    public Conteudo(String titulo, String texto, List<String> midias, List<String> hashtags, LocalDateTime dataAgendamento) {
        this.titulo = titulo;
        this.texto = texto;
        this.midias = midias;
        this.hashtags = hashtags;
        this.dataAgendamento = dataAgendamento;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getTexto() {
        return texto;
    }

    public List<String> getMidias() {
        return midias;
    }

    public List<String> getHashtags() {
        return hashtags;
    }

    public LocalDateTime getDataAgendamento() {
        return dataAgendamento;
    }

    @Override
    public String toString() {
        return "Conteudo{" +
                "titulo='" + titulo + '\'' +
                ", texto='" + texto + '\'' +
                ", midias=" + midias +
                ", hashtags=" + hashtags +
                ", dataAgendamento=" + dataAgendamento +
                '}';
    }
}

