import factory.TipoPlataforma;
import model.Conteudo;
import model.Estatisticas;
import model.Publicacao;
import service.ServicoPublicacao;
import strategy.PublicacaoComFallback;
import strategy.PublicacaoSequencial;
import strategy.PublicacaoSimultanea;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("=".repeat(70));
        System.out.println("SISTEMA DE INTEGRAÇÃO DE APIS DE MÍDIA SOCIAL");
        System.out.println("Design Patterns: Adapter + Strategy + Factory Method");
        System.out.println("=".repeat(70));

        // Demonstração 1: Publicação Simultânea em todas as plataformas
        demonstracaoPublicacaoSimultanea();

        // Demonstração 2: Publicação Sequencial com intervalo
        demonstracaoPublicacaoSequencial();

        // Demonstração 3: Publicação com Fallback (tratamento de erros)
        demonstracaoPublicacaoComFallback();

        // Demonstração 4: Configuração dinâmica por ambiente
        demonstracaoConfiguracaoDinamica();
    }

    private static void demonstracaoPublicacaoSimultanea() {
        System.out.println("\n\n" + "█".repeat(70));
        System.out.println("DEMONSTRAÇÃO 1: PUBLICAÇÃO SIMULTÂNEA");
        System.out.println("█".repeat(70));

        ServicoPublicacao servico = new ServicoPublicacao();

        // Adiciona plataformas usando Factory
        servico.adicionarPlataforma(TipoPlataforma.TWITTER);
        servico.adicionarPlataforma(TipoPlataforma.INSTAGRAM);
        servico.adicionarPlataforma(TipoPlataforma.LINKEDIN);

        // Define estratégia
        servico.setEstrategia(new PublicacaoSimultanea());

        // Cria conteúdo
        Conteudo conteudo = new Conteudo(
            "Lançamento de Produto",
            "Estamos muito felizes em anunciar o lançamento do nosso novo produto! 🚀",
            Arrays.asList("https://exemplo.com/imagem1.jpg", "https://exemplo.com/imagem2.jpg"),
            Arrays.asList("#lancamento", "#novoproduto", "#inovacao"),
            LocalDateTime.now()
        );

        // Publica
        List<Publicacao> publicacoes = servico.publicar(conteudo);

        // Obtém estatísticas
        Map<String, Estatisticas> stats = servico.obterTodasEstatisticas(publicacoes);

        // Exibe relatório
        servico.exibirRelatorio(publicacoes, stats);
    }

    private static void demonstracaoPublicacaoSequencial() {
        System.out.println("\n\n" + "█".repeat(70));
        System.out.println("DEMONSTRAÇÃO 2: PUBLICAÇÃO SEQUENCIAL");
        System.out.println("█".repeat(70));

        ServicoPublicacao servico = new ServicoPublicacao();

        // Adiciona plataformas
        servico.adicionarPlataforma(TipoPlataforma.TWITTER);
        servico.adicionarPlataforma(TipoPlataforma.TIKTOK);
        servico.adicionarPlataforma(TipoPlataforma.INSTAGRAM);

        // Define estratégia sequencial com intervalo de 1 segundo
        servico.setEstrategia(new PublicacaoSequencial(1000));

        // Cria conteúdo
        Conteudo conteudo = new Conteudo(
            "Dicas de Marketing Digital",
            "5 estratégias infalíveis para aumentar seu engajamento nas redes sociais! 📈",
            Arrays.asList("https://exemplo.com/video.mp4"),
            Arrays.asList("#marketing", "#digital", "#dicas", "#engajamento"),
            LocalDateTime.now()
        );

        // Publica
        List<Publicacao> publicacoes = servico.publicar(conteudo);

        // Obtém estatísticas
        Map<String, Estatisticas> stats = servico.obterTodasEstatisticas(publicacoes);

        // Exibe relatório
        servico.exibirRelatorio(publicacoes, stats);
    }

    private static void demonstracaoPublicacaoComFallback() {
        System.out.println("\n\n" + "█".repeat(70));
        System.out.println("DEMONSTRAÇÃO 3: PUBLICAÇÃO COM FALLBACK");
        System.out.println("█".repeat(70));

        ServicoPublicacao servico = new ServicoPublicacao();

        // Adiciona todas as plataformas
        servico.adicionarPlataforma(TipoPlataforma.TWITTER);
        servico.adicionarPlataforma(TipoPlataforma.INSTAGRAM);
        servico.adicionarPlataforma(TipoPlataforma.LINKEDIN);
        servico.adicionarPlataforma(TipoPlataforma.TIKTOK);

        // Define estratégia com fallback
        servico.setEstrategia(new PublicacaoComFallback());

        // Cria conteúdo que pode falhar em algumas plataformas
        Conteudo conteudo = new Conteudo(
            "Webinar Gratuito",
            "Participe do nosso webinar sobre tendências de tecnologia! Inscreva-se agora e garanta sua vaga. Link na bio! 💻",
            Arrays.asList("https://exemplo.com/banner.jpg"),
            Arrays.asList("#webinar", "#tecnologia", "#gratuito"),
            LocalDateTime.now()
        );

        // Publica
        List<Publicacao> publicacoes = servico.publicar(conteudo);

        // Obtém estatísticas apenas das publicações bem-sucedidas
        Map<String, Estatisticas> stats = servico.obterTodasEstatisticas(publicacoes);

        // Exibe relatório
        servico.exibirRelatorio(publicacoes, stats);
    }

    private static void demonstracaoConfiguracaoDinamica() {
        System.out.println("\n\n" + "█".repeat(70));
        System.out.println("DEMONSTRAÇÃO 4: CONFIGURAÇÃO DINÂMICA");
        System.out.println("█".repeat(70));

        ServicoPublicacao servico = new ServicoPublicacao();

        // Simula configuração vinda de arquivo ou variável de ambiente
        String[] plataformasConfig = {"Twitter", "LinkedIn"}; // Poderia vir de um arquivo .properties

        System.out.println("\nCarregando configuração de plataformas...");
        for (String plataforma : plataformasConfig) {
            servico.adicionarPlataforma(plataforma); // Usa o factory com string
        }

        // Define estratégia
        servico.setEstrategia(new PublicacaoSimultanea());

        // Cria conteúdo profissional para LinkedIn e Twitter
        Conteudo conteudo = new Conteudo(
            "Insights sobre Arquitetura de Software",
            "Como os Design Patterns podem transformar a qualidade do seu código. " +
            "Adapter, Strategy e Factory Method em ação! #SoftwareEngineering",
            Arrays.asList("https://exemplo.com/diagram.png"),
            Arrays.asList("#designpatterns", "#softwarearchitecture", "#coding"),
            LocalDateTime.now()
        );

        // Publica
        List<Publicacao> publicacoes = servico.publicar(conteudo);

        // Obtém estatísticas
        Map<String, Estatisticas> stats = servico.obterTodasEstatisticas(publicacoes);

        // Exibe relatório
        servico.exibirRelatorio(publicacoes, stats);

        System.out.println("\n" + "█".repeat(70));
        System.out.println("FIM DAS DEMONSTRAÇÕES");
        System.out.println("█".repeat(70));
    }
}