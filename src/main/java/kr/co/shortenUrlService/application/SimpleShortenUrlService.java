package kr.co.shortenUrlService.application;

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
        String shortenUrlKey = ShortenUrl.generateSnortenedUrlKey();
        //원래 URL과 단축 URL 키를 통해 ShortenUrl 도메인 객체 생
        ShortenUrl shortenUrl = new ShortenUrl(originalUrl, shortenUrlKey);

        //생성된 ShortenUrl을 레포지토리를 통해 저장
        shortenUrlRepository.saveShortenUrl(shortenUrl);
        //ShortenUrl을 ShortenUrlCreateResponseDto로 변환하여서 최종 반환
        return null;
    }

//    public ShortenUrlInformationDto getShortenUrlInformationByShortenUrlKey(String shortenUrlKey) {
//        ShortenUrlInformationDto shortenUrlInformationDto = shortenUrlRepository.findShortenUrlByShortenUrlKey(shortenUrlKey);
//    }
}
