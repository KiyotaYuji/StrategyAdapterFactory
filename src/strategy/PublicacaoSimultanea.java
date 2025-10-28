package strategy;

import interfaces.GerenciadorMidiaSocial;
import model.Conteudo;
import model.Publicacao;

import java.util.ArrayList;
import java.util.List;

// Estratégia que publica em todas as plataformas simultaneamente
public class PublicacaoSimultanea implements EstrategiaPublicacao {

    @Override
    public List<Publicacao> executar(Conteudo conteudo, List<GerenciadorMidiaSocial> plataformas) {
        System.out.println("\n[Estratégia] Publicação Simultânea em " + plataformas.size() + " plataformas");
        List<Publicacao> publicacoes = new ArrayList<>();

        for (GerenciadorMidiaSocial plataforma : plataformas) {
            System.out.println("Publicando em: " + plataforma.getNomePlataforma());
            Publicacao pub = plataforma.publicar(conteudo);
            publicacoes.add(pub);
        }

        return publicacoes;
    }

    @Override
    public String getNomeEstrategia() {
        return "Publicação Simultânea";
    }
}

