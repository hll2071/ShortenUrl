package kr.co.shortenUrlService.presentation;

import kr.co.shortenUrlService.domain.ShortenUrl;

public class ShortenUrlInformationDto {

    private String originalUrl;

    private String shortenedUrlKey;

    private Long redirectCount;

    public ShortenUrlInformationDto(ShortenUrl shortenUrl) {
        this.originalUrl = shortenUrl.getOriginalUrl();
        this.shortenedUrlKey = shortenUrl.getShortenUrlKey();
        this.redirectCount = shortenUrl.getRedirectCount();
    }

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
