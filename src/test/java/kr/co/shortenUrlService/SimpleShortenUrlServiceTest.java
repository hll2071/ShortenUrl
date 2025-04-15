package kr.co.shortenUrlService;

import kr.co.shortenUrlService.application.SimpleShortenUrlService;
import kr.co.shortenUrlService.presentation.ShortenUrlCreateRequestDto;
import kr.co.shortenUrlService.presentation.ShortenUrlCreateResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;


// 통합 테스트를 진행할 때는 스프링의 힘을 빌려야하기 때문에 아래 어노테이션을 넣어줘야 한다.
@SpringBootTest
public class SimpleShortenUrlServiceTest {

    // 스프링 컨테이너로부터 실제 서비스 객체를 불러와서 연결시키는 코드
    @Autowired
    private SimpleShortenUrlService simpleShortenUrlService;

    @Test
    @DisplayName("url을 단축한 후에 단축된 url 키로 조회하면 원래 url이 조회 돼야 함")
    void shortenUrlAddTest() {
        // given : ~~가 주어졌을 때
        String expectedOriginalUrl = "http://www.google.com";
        ShortenUrlCreateRequestDto shortenUrlCreateRequestDto = new ShortenUrlCreateRequestDto(expectedOriginalUrl);

        // when : 그 주어진걸로 ~~를 했을 때
        ShortenUrlCreateResponseDto shortenUrlCreateResponseDto = simpleShortenUrlService.generateShortenUrl(shortenUrlCreateRequestDto);
        String shortenedUrlKey = shortenUrlCreateResponseDto.getShortenedUrlKey();
        String originalUrl = simpleShortenUrlService.getOriginalUrlByShortenUrlKey(shortenedUrlKey);

        // then : 기대하는 결과가 잘 나왔나?
        assertThat(originalUrl).isEqualTo(expectedOriginalUrl);
        assertEquals(originalUrl, expectedOriginalUrl);
        assertTrue(originalUrl.equals(expectedOriginalUrl));
    }
}
