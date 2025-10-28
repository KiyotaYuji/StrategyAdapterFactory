package strategy;

import interfaces.GerenciadorMidiaSocial;
import model.Conteudo;
import model.Publicacao;
import model.StatusPublicacao;

import java.util.ArrayList;
import java.util.List;

// Estratégia que publica sequencialmente com intervalo
public class PublicacaoSequencial implements EstrategiaPublicacao {
    private long intervaloMs;

    public PublicacaoSequencial(long intervaloMs) {
        this.intervaloMs = intervaloMs;
    }

    @Override
    public List<Publicacao> executar(Conteudo conteudo, List<GerenciadorMidiaSocial> plataformas) {
        System.out.println("\n[Estratégia] Publicação Sequencial com intervalo de " + intervaloMs + "ms");
        List<Publicacao> publicacoes = new ArrayList<>();

        for (int i = 0; i < plataformas.size(); i++) {
            GerenciadorMidiaSocial plataforma = plataformas.get(i);

            if (i > 0) {
                try {
                    System.out.println("Aguardando " + intervaloMs + "ms...");
                    Thread.sleep(intervaloMs);
                } catch (InterruptedException e) {
                    System.err.println("Erro no intervalo: " + e.getMessage());
                }
            }

            System.out.println("Publicando em: " + plataforma.getNomePlataforma());
            Publicacao pub = plataforma.publicar(conteudo);
            publicacoes.add(pub);
        }

        return publicacoes;
    }

    @Override
    public String getNomeEstrategia() {
        return "Publicação Sequencial";
    }
}

