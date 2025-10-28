package api;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

// API simulada do Twitter com sua própria estrutura de dados
public class TwitterAPI {
    private Map<String, TweetData> tweets = new HashMap<>();
    private Random random = new Random();

    public String postTweet(String text, String[] media, String[] hashtags) {
        if (text.length() > 280) {
            throw new IllegalArgumentException("Tweet excede 280 caracteres");
        }

        String tweetId = "tw_" + System.currentTimeMillis();
        TweetData tweet = new TweetData(tweetId, text, media, hashtags);
        tweets.put(tweetId, tweet);

        System.out.println("[Twitter API] Tweet publicado com sucesso: " + tweetId);
        return tweetId;
    }

    public TweetMetrics getMetrics(String tweetId) {
        if (!tweets.containsKey(tweetId)) {
            throw new IllegalArgumentException("Tweet não encontrado");
        }

        TweetMetrics metrics = new TweetMetrics();
        metrics.likes = random.nextInt(1000);
        metrics.retweets = random.nextInt(500);
        metrics.replies = random.nextInt(200);
        metrics.impressions = random.nextInt(5000);

        return metrics;
    }

    public boolean deleteTweet(String tweetId) {
        return tweets.remove(tweetId) != null;
    }

    // Classe interna para dados do Tweet
    public static class TweetData {
        public String id;
        public String text;
        public String[] media;
        public String[] hashtags;
        public long timestamp;

        public TweetData(String id, String text, String[] media, String[] hashtags) {
            this.id = id;
            this.text = text;
            this.media = media;
            this.hashtags = hashtags;
            this.timestamp = System.currentTimeMillis();
        }
    }

    // Classe interna para métricas
    public static class TweetMetrics {
        public int likes;
        public int retweets;
        public int replies;
        public int impressions;
    }
}

