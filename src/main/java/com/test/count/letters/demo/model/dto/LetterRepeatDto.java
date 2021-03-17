package com.test.count.letters.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * простой класс DTO который нужен для передачи символа и количества его повторений
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LetterRepeatDto {
    private char letter;
    @JsonProperty("count")
    private int repeatsCount;

}
