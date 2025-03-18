package kr.co.shortenUrlService.presentation;

import jakarta.validation.Valid;
import kr.co.shortenUrlService.application.SimpleShortenUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShortenUrlRestController {

    private final SimpleShortenUrlService simpleShortenUrlService;

    @Autowired
    public ShortenUrlRestController(SimpleShortenUrlService shortenUrlService) {
        this.simpleShortenUrlService = shortenUrlService;
    }

    @PostMapping( "/shortenUrl")
    public ResponseEntity<ShortenUrlCreateResponseDto> createShortenUrl(@Valid @RequestBody ShortenUrlCreateRequestDto shortenUrlCreateRequestDto) {
        ShortenUrlCreateResponseDto shortenUrlCreateResponseDto = simpleShortenUrlService.generateShortenUrl(shortenUrlCreateRequestDto);
        return ResponseEntity.ok(shortenUrlCreateResponseDto);
    }

    @GetMapping( "/{shortenUrlKey}")
    public ResponseEntity<ShortenUrlInformationDto> redirectToShortenUrl() {
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/shortenUrl/{shortenUrlKey}")
    public ResponseEntity<?> getShortenUrlInformation(@PathVariable String shortenUrlKey) {
        ShortenUrlInformationDto shortenUrlInformationDto = simpleShortenUrlService.getShortenUrlInformationByShortenUrlKey(shortenUrlKey);
        return ResponseEntity.ok().body(null);
    }



}
