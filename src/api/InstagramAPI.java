package api;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

// API simulada do Instagram com sua própria estrutura
public class InstagramAPI {
    private Map<String, InstagramPost> posts = new HashMap<>();
    private Random random = new Random();

    public InstagramResponse publishPost(InstagramPostRequest request) {
        if (request.imageUrls == null || request.imageUrls.length == 0) {
            return new InstagramResponse(false, null, "Instagram requer pelo menos uma imagem");
        }

        String postId = "ig_" + System.currentTimeMillis();
        InstagramPost post = new InstagramPost(postId, request);
        posts.put(postId, post);

        System.out.println("[Instagram API] Post publicado com sucesso: " + postId);
        return new InstagramResponse(true, postId, "Sucesso");
    }

    public InstagramInsights getInsights(String postId) {
        if (!posts.containsKey(postId)) {
            throw new IllegalArgumentException("Post não encontrado");
        }

        InstagramInsights insights = new InstagramInsights();
        insights.likes = random.nextInt(2000);
        insights.comments = random.nextInt(300);
        insights.saves = random.nextInt(150);
        insights.reach = random.nextInt(8000);
        insights.engagement_rate = random.nextDouble() * 10;

        return insights;
    }

    // Classes internas
    public static class InstagramPostRequest {
        public String caption;
        public String[] imageUrls;
        public String[] tags;

        public InstagramPostRequest(String caption, String[] imageUrls, String[] tags) {
            this.caption = caption;
            this.imageUrls = imageUrls;
            this.tags = tags;
        }
    }

    public static class InstagramPost {
        public String id;
        public InstagramPostRequest data;
        public long createdAt;

        public InstagramPost(String id, InstagramPostRequest data) {
            this.id = id;
            this.data = data;
            this.createdAt = System.currentTimeMillis();
        }
    }

    public static class InstagramResponse {
        public boolean success;
        public String postId;
        public String message;

        public InstagramResponse(boolean success, String postId, String message) {
            this.success = success;
            this.postId = postId;
            this.message = message;
        }
    }

    public static class InstagramInsights {
        public int likes;
        public int comments;
        public int saves;
        public int reach;
        public double engagement_rate;
    }
}

