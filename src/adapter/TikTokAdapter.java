package adapter;

import api.TikTokAPI;
import interfaces.GerenciadorMidiaSocial;
import model.Conteudo;
import model.Estatisticas;
import model.Publicacao;
import model.StatusPublicacao;

// Adapter para a API do TikTok
public class TikTokAdapter implements GerenciadorMidiaSocial {
    private TikTokAPI tiktokAPI;

    public TikTokAdapter() {
        this.tiktokAPI = new TikTokAPI();
    }

    @Override
    public Publicacao publicar(Conteudo conteudo) {
        try {
            // TikTok precisa de URL de vídeo
            String videoUrl = (conteudo.getMidias() != null && !conteudo.getMidias().isEmpty())
                ? conteudo.getMidias().get(0)
                : null;

            String[] hashtags = conteudo.getHashtags() != null
                ? conteudo.getHashtags().toArray(new String[0])
                : new String[0];

            TikTokAPI.TikTokVideoData videoData =
                new TikTokAPI.TikTokVideoData(videoUrl, conteudo.getTexto(), hashtags);

            TikTokAPI.TikTokUploadResult result = tiktokAPI.uploadVideo(videoData);

            if (result.success) {
                return new Publicacao(result.videoId, "TikTok", conteudo, StatusPublicacao.PUBLICADO);
            } else {
                Publicacao publicacao = new Publicacao(null, "TikTok", conteudo, StatusPublicacao.FALHOU);
                publicacao.setMensagemErro(result.message);
                return publicacao;
            }

        } catch (Exception e) {
            Publicacao publicacao = new Publicacao(null, "TikTok", conteudo, StatusPublicacao.FALHOU);
            publicacao.setMensagemErro(e.getMessage());
            return publicacao;
        }
    }

    @Override
    public Estatisticas obterEstatisticas(String publicacaoId) {
        TikTokAPI.TikTokStats stats = tiktokAPI.getVideoStats(publicacaoId);

        Estatisticas estatisticas = new Estatisticas(publicacaoId, "TikTok");
        estatisticas.setCurtidas(stats.likes);
        estatisticas.setComentarios(stats.comments);
        estatisticas.setCompartilhamentos(stats.shares);
        estatisticas.setVisualizacoes(stats.views);

        // Calcula engajamento
        double engajamento = ((double)(stats.likes + stats.comments + stats.shares + stats.favorites) / stats.views) * 100;
        estatisticas.setTaxaEngajamento(engajamento);

        return estatisticas;
    }

    @Override
    public boolean deletarPublicacao(String publicacaoId) {
        System.out.println("[TikTok] Deleção não disponível na API");
        return false;
    }

    @Override
    public String getNomePlataforma() {
        return "TikTok";
    }
}

