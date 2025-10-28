package strategy;

import interfaces.GerenciadorMidiaSocial;
import model.Conteudo;
import model.Publicacao;
import model.StatusPublicacao;

import java.util.ArrayList;
import java.util.List;

// Estratégia que continua publicando mesmo se houver falhas
public class PublicacaoComFallback implements EstrategiaPublicacao {

    @Override
    public List<Publicacao> executar(Conteudo conteudo, List<GerenciadorMidiaSocial> plataformas) {
        System.out.println("\n[Estratégia] Publicação com Fallback (continua mesmo com falhas)");
        List<Publicacao> publicacoes = new ArrayList<>();
        int sucesso = 0;
        int falhas = 0;

        for (GerenciadorMidiaSocial plataforma : plataformas) {
            try {
                System.out.println("Tentando publicar em: " + plataforma.getNomePlataforma());
                Publicacao pub = plataforma.publicar(conteudo);
                publicacoes.add(pub);

                if (pub.getStatus() == StatusPublicacao.PUBLICADO) {
                    sucesso++;
                    System.out.println("✓ Sucesso em " + plataforma.getNomePlataforma());
                } else {
                    falhas++;
                    System.out.println("✗ Falhou em " + plataforma.getNomePlataforma() + ": " + pub.getMensagemErro());
                }
            } catch (Exception e) {
                falhas++;
                System.out.println("✗ Erro em " + plataforma.getNomePlataforma() + ": " + e.getMessage());
            }
        }

        System.out.println("\nResultado: " + sucesso + " sucessos, " + falhas + " falhas");
        return publicacoes;
    }

    @Override
    public String getNomeEstrategia() {
        return "Publicação com Fallback";
    }
}
