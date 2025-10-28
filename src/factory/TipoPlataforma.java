package factory;

public enum TipoPlataforma {
    TWITTER("Twitter"),
    INSTAGRAM("Instagram"),
    LINKEDIN("LinkedIn"),
    TIKTOK("TikTok");

    private String nome;

    TipoPlataforma(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public static TipoPlataforma fromString(String nome) {
        for (TipoPlataforma tipo : TipoPlataforma.values()) {
            if (tipo.nome.equalsIgnoreCase(nome)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Plataforma não encontrada: " + nome);
    }
}

