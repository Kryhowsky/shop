package com.kryhowsky.shop.controller;

import com.kryhowsky.shop.flyweight.generic.GenericFactory;
import com.kryhowsky.shop.flyweight.generic.strategy.file.FileGeneratorStrategy;
import com.kryhowsky.shop.flyweight.model.FileType;
import com.kryhowsky.shop.flyweight.standard.GeneratorFactory;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/files")
public class FileController {

    private final GeneratorFactory generatorFactory;
    private final GenericFactory<FileType, FileGeneratorStrategy> genericFactory;

    @GetMapping
    @Operation(description = "Tests Flyweigth pattern in non generic version.")
    public void testNonGenericFlyweight(@RequestParam FileType fileType) {
        generatorFactory.getStrategyByType(fileType).generateFile();
    }

    @GetMapping("/generic")
    @Operation(description = "Tests Flyweight pattern in generic version.")
    public ResponseEntity<byte[]> testGenericFlyweight(@RequestParam FileType fileType) { // use ResponseEntity only when custom headers needed
        byte[] file = genericFactory.getStrategyByType(fileType).generateFile();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        httpHeaders.set(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length));
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=report." + fileType.name().toLowerCase());
        return ResponseEntity.ok().headers(httpHeaders).body(file);
    }

}
