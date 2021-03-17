package com.test.count.letters.demo.controller;

import com.test.count.letters.demo.errors.EmptyWordException;
import com.test.count.letters.demo.model.dto.LetterRepeatDto;
import com.test.count.letters.demo.service.AnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * класс контроллера который принимает по http параметры
 * производит анализ и отвечает клиенту специальным сообщением
 * при помощи {@link AnalyzerService}
 */
@RestController
@RequestMapping("/wordanalyzer")
public class WordAnalyzerController {
    //интерфейс анализатора
    private final AnalyzerService analyzerService;

    @Autowired
    public WordAnalyzerController(AnalyzerService analyzerService) {
        this.analyzerService = analyzerService;
    }

 //данный метод посредством Get запроса принимает параметр-слово для дальнейшего анализа
    @GetMapping("/analyze")
    public ResponseEntity<LetterRepeatDto> runAnalyse(@RequestParam String word) {
        LetterRepeatDto letterRepeatDto;
        if (word.isBlank()) throw new EmptyWordException("word mustn't be empty");
        letterRepeatDto=analyzerService.doAnalyse(word);
        return ResponseEntity.ok().body(letterRepeatDto);
    }
}
