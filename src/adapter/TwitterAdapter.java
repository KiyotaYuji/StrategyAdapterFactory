package adapter;

import api.TwitterAPI;
import interfaces.GerenciadorMidiaSocial;
import model.Conteudo;
import model.Estatisticas;
import model.Publicacao;
import model.StatusPublicacao;

// Adapter para a API do Twitter
public class TwitterAdapter implements GerenciadorMidiaSocial {
    private TwitterAPI twitterAPI;

    public TwitterAdapter() {
        this.twitterAPI = new TwitterAPI();
    }

    @Override
    public Publicacao publicar(Conteudo conteudo) {
        try {
            // Converte hashtags para array
            String[] hashtags = conteudo.getHashtags() != null
                ? conteudo.getHashtags().toArray(new String[0])
                : new String[0];

            // Converte mídias para array
            String[] media = conteudo.getMidias() != null
                ? conteudo.getMidias().toArray(new String[0])
                : new String[0];

            // Formata o texto com hashtags
            String texto = conteudo.getTexto();
            if (hashtags.length > 0) {
                texto += " " + String.join(" ", hashtags);
            }

            // Publica no Twitter
            String tweetId = twitterAPI.postTweet(texto, media, hashtags);

            Publicacao publicacao = new Publicacao(tweetId, "Twitter", conteudo, StatusPublicacao.PUBLICADO);
            return publicacao;

        } catch (Exception e) {
            Publicacao publicacao = new Publicacao(null, "Twitter", conteudo, StatusPublicacao.FALHOU);
            publicacao.setMensagemErro(e.getMessage());
            return publicacao;
        }
    }

    @Override
    public Estatisticas obterEstatisticas(String publicacaoId) {
        TwitterAPI.TweetMetrics metrics = twitterAPI.getMetrics(publicacaoId);

        Estatisticas stats = new Estatisticas(publicacaoId, "Twitter");
        stats.setCurtidas(metrics.likes);
        stats.setCompartilhamentos(metrics.retweets);
        stats.setComentarios(metrics.replies);
        stats.setVisualizacoes(metrics.impressions);

        // Calcula taxa de engajamento
        double engajamento = ((double)(metrics.likes + metrics.retweets + metrics.replies) / metrics.impressions) * 100;
        stats.setTaxaEngajamento(engajamento);

        return stats;
    }

    @Override
    public boolean deletarPublicacao(String publicacaoId) {
        return twitterAPI.deleteTweet(publicacaoId);
    }

    @Override
    public String getNomePlataforma() {
        return "Twitter";
    }
}

