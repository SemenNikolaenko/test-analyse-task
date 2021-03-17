package com.test.count.letters.demo.controller;

import com.test.count.letters.demo.model.dto.LetterRepeatDto;
import com.test.count.letters.demo.service.AnalyzerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(WordAnalyzerController.class)
class WordAnalyzerControllerTest {
    @MockBean
    private AnalyzerService analyzerService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldBeNotNull() {
        assertAll(
                () -> assertNotNull(mockMvc),
                () -> assertNotNull(analyzerService)
        );
    }

    @Test
    public void shouldThrowException() throws Exception {
        String emptyWord = "";
        String errorMessage = "word mustn't be empty";

        mockMvc.perform(get("/wordanalyzer/analyze")
                .queryParam("word", emptyWord))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(errorMessage));
    }

    @Test
    public void shouldRunAnalyseWord() throws Exception {
        String correctWord = "test";
        LetterRepeatDto dto = new LetterRepeatDto('t', 2);
        mockMvc.perform(get("/wordanalyzer/analyze")
                .queryParam("word", correctWord))
                .andExpect(status().isOk());


    }

}