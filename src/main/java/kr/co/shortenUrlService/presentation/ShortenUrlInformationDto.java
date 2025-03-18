package kr.co.shortenUrlService.presentation;

public class ShortenUrlInformationDto {

    private String originalUrl;

    private String shortenedUrlKey;

    private Long redirectCount;

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortenedUrlKey() {
        return shortenedUrlKey;
    }

    public Long getRedirectCount() {
        return redirectCount;
    }
}
