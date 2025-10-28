package adapter;

import api.InstagramAPI;
import interfaces.GerenciadorMidiaSocial;
import model.Conteudo;
import model.Estatisticas;
import model.Publicacao;
import model.StatusPublicacao;

// Adapter para a API do Instagram
public class InstagramAdapter implements GerenciadorMidiaSocial {
    private InstagramAPI instagramAPI;

    public InstagramAdapter() {
        this.instagramAPI = new InstagramAPI();
    }

    @Override
    public Publicacao publicar(Conteudo conteudo) {
        try {
            // Converte dados para o formato do Instagram
            String[] imageUrls = conteudo.getMidias() != null
                ? conteudo.getMidias().toArray(new String[0])
                : new String[0];

            String[] tags = conteudo.getHashtags() != null
                ? conteudo.getHashtags().toArray(new String[0])
                : new String[0];

            // Monta caption com título e texto
            String caption = conteudo.getTitulo() != null
                ? conteudo.getTitulo() + "\n\n" + conteudo.getTexto()
                : conteudo.getTexto();

            InstagramAPI.InstagramPostRequest request =
                new InstagramAPI.InstagramPostRequest(caption, imageUrls, tags);

            InstagramAPI.InstagramResponse response = instagramAPI.publishPost(request);

            if (response.success) {
                return new Publicacao(response.postId, "Instagram", conteudo, StatusPublicacao.PUBLICADO);
            } else {
                Publicacao publicacao = new Publicacao(null, "Instagram", conteudo, StatusPublicacao.FALHOU);
                publicacao.setMensagemErro(response.message);
                return publicacao;
            }

        } catch (Exception e) {
            Publicacao publicacao = new Publicacao(null, "Instagram", conteudo, StatusPublicacao.FALHOU);
            publicacao.setMensagemErro(e.getMessage());
            return publicacao;
        }
    }

    @Override
    public Estatisticas obterEstatisticas(String publicacaoId) {
        InstagramAPI.InstagramInsights insights = instagramAPI.getInsights(publicacaoId);

        Estatisticas stats = new Estatisticas(publicacaoId, "Instagram");
        stats.setCurtidas(insights.likes);
        stats.setComentarios(insights.comments);
        stats.setCompartilhamentos(insights.saves); // Saves como compartilhamentos
        stats.setVisualizacoes(insights.reach);
        stats.setTaxaEngajamento(insights.engagement_rate);

        return stats;
    }

    @Override
    public boolean deletarPublicacao(String publicacaoId) {
        // Instagram API não implementa deleção nesta simulação
        System.out.println("[Instagram] Deleção não disponível na API");
        return false;
    }

    @Override
    public String getNomePlataforma() {
        return "Instagram";
    }
}

