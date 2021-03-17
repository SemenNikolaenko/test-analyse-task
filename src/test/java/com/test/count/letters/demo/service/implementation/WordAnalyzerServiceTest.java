package com.test.count.letters.demo.service.implementation;

import com.test.count.letters.demo.CountLettersApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class WordAnalyzerServiceTest {

    private WordAnalyzerService service = new WordAnalyzerService();



    @Test
    public void shouldReturnCharactersWithTheirMaxValues() {
        Map<Character,Integer> testMap=new HashMap<>();
        testMap.put('w',3);
        testMap.put('e',1);
        testMap.put('a',2);
        testMap.put('z',3);
        int maxRepeats=3;
        Map<Character, Integer> readyMap = service.getMaxValues(testMap,maxRepeats);
        assertAll(
                ()->assertNotNull(readyMap),
                ()->assertEquals(2,readyMap.size()),
                ()->assertTrue(readyMap.containsKey('w')),
                ()->assertTrue(readyMap.containsKey('z'))
        );
    }

    @Test
    public void shouldReturnMapWithAllCharAndTheirRepeats(){
        String inputWord="hello";

        Map<Character, Integer> repeatsAllLetters = service.getRepeatsAllLetters(inputWord);

        assertAll(
                ()->assertNotNull(repeatsAllLetters),
                ()->assertEquals(4,repeatsAllLetters.size()),
                ()->assertEquals(2,repeatsAllLetters.get('l'))
        );
    }

    @Test
    public void shouldReturnCharWithMostRepeatsAndBiggestIndex(){
        String inputWord="tester";
        Map<Character,Integer> mapChars = new HashMap<>();
        mapChars.put('t',2);
        mapChars.put('e',2);
        mapChars.put('s',1);
        mapChars.put('r',1);

        Map.Entry<Character, Integer> entry = service.getLatestRepeat(mapChars,inputWord);

        assertAll(
                ()->assertNotNull(entry),
                ()->assertEquals('e',entry.getKey()),
                ()->assertEquals(2,entry.getValue())
        );



    }

}