package kr.co.shortenUrlService.presentation;

import kr.co.shortenUrlService.domain.LackOfShortenUrlKeyException;
import kr.co.shortenUrlService.domain.NotFoundShortenUrlException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundShortenUrlException.class)
    public ResponseEntity<?> handleNotFoundShortenUrlException(NotFoundShortenUrlException e) {
        return new ResponseEntity<>("존재하지 않는 단축 URL입니다.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LackOfShortenUrlKeyException.class)
    public ResponseEntity<?> handleLackOfShortenUrlKeyException(LackOfShortenUrlKeyException e) {
        return new ResponseEntity<>("단축 URL 용량이 부족합니다.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
