package kr.co.shortenUrlService.application;

import kr.co.shortenUrlService.domain.LackOfShortenUrlKeyException;
import kr.co.shortenUrlService.domain.NotFoundShortenUrlException;
import kr.co.shortenUrlService.domain.ShortenUrl;
import kr.co.shortenUrlService.domain.ShortenUrlRepository;
import kr.co.shortenUrlService.presentation.ShortenUrlCreateRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

// 단위 테스트이다. (목 객체를 만들어야함.)
@ExtendWith(MockitoExtension.class)
class SimpleShortenUrlServiceUnitTest {

    @Mock
    private ShortenUrlRepository shortenUrlRepository;

    @InjectMocks
    private SimpleShortenUrlService simpleShortenUrlService;

    @Test
    @DisplayName("단축 URL이 계속 중복되면 LackOfShortenUrlKeyException 예외가 발생해야 한다.")
    void throwLackOfShortenUrlKeyExceptionTest() {
        //given
        ShortenUrlCreateRequestDto shortenUrlCreateRequestDto = new ShortenUrlCreateRequestDto(null);

        //when : 여기에 목객체의 수행흐름을 적어줘야 한다.
        when(shortenUrlRepository.findShortenUrlByShortenUrlKey(any())).thenReturn(new ShortenUrl(null, null));
        //then
        assertThrows(LackOfShortenUrlKeyException.class,
                () -> simpleShortenUrlService.generateShortenUrl(shortenUrlCreateRequestDto));
    }

    @Test
    @DisplayName("존재하지 않는 URL 키를 조회하면 NotFoundShortenUrlException이 터져야 함")
    void throwNotFoundShortenUrlExceptionTest() {
        // given
        String invalidShortenUrlKey = "없는 값";

        // when
        when(shortenUrlRepository.findShortenUrlByShortenUrlKey(invalidShortenUrlKey)).thenReturn(null);

        // then
        assertThrows(NotFoundShortenUrlException.class,
                () -> simpleShortenUrlService.getShortenUrlInformationByShortenUrlKey(invalidShortenUrlKey));
    }
}