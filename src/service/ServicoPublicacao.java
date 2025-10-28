package service;

import factory.SocialMediaFactory;
import factory.TipoPlataforma;
import interfaces.GerenciadorMidiaSocial;
import model.Conteudo;
import model.Estatisticas;
import model.Publicacao;
import strategy.EstrategiaPublicacao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Serviço central que gerencia todas as publicações
public class ServicoPublicacao {
    private List<GerenciadorMidiaSocial> plataformas;
    private EstrategiaPublicacao estrategia;
    private Map<String, Publicacao> historicoPublicacoes;

    public ServicoPublicacao() {
        this.plataformas = new ArrayList<>();
        this.historicoPublicacoes = new HashMap<>();
    }

    // Adiciona uma plataforma usando o factory
    public void adicionarPlataforma(TipoPlataforma tipo) {
        GerenciadorMidiaSocial gerenciador = SocialMediaFactory.criarGerenciador(tipo);
        plataformas.add(gerenciador);
        System.out.println("Plataforma adicionada: " + gerenciador.getNomePlataforma());
    }

    // Adiciona plataforma por nome (configuração dinâmica)
    public void adicionarPlataforma(String nomePlataforma) {
        GerenciadorMidiaSocial gerenciador = SocialMediaFactory.criarGerenciador(nomePlataforma);
        plataformas.add(gerenciador);
        System.out.println("Plataforma adicionada: " + gerenciador.getNomePlataforma());
    }

    // Define a estratégia de publicação
    public void setEstrategia(EstrategiaPublicacao estrategia) {
        this.estrategia = estrategia;
        System.out.println("Estratégia definida: " + estrategia.getNomeEstrategia());
    }

    // Publica conteúdo em todas as plataformas usando a estratégia definida
    public List<Publicacao> publicar(Conteudo conteudo) {
        if (estrategia == null) {
            throw new IllegalStateException("Nenhuma estratégia de publicação definida!");
        }

        if (plataformas.isEmpty()) {
            throw new IllegalStateException("Nenhuma plataforma configurada!");
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("Iniciando publicação: " + conteudo.getTitulo());
        System.out.println("=".repeat(60));

        List<Publicacao> publicacoes = estrategia.executar(conteudo, plataformas);

        // Armazena no histórico
        for (Publicacao pub : publicacoes) {
            if (pub.getId() != null) {
                historicoPublicacoes.put(pub.getId(), pub);
            }
        }

        return publicacoes;
    }

    // Obtém estatísticas unificadas de todas as publicações
    public Map<String, Estatisticas> obterTodasEstatisticas(List<Publicacao> publicacoes) {
        Map<String, Estatisticas> estatisticas = new HashMap<>();

        System.out.println("\n" + "=".repeat(60));
        System.out.println("Obtendo estatísticas das publicações");
        System.out.println("=".repeat(60));

        for (Publicacao pub : publicacoes) {
            if (pub.getId() != null) {
                try {
                    GerenciadorMidiaSocial plataforma = encontrarPlataforma(pub.getPlataforma());
                    if (plataforma != null) {
                        Estatisticas stats = plataforma.obterEstatisticas(pub.getId());
                        estatisticas.put(pub.getId(), stats);
                    }
                } catch (Exception e) {
                    System.err.println("Erro ao obter estatísticas de " + pub.getPlataforma() + ": " + e.getMessage());
                }
            }
        }

        return estatisticas;
    }

    // Exibe relatório consolidado
    public void exibirRelatorio(List<Publicacao> publicacoes, Map<String, Estatisticas> estatisticas) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("RELATÓRIO DE PUBLICAÇÕES");
        System.out.println("=".repeat(60));

        for (Publicacao pub : publicacoes) {
            System.out.println("\n" + pub);
            if (pub.getId() != null && estatisticas.containsKey(pub.getId())) {
                System.out.println(estatisticas.get(pub.getId()));
            }
        }

        System.out.println("\n" + "=".repeat(60));
    }

    // Encontra plataforma pelo nome
    private GerenciadorMidiaSocial encontrarPlataforma(String nomePlataforma) {
        for (GerenciadorMidiaSocial plataforma : plataformas) {
            if (plataforma.getNomePlataforma().equalsIgnoreCase(nomePlataforma)) {
                return plataforma;
            }
        }
        return null;
    }

    // Limpa todas as plataformas
    public void limparPlataformas() {
        plataformas.clear();
        System.out.println("Todas as plataformas foram removidas");
    }

    // Getters
    public List<GerenciadorMidiaSocial> getPlataformas() {
        return new ArrayList<>(plataformas);
    }

    public Map<String, Publicacao> getHistoricoPublicacoes() {
        return new HashMap<>(historicoPublicacoes);
    }
}

