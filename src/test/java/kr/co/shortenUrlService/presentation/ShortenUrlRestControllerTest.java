package kr.co.shortenUrlService.presentation;

import kr.co.shortenUrlService.application.SimpleShortenUrlService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

//스프링을 불러오면 통합테스트, 아니면 단위 테스트
//이거는 통합 테스트 중에서도 웹 통신을 테스트 해봐야하는 Controller 테스트이다.
//이거는 내장 톰캣을 사용해야 하기 때문에 스프링을 어쩔 수 없이 띄워야 한다.
@WebMvcTest(ShortenUrlRestController.class)

//이렇게 등장하는 Autowired에서 진짜를 주입시킬지 가짜를 주입시킬지 헷갈린다면 MockServiceConfig에 기반해서 주입해주도록 함
@Import(ShortenUrlRestControllerTest.MockServiceConfig.class)
class ShortenUrlRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SimpleShortenUrlService simpleShortenUrlService;

    @Test
    @DisplayName("원래의 URL로 리다이렉트 되어야 한다.")
    void redirectTest() throws Exception{
        //given
        String expectedOriginalUrl = "http://www.google.com";

        //when
        when(simpleShortenUrlService.getOriginalUrlByShortenUrlKey(any()))
                .thenReturn(expectedOriginalUrl);
        //then
        mockMvc.perform(get("/any-key"))
                .andExpect(status().isMovedPermanently())
                .andExpect(header().string("Location", expectedOriginalUrl));
    }

    //스프링을 통해서 위에서 Autowired()를 실제 객체로 시켰었는데
    //그 불러오느너를 슾 그링 컨테이너에 가짜 객체로
    static class MockServiceConfig {
        @Bean
        public SimpleShortenUrlService simpleShortenUrlService() {
            return mock(SimpleShortenUrlService.class);
        }
    }
}