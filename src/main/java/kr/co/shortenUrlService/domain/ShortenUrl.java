package kr.co.shortenUrlService.domain;

import java.util.Random;

public class ShortenUrl {

    private String originalUrl;

    private String shortenedUrlKey;

    private Long redirectCount;

    public ShortenUrl(String originalUrl, String shortenUrlKey, Long redirectCount) {
        this.originalUrl = originalUrl;
        this.shortenedUrlKey = shortenUrlKey;
        this.redirectCount = redirectCount;
    }

    public ShortenUrl(String originalUrl, String shortenUrlKey) {
        this.originalUrl = originalUrl;
        this.shortenedUrlKey = shortenUrlKey;
        this.redirectCount = 0L;
    }



    public static String generateSnortenedUrlKey() {
        String base56Characters = "23456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz";
        Random random = new Random();
        StringBuilder shortenedUrlKey = new StringBuilder();
        for(int i=0; i<8; i++) {
            char base56Character = base56Characters.charAt(random.nextInt(base56Characters.length()));
            shortenedUrlKey.append(base56Character);
        }
        return shortenedUrlKey.toString();
    }

    public String getShortenUrlKey() {
        return shortenedUrlKey;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public Long getRedirectCount() {
        return redirectCount;
    }

    public void setRedirectCount(long l) {
        this.redirectCount = l;
    }

    public void increaseRedirectCount() {
        this.redirectCount++;
    }
}
