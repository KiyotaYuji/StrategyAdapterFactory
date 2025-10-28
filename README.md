    # Sistema de Integração de APIs de Mídia Social

## 📋 Descrição do Projeto

Sistema unificado de gerenciamento de redes sociais que utiliza os padrões de design **Adapter**, **Strategy** e **Factory Method** para integrar múltiplas plataformas de mídia social (Twitter, Instagram, LinkedIn, TikTok) através de uma interface unificada.

## 🎯 Objetivos

- Demonstrar competência em design de software orientado a objetos
- Implementar integração com APIs heterogêneas de forma flexível
- Aplicar padrões de design para resolver problemas reais
- Criar arquitetura extensível e de fácil manutenção

## 🏗️ Arquitetura do Sistema

### Estrutura de Pastas

```
AdapterStrategyFactory/src/
├── api/                    # APIs simuladas das plataformas
│   ├── TwitterAPI.java
│   ├── InstagramAPI.java
│   ├── LinkedInAPI.java
│   └── TikTokAPI.java
├── model/                  # Modelos de dados unificados
│   ├── Conteudo.java
│   ├── Publicacao.java
│   ├── Estatisticas.java
│   └── StatusPublicacao.java
├── interfaces/             # Interface unificada
│   └── GerenciadorMidiaSocial.java
├── adapter/                # Adapters para cada plataforma
│   ├── TwitterAdapter.java
│   ├── InstagramAdapter.java
│   ├── LinkedInAdapter.java
│   └── TikTokAdapter.java
├── strategy/               # Estratégias de publicação
│   ├── EstrategiaPublicacao.java
│   ├── PublicacaoSimultanea.java
│   ├── PublicacaoSequencial.java
│   └── PublicacaoComFallback.java
├── factory/                # Factory Method
│   ├── SocialMediaFactory.java
│   └── TipoPlataforma.java
├── service/                # Serviço central
│   └── ServicoPublicacao.java
└── Main.java               # Demonstrações
```

## 🔧 Padrões de Design Implementados

### 1. **Adapter Pattern** 🔌

**Problema:** Cada plataforma de mídia social possui sua própria API com estruturas de dados, métodos e protocolos diferentes.

**Solução:** Criamos adapters que convertem a interface específica de cada plataforma para nossa interface unificada `GerenciadorMidiaSocial`.

**Implementação:**
- `TwitterAdapter` - Adapta a API do Twitter (limite de 280 caracteres)
- `InstagramAdapter` - Adapta a API do Instagram (requer imagens)
- `LinkedInAdapter` - Adapta a API do LinkedIn (focado em artigos)
- `TikTokAdapter` - Adapta a API do TikTok (focado em vídeos)

**Exemplo de uso:**
```java
GerenciadorMidiaSocial twitter = new TwitterAdapter();
Publicacao pub = twitter.publicar(conteudo);
```

### 2. **Strategy Pattern** 🎯

**Problema:** Diferentes cenários requerem diferentes estratégias de publicação (simultânea, sequencial, com tratamento de erros).

**Solução:** Encapsulamos cada algoritmo de publicação em uma classe separada que implementa `EstrategiaPublicacao`.

**Estratégias implementadas:**
- `PublicacaoSimultanea` - Publica em todas as plataformas ao mesmo tempo
- `PublicacaoSequencial` - Publica uma por vez com intervalo configurável
- `PublicacaoComFallback` - Continua publicando mesmo se houver falhas

**Exemplo de uso:**
```java
servico.setEstrategia(new PublicacaoSimultanea());
servico.publicar(conteudo);
```

### 3. **Factory Method Pattern** 🏭

**Problema:** Criar instâncias de adapters de forma flexível e desacoplada.

**Solução:** A `SocialMediaFactory` centraliza a criação de gerenciadores de mídia social.

**Vantagens:**
- Configuração dinâmica por string ou enum
- Fácil adição de novas plataformas
- Desacoplamento da lógica de criação

**Exemplo de uso:**
```java
// Por enum
GerenciadorMidiaSocial twitter = SocialMediaFactory.criarGerenciador(TipoPlataforma.TWITTER);

// Por string (configuração dinâmica)
GerenciadorMidiaSocial instagram = SocialMediaFactory.criarGerenciador("Instagram");
```


## 🚀 Como Executar

### Pré-requisitos
- Java 8 ou superior
- IDE Java (IntelliJ IDEA, Eclipse, etc.)

### Passos para execução

1. **Clone ou baixe o projeto**
   ```bash
   cd AdapterStrategyFactory
   ```

2. **Compile o projeto**
   ```bash
   javac -d bin src/**/*.java
   ```

3. **Execute o Main**
   ```bash
   java -cp bin Main
   ```

Ou simplesmente execute a classe `Main.java` pela sua IDE.

## 📝 Exemplos de Uso

### Exemplo 1: Publicação Simultânea

```java
ServicoPublicacao servico = new ServicoPublicacao();

// Adiciona plataformas
servico.adicionarPlataforma(TipoPlataforma.TWITTER);
servico.adicionarPlataforma(TipoPlataforma.INSTAGRAM);

// Define estratégia
servico.setEstrategia(new PublicacaoSimultanea());

// Cria conteúdo
Conteudo conteudo = new Conteudo(
    "Título",
    "Texto da publicação",
    Arrays.asList("url_imagem.jpg"),
    Arrays.asList("#hashtag1", "#hashtag2"),
    LocalDateTime.now()
);

// Publica
List<Publicacao> publicacoes = servico.publicar(conteudo);

// Obtém estatísticas
Map<String, Estatisticas> stats = servico.obterTodasEstatisticas(publicacoes);
```

### Exemplo 2: Configuração Dinâmica

```java
// Carrega plataformas de um arquivo de configuração
String[] plataformas = {"Twitter", "LinkedIn", "Instagram"};

for (String plataforma : plataformas) {
    servico.adicionarPlataforma(plataforma);
}
```

### Exemplo 3: Publicação com Tratamento de Erros

```java
servico.setEstrategia(new PublicacaoComFallback());
List<Publicacao> publicacoes = servico.publicar(conteudo);

// Verifica quais publicações falharam
for (Publicacao pub : publicacoes) {
    if (pub.getStatus() == StatusPublicacao.FALHOU) {
        System.out.println("Falha em " + pub.getPlataforma() + 
                          ": " + pub.getMensagemErro());
    }
}
```

## 🎨 Demonstrações Incluídas

O arquivo `Main.java` contém 4 demonstrações completas:

1. **Publicação Simultânea** - Publica em Twitter, Instagram e LinkedIn ao mesmo tempo
2. **Publicação Sequencial** - Publica em Twitter, TikTok e Instagram com intervalo de 1s
3. **Publicação com Fallback** - Publica em todas as plataformas com tratamento de erros
4. **Configuração Dinâmica** - Carrega plataformas dinamicamente por string

## 🔍 Características Principais

### ✅ Vantagens da Arquitetura

- **Extensibilidade:** Fácil adicionar novas plataformas
- **Manutenibilidade:** Código organizado e separado por responsabilidades
- **Flexibilidade:** Troca de estratégias em tempo de execução
- **Reutilização:** Interface unificada reutilizável
- **Testabilidade:** Componentes desacoplados facilitam testes

### 🎯 Princípios SOLID Aplicados

- **S**ingle Responsibility: Cada classe tem uma única responsabilidade
- **O**pen/Closed: Aberto para extensão, fechado para modificação
- **L**iskov Substitution: Adapters são substituíveis
- **I**nterface Segregation: Interfaces coesas e específicas
- **D**ependency Inversion: Depende de abstrações, não de implementações

## 📚 Tecnologias Utilizadas

- **Java 8+**
- **Collections Framework** (List, Map, HashMap)
- **Enums** para tipos de plataforma
- **LocalDateTime** para manipulação de datas

## 🔮 Possíveis Extensões

1. **Autenticação Real:** Implementar OAuth2 para APIs reais
2. **Persistência:** Salvar publicações em banco de dados
3. **Agendamento:** Sistema de agendamento de publicações
4. **Analytics:** Dashboard de analytics consolidado
5. **Webhooks:** Notificações de eventos das plataformas
6. **Cache:** Sistema de cache para estatísticas
7. **Retry:** Mecanismo de retry para falhas temporárias
8. **Logging:** Sistema de logs estruturado

## 👥 Autores

**Nome:** Yuji Chikara Kiyota

Projeto desenvolvido como exercício prático da disciplina de Design Patterns.

## 📄 Licença

Este projeto é de uso educacional.

---

**Tempo estimado de desenvolvimento:** 4-6 horas  
**Padrões aplicados:** Adapter, Strategy, Factory Method  
**Nível:** Intermediário/Avançado

