package api;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

// API simulada do LinkedIn com estrutura profissional
public class LinkedInAPI {
    private Map<String, LinkedInArticle> articles = new HashMap<>();
    private Random random = new Random();

    public String createArticle(LinkedInArticleData articleData) {
        if (articleData.title == null || articleData.title.isEmpty()) {
            throw new IllegalArgumentException("Título é obrigatório no LinkedIn");
        }

        String articleId = "li_" + System.currentTimeMillis();
        LinkedInArticle article = new LinkedInArticle(articleId, articleData);
        articles.put(articleId, article);

        System.out.println("[LinkedIn API] Artigo publicado com sucesso: " + articleId);
        return articleId;
    }

    public LinkedInAnalytics getAnalytics(String articleId) {
        if (!articles.containsKey(articleId)) {
            throw new IllegalArgumentException("Artigo não encontrado");
        }

        LinkedInAnalytics analytics = new LinkedInAnalytics();
        analytics.reactions = random.nextInt(500);
        analytics.comments = random.nextInt(100);
        analytics.shares = random.nextInt(50);
        analytics.views = random.nextInt(3000);
        analytics.clickThroughRate = random.nextDouble() * 5;

        return analytics;
    }

    // Classes internas
    public static class LinkedInArticleData {
        public String title;
        public String content;
        public String[] mediaUrls;
        public String[] topics;

        public LinkedInArticleData(String title, String content, String[] mediaUrls, String[] topics) {
            this.title = title;
            this.content = content;
            this.mediaUrls = mediaUrls;
            this.topics = topics;
        }
    }

    public static class LinkedInArticle {
        public String id;
        public LinkedInArticleData data;
        public long publishedDate;

        public LinkedInArticle(String id, LinkedInArticleData data) {
            this.id = id;
            this.data = data;
            this.publishedDate = System.currentTimeMillis();
        }
    }

    public static class LinkedInAnalytics {
        public int reactions;
        public int comments;
        public int shares;
        public int views;
        public double clickThroughRate;
    }
}

