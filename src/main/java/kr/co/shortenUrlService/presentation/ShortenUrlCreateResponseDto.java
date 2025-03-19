package kr.co.shortenUrlService.presentation;

import kr.co.shortenUrlService.domain.ShortenUrl;

public class ShortenUrlCreateResponseDto {

    private String originalUrl;

    private String shortenedUrlKey;

    public ShortenUrlCreateResponseDto(ShortenUrl shortenUrl) {
        this.originalUrl = shortenUrl.getOriginalUrl();
        this.shortenedUrlKey = shortenUrl.getShortenUrlKey();
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortenedUrlKey() {
        return shortenedUrlKey;
    }
}
