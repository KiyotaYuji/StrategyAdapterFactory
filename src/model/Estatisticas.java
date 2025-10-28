package model;

public class Estatisticas {
    private String publicacaoId;
    private String plataforma;
    private int curtidas;
    private int compartilhamentos;
    private int comentarios;
    private int visualizacoes;
    private double taxaEngajamento;

    public Estatisticas(String publicacaoId, String plataforma) {
        this.publicacaoId = publicacaoId;
        this.plataforma = plataforma;
    }

    public String getPublicacaoId() {
        return publicacaoId;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public int getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(int curtidas) {
        this.curtidas = curtidas;
    }

    public int getCompartilhamentos() {
        return compartilhamentos;
    }

    public void setCompartilhamentos(int compartilhamentos) {
        this.compartilhamentos = compartilhamentos;
    }

    public int getComentarios() {
        return comentarios;
    }

    public void setComentarios(int comentarios) {
        this.comentarios = comentarios;
    }

    public int getVisualizacoes() {
        return visualizacoes;
    }

    public void setVisualizacoes(int visualizacoes) {
        this.visualizacoes = visualizacoes;
    }

    public double getTaxaEngajamento() {
        return taxaEngajamento;
    }

    public void setTaxaEngajamento(double taxaEngajamento) {
        this.taxaEngajamento = taxaEngajamento;
    }

    @Override
    public String toString() {
        return "Estatisticas{" +
                "plataforma='" + plataforma + '\'' +
                ", curtidas=" + curtidas +
                ", compartilhamentos=" + compartilhamentos +
                ", comentarios=" + comentarios +
                ", visualizacoes=" + visualizacoes +
                ", taxaEngajamento=" + String.format("%.2f%%", taxaEngajamento) +
                '}';
    }
}
