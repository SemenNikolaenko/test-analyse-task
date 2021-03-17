package com.test.count.letters.demo.service;

import com.test.count.letters.demo.model.dto.LetterRepeatDto;

/**
 * интерфейс определяюий носновной метод анализатора
 */
public interface AnalyzerService {
    public LetterRepeatDto doAnalyse(String word);
}
