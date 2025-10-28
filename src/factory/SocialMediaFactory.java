package factory;

import adapter.InstagramAdapter;
import adapter.LinkedInAdapter;
import adapter.TikTokAdapter;
import adapter.TwitterAdapter;
import interfaces.GerenciadorMidiaSocial;

// Factory para criar instâncias de gerenciadores de mídia social
public class SocialMediaFactory {

    public static GerenciadorMidiaSocial criarGerenciador(TipoPlataforma tipo) {
        switch (tipo) {
            case TWITTER:
                return new TwitterAdapter();
            case INSTAGRAM:
                return new InstagramAdapter();
            case LINKEDIN:
                return new LinkedInAdapter();
            case TIKTOK:
                return new TikTokAdapter();
            default:
                throw new IllegalArgumentException("Plataforma não suportada: " + tipo);
        }
    }

    // Método com configuração dinâmica
    public static GerenciadorMidiaSocial criarGerenciador(String nomePlataforma) {
        TipoPlataforma tipo = TipoPlataforma.fromString(nomePlataforma);
        return criarGerenciador(tipo);
    }
}

