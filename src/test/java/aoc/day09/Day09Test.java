package aoc.day09;

import aoc.day09.Day09;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Day09Test {

    private static List<String> loadInput(int day){
        String paddedDay = String.valueOf(day);
        if(day < 10) {
            paddedDay = "0" + day;
        }
        String fileName = "day" + paddedDay + ".txt";

        try(BufferedReader r = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(fileName)))){
            return r.lines().collect(toList());
        } catch(IOException e){
            throw new UncheckedIOException(e);
        }
    }



    @Test
    public void testPart1(){
        // Given
        List<String> input = loadInput(9);

        // When
        String result = new Day09().part1(input);

        System.out.println(result);
        // Then
    }

    @Test
    public void testPart2(){
        // Given
        List<String> input = loadInput(9);

        // When
        String result = new Day09().part2(input);

        System.out.println(result);
    }
}
