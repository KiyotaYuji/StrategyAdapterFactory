package adapter;

import api.LinkedInAPI;
import interfaces.GerenciadorMidiaSocial;
import model.Conteudo;
import model.Estatisticas;
import model.Publicacao;
import model.StatusPublicacao;

// Adapter para a API do LinkedIn
public class LinkedInAdapter implements GerenciadorMidiaSocial {
    private LinkedInAPI linkedInAPI;

    public LinkedInAdapter() {
        this.linkedInAPI = new LinkedInAPI();
    }

    @Override
    public Publicacao publicar(Conteudo conteudo) {
        try {
            String[] mediaUrls = conteudo.getMidias() != null
                ? conteudo.getMidias().toArray(new String[0])
                : new String[0];

            String[] topics = conteudo.getHashtags() != null
                ? conteudo.getHashtags().toArray(new String[0])
                : new String[0];

            LinkedInAPI.LinkedInArticleData articleData =
                new LinkedInAPI.LinkedInArticleData(
                    conteudo.getTitulo(),
                    conteudo.getTexto(),
                    mediaUrls,
                    topics
                );

            String articleId = linkedInAPI.createArticle(articleData);

            return new Publicacao(articleId, "LinkedIn", conteudo, StatusPublicacao.PUBLICADO);

        } catch (Exception e) {
            Publicacao publicacao = new Publicacao(null, "LinkedIn", conteudo, StatusPublicacao.FALHOU);
            publicacao.setMensagemErro(e.getMessage());
            return publicacao;
        }
    }

    @Override
    public Estatisticas obterEstatisticas(String publicacaoId) {
        LinkedInAPI.LinkedInAnalytics analytics = linkedInAPI.getAnalytics(publicacaoId);

        Estatisticas stats = new Estatisticas(publicacaoId, "LinkedIn");
        stats.setCurtidas(analytics.reactions);
        stats.setComentarios(analytics.comments);
        stats.setCompartilhamentos(analytics.shares);
        stats.setVisualizacoes(analytics.views);

        // Calcula engajamento baseado em CTR
        double engajamento = ((double)(analytics.reactions + analytics.comments + analytics.shares) / analytics.views) * 100;
        stats.setTaxaEngajamento(engajamento);

        return stats;
    }

    @Override
    public boolean deletarPublicacao(String publicacaoId) {
        System.out.println("[LinkedIn] Deleção não disponível na API");
        return false;
    }

    @Override
    public String getNomePlataforma() {
        return "LinkedIn";
    }
}

