package strategy;

import interfaces.GerenciadorMidiaSocial;
import model.Conteudo;
import model.Publicacao;

import java.util.List;

// Interface para estratégias de publicação
public interface EstrategiaPublicacao {
    List<Publicacao> executar(Conteudo conteudo, List<GerenciadorMidiaSocial> plataformas);
    String getNomeEstrategia();
}

