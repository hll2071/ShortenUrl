package kr.co.shortenUrlService.application;

import kr.co.shortenUrlService.domain.LackOfShortenUrlKeyException;
import kr.co.shortenUrlService.domain.NotFoundShortenUrlException;
import kr.co.shortenUrlService.domain.ShortenUrl;
import kr.co.shortenUrlService.domain.ShortenUrlRepository;
import kr.co.shortenUrlService.presentation.ShortenUrlCreateRequestDto;
import kr.co.shortenUrlService.presentation.ShortenUrlCreateResponseDto;
import kr.co.shortenUrlService.presentation.ShortenUrlInformationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleShortenUrlService {

    private final ShortenUrlRepository shortenUrlRepository;

    @Autowired
    public SimpleShortenUrlService(ShortenUrlRepository shortenUrlRepository) {
        this.shortenUrlRepository = shortenUrlRepository;
    }

    public ShortenUrlCreateResponseDto generateShortenUrl(ShortenUrlCreateRequestDto shortenUrlCreateRequestDto) {
        //해야할 일 4가지

        //원래 URL과 단축 URL 생성
        String originalUrl = shortenUrlCreateRequestDto.getOriginalUrl();
        String shortenUrlKey = getShortenUrl();
        //원래 URL과 단축 URL 키를 통해 ShortenUrl 도메인 객체 생성
        ShortenUrl shortenUrl = new ShortenUrl(originalUrl, shortenUrlKey);

        //생성된 ShortenUrl을 레포지토리를 통해 저장
        shortenUrlRepository.saveShortenUrl(shortenUrl);
        //ShortenUrl을 ShortenUrlCreateResponseDto로 변환하여서 최종 반환
        ShortenUrlCreateResponseDto shortenUrlCreateResponseDto = new ShortenUrlCreateResponseDto(shortenUrl);
        return shortenUrlCreateResponseDto;
    }

    public ShortenUrlInformationDto getShortenUrlInformationByShortenUrlKey(String shortenUrlKey) {
        ShortenUrl shortenUrl = shortenUrlRepository.findShortenUrlByShortenUrlKey(shortenUrlKey);

        if(shortenUrl == null) throw new NotFoundShortenUrlException();

        ShortenUrlInformationDto shortenUrlInformationDto = new ShortenUrlInformationDto(shortenUrl);
        return shortenUrlInformationDto;
    }

    public String getOriginalUrlByShortenUrlKey(String shortenUrlKey) {
        ShortenUrl shortenUrl = shortenUrlRepository.findShortenUrlByShortenUrlKey(shortenUrlKey);

        shortenUrl.increaseRedirectCount();
        shortenUrlRepository.saveShortenUrl(shortenUrl);
        return shortenUrl.getOriginalUrl();
    }
    private String getShortenUrl() {
        String shortenUrlKey = ShortenUrl.generateSnortenedUrlKey();
        for(int i=0;i<5;i++) {
            if(shortenUrlRepository.findShortenUrlByShortenUrlKey(shortenUrlKey) != null) {
                if(i == 4) throw new LackOfShortenUrlKeyException();
                else {
                    shortenUrlKey = ShortenUrl.generateSnortenedUrlKey();
                }
            } else {
                break;
            }
        }
        return shortenUrlKey;
    }
}
