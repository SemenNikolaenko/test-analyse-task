package com.test.count.letters.demo.utils;

import com.test.count.letters.demo.model.dto.LetterRepeatDto;
import org.springframework.stereotype.Component;

/**
 * Класс для создания DTO
 */
@Component
public class DtoCreator {
    public static LetterRepeatDto createLetterRepeater(char letter,int repeats){
        return new LetterRepeatDto(letter,repeats);
    }
}
