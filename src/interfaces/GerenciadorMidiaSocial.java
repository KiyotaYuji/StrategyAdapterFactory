package interfaces;

import model.Conteudo;
import model.Publicacao;
import model.Estatisticas;

// Interface unificada para todas as plataformas de mídia social
public interface GerenciadorMidiaSocial {
    Publicacao publicar(Conteudo conteudo);
    Estatisticas obterEstatisticas(String publicacaoId);
    boolean deletarPublicacao(String publicacaoId);
    String getNomePlataforma();
}

