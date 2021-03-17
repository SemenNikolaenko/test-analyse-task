package com.test.count.letters.demo.service.implementation;

import com.test.count.letters.demo.model.dto.LetterRepeatDto;
import com.test.count.letters.demo.service.AnalyzerService;
import com.test.count.letters.demo.utils.DtoCreator;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * класс анализатора который подсчитывает количество повторяемых букв в слове
 *
 * @version 1
 */
@Service
public class WordAnalyzerService implements AnalyzerService {
    /**
     * метод производит анализ путем вызова некоторых внутренних методов
     *
     * @param word слово поступающее на вход анализатора
     * @return объект {@link LetterRepeatDto} который содержит всю необходимую информацию
     */
    @Override
    public LetterRepeatDto doAnalyse(String word) {
        Map<Character, Integer> allRepeats = getRepeatsAllLetters(word);
        Map.Entry<Character, Integer> letterWithMostRepeats = getLatestRepeat(allRepeats, word);
        //создается DTO для передачи в контроллер
        LetterRepeatDto analyseResult = DtoCreator.createLetterRepeater(letterWithMostRepeats.getKey(), letterWithMostRepeats.getValue());
        return analyseResult;
    }

    /**
     * метод считает повторение каждого символа в слове, и затем создает Map которая содержит эту информацию
     *
     * @param word слово поступающее на вход анализатора
     * @return Map с символом и количеством его повторов в слове
     */
    public Map<Character, Integer> getRepeatsAllLetters(String word) {
        Map<Character, Integer> lettersAndTheirRepeats = new HashMap<>();
        //получаем набор символов из слова
        char[] letters = word.toLowerCase().toCharArray();
        //производим подсчет повторений для каждого символа
        for (int i = 0; i < letters.length; i++) {
            if (lettersAndTheirRepeats.containsKey(letters[i])) {
                //если данный символ уже содержиться в Map то просто увеличиваем его значения на 1
                lettersAndTheirRepeats.put(letters[i], lettersAndTheirRepeats.get(letters[i]) + 1);
            } else {
                //если символа в Map не существует, заносим его значение
                lettersAndTheirRepeats.put(letters[i], 1);
            }
        }
        return lettersAndTheirRepeats;
    }

    /**
     * если в слове есть несколько символов с одинаковым количеством повторний,
     * метод вычисляет последний индекс каждого символа и возвращает символ
     * который имеет наибольший индекс, т.е. тот который упоминается последним
     *
     * @param lettersAndTheirRepeats Map которая содержит символы и количество их повторений
     * @param word                   входное слово
     * @return набор символа с наибольшим количеством повторений и встречающийся последним и количество его повторений
     */
    public Map.Entry<Character, Integer> getLatestRepeat(Map<Character, Integer> lettersAndTheirRepeats, String word) {
        //получаем наибольшее число повторений символа
        int maxValuesRepeat = lettersAndTheirRepeats.entrySet().stream()
                .mapToInt(c -> c.getValue())
                .max().getAsInt();
        //получаем набор со всеми символами, которые встречаются одинаково максимальное число раз
        Map<Character, Integer> maxValues = getMaxValues(lettersAndTheirRepeats, maxValuesRepeat);

        //для каждого символа с максимальным числом повторений вычисляем индекс в слове
        for (Map.Entry<Character, Integer> entry : maxValues.entrySet()) {
            entry.setValue(word.lastIndexOf(entry.getKey()));
        }

        //находим символ с наибольшим индексом
        Map.Entry<Character, Integer> finalLetter = maxValues.entrySet().stream()
                //сортируем Map по value в убывающем порядке
                .sorted(Map.Entry.<Character, Integer>comparingByValue().reversed())
                .findFirst()
                .get();

        //возвращаем символу с самым большим индексом его количество повторов
        finalLetter.setValue(maxValuesRepeat);

        return finalLetter;

    }

    /**
     * если слово содержит несколько символов, которые встречаются одинаково большое количество раз,
     * метод возвращает их как набор пар символ/количество повторов
     *
     * @param lettersAndTheirRepeats символы и количество их повторений
     * @param maxRepeat              наибольшее количество повторений
     * @return Map с повторяющимися символами и количеством повторов
     */
    public Map<Character, Integer> getMaxValues(Map<Character, Integer> lettersAndTheirRepeats, int maxRepeat) {
        return lettersAndTheirRepeats.entrySet().stream()
                //сортируем Map по value в убывающем порядке
                .sorted(Map.Entry.<Character, Integer>comparingByValue().reversed())
                //забираем все символы у которых число потворений равно максимальному
                .takeWhile(c -> c.getValue() == maxRepeat)
                .collect(Collectors.toMap(s -> s.getKey(), s -> s.getValue()));
    }
}
