package aoc.day01;

import aoc.Day;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day01 implements Day {

    @Override
    public String part1(List<String> input) {
        int total = 0;
        for (String line: input) {
            int result = 0;
            for (int i = 0; i < line.length(); i++) {
                if (Character.isDigit(line.charAt(i))) {
                    result = 10*Integer.parseInt(String.valueOf(line.charAt(i)));
                    break;
                }
            }
            for (int i = line.length()-1; i >= 0 ; i--) {
                if (Character.isDigit(line.charAt(i))) {
                    result += Integer.parseInt(String.valueOf(line.charAt(i)));
                    break;
                }
            }
            total += result;
        }
        return String.valueOf(total);
    }

    @Override
    public String part2(List<String> input) {
        int total = 0;
        for (String line: input) {
            int result = 0;

            result = 10*findFirst(line);
            result += findLast(line);

            System.out.printf("%d %s\n", result, line);

            total += result;
        }
        return String.valueOf(total);
    }

    static final Map<String, Integer> options = new HashMap<>(){
        {
            put("0", 0);
            put("1", 1);
            put("2", 2);
            put("3", 3);
            put("4", 4);
            put("5", 5);
            put("6", 6);
            put("7", 7);
            put("8", 8);
            put("9", 9);
            put("one", 1);
            put("two", 2);
            put("three", 3);
            put("four", 4);
            put("five", 5);
            put("six", 6);
            put("seven", 7);
            put("eight", 8);
            put("nine", 9);
        }
    };

    public static int findFirst(String input) {
        int index = 9999;
        int value = 0;

        for (var option: options.entrySet()) {
            if (input.contains(option.getKey()) && input.indexOf(option.getKey()) < index) {
                index = input.indexOf(option.getKey());
                value = option.getValue();
            }
        }
        return value;
    }


    public static int findLast(String input) {
        int index = -1;
        int value = 0;

        for (var option: options.entrySet()) {
            if (input.contains(option.getKey()) && input.lastIndexOf(option.getKey()) > index) {
                index = input.lastIndexOf(option.getKey());
                value = option.getValue();
            }
        }
        return value;
    }

}
