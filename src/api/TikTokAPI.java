package api;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

// API simulada do TikTok focada em vídeos
public class TikTokAPI {
    private Map<String, TikTokVideo> videos = new HashMap<>();
    private Random random = new Random();

    public TikTokUploadResult uploadVideo(TikTokVideoData videoData) {
        if (videoData.videoUrl == null || videoData.videoUrl.isEmpty()) {
            return new TikTokUploadResult(false, null, "URL do vídeo é obrigatória");
        }

        String videoId = "tt_" + System.currentTimeMillis();
        TikTokVideo video = new TikTokVideo(videoId, videoData);
        videos.put(videoId, video);

        System.out.println("[TikTok API] Vídeo publicado com sucesso: " + videoId);
        return new TikTokUploadResult(true, videoId, "Upload realizado com sucesso");
    }

    public TikTokStats getVideoStats(String videoId) {
        if (!videos.containsKey(videoId)) {
            throw new IllegalArgumentException("Vídeo não encontrado");
        }

        TikTokStats stats = new TikTokStats();
        stats.views = random.nextInt(10000);
        stats.likes = random.nextInt(5000);
        stats.comments = random.nextInt(500);
        stats.shares = random.nextInt(300);
        stats.favorites = random.nextInt(400);
        stats.watchTime = random.nextInt(50000);

        return stats;
    }

    // Classes internas
    public static class TikTokVideoData {
        public String videoUrl;
        public String description;
        public String[] hashtags;
        public String soundtrack;

        public TikTokVideoData(String videoUrl, String description, String[] hashtags) {
            this.videoUrl = videoUrl;
            this.description = description;
            this.hashtags = hashtags;
        }
    }

    public static class TikTokVideo {
        public String id;
        public TikTokVideoData data;
        public long uploadTime;

        public TikTokVideo(String id, TikTokVideoData data) {
            this.id = id;
            this.data = data;
            this.uploadTime = System.currentTimeMillis();
        }
    }

    public static class TikTokUploadResult {
        public boolean success;
        public String videoId;
        public String message;

        public TikTokUploadResult(boolean success, String videoId, String message) {
            this.success = success;
            this.videoId = videoId;
            this.message = message;
        }
    }

    public static class TikTokStats {
        public int views;
        public int likes;
        public int comments;
        public int shares;
        public int favorites;
        public int watchTime;
    }
}

