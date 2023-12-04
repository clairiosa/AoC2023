package aoc.day03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aoc.Day;

public class Day03 implements Day {

    @Override
    public String part1(List<String> input) {
        int sum = 0;
        List<List<Character>> arr = parseInput(input);
        for (int y = 0; y < arr.size(); y++) {
            for (int x = 0; x < arr.get(0).size(); x++) {
                if (Character.isDigit(arr.get(y).get(x))) {
                    int startx = x;
                    int endx = x;

                    int n = Character.getNumericValue(arr.get(y).get(x));
                    while (Character.isDigit(arr.get(y).get(x)) && x+1 < arr.get(0).size()) {
                        x++;
                        if (Character.isDigit(arr.get(y).get(x))) {
                            n *= 10;
                            n += Character.getNumericValue(arr.get(y).get(x));
                            endx = x;
                        }
                    }
                    if (hasPart(arr, startx, endx, y, n)) {
                        sum += n;
                    }
                }
            }
        }
        return String.format("%d\n", sum);
    }

    @Override
    public String part2(List<String> input) {
        int sum = 0;

        Map<String, Gear> gearMap = new HashMap<>();


        List<List<Character>> arr = parseInput(input);
        for (int y = 0; y < arr.size(); y++) {
            for (int x = 0; x < arr.get(0).size(); x++) {
                if (Character.isDigit(arr.get(y).get(x))) {
                    int startx = x;
                    int endx = x;

                    int n = Character.getNumericValue(arr.get(y).get(x));
                    while (Character.isDigit(arr.get(y).get(x)) && x+1 < arr.get(0).size()) {
                        x++;
                        if (Character.isDigit(arr.get(y).get(x))) {
                            n *= 10;
                            n += Character.getNumericValue(arr.get(y).get(x));
                            endx = x;
                        }
                    }
                    hasGear(arr, startx, endx, y, n, gearMap);
                }
            }
        }

        for (Gear gear: gearMap.values()) {
            if (gear.matches.size() == 2) {
                sum += gear.matches.get(0) * gear.matches.get(1);
            }
        }
        return String.format("%d\n", sum);
    }

    List<List<Character>> parseInput(List<String> input) {
        List<List<Character>> output = new ArrayList<>();
        for (String line : input) {
            List<Character> l = new ArrayList<>();
            for (Character c : line.toCharArray()) {
                l.add(c);
            }
            output.add(l);
        }
        return output;
    }

    boolean hasPart(List<List<Character>> arr, int startx, int endx, int starty, int n) {

        int x;
        int y;

        boolean result = false;

        // Top
        if (starty > 0) {
            y = starty - 1;
            if (findSymbolLtR(arr, startx, endx, y)) result = true;
        }

        // Left
        if (startx > 0) {
            x = startx - 1;
            y = starty;
            if (!Character.isDigit(arr.get(y).get(x)) && arr.get(y).get(x) != '.') {
                result = true;
            }
        }


        // Right
        if (endx+1 < arr.get(0).size()) {
            x = endx + 1;
            y = starty;
            if (!Character.isDigit(arr.get(y).get(x)) && arr.get(y).get(x) != '.') {
                result = true;
            }
        }

        // Bottom
        if (starty+1 < arr.size()) {
            y = starty + 1;
            if (findSymbolLtR(arr, startx, endx, y))result = true;
        }

        return result;
    }

    private boolean findSymbolLtR(List<List<Character>> arr, int startx, int endx, int y) {
        int x;
        boolean result = false;

        if (startx > 0) {
            x = startx - 1;

            if (!Character.isDigit(arr.get(y).get(x)) && arr.get(y).get(x) != '.') {
                result = true;
            }
        }

        x = startx-1;
        while (x <= endx && x+1 < arr.get(0).size()) {
            x ++;

            if (!Character.isDigit(arr.get(y).get(x)) && arr.get(y).get(x) != '.') {
                result = true;
            }
        }
        return result;
    }

    void hasGear(List<List<Character>> arr, int startx, int endx, int starty, int n, Map<String, Gear> gearMap) {

        int x;
        int y;


        // Top
        if (starty > 0) {
            y = starty - 1;
            hasGearLR(arr, startx, endx, y, n, gearMap);
        }

        // Left
        if (startx > 0) {
            x = startx - 1;
            y = starty;
            if (arr.get(y).get(x) == '*') {
                Gear gear = gearMap.getOrDefault(getLocation(y, x), new Gear(y, x));
                gear.matches.add(n);
                gearMap.put(getLocation(y, x), gear);
            }
        }


        // Right
        if (endx+1 < arr.get(0).size()) {
            x = endx + 1;
            y = starty;
            if (arr.get(y).get(x) == '*') {
                Gear gear = gearMap.getOrDefault(getLocation(y, x), new Gear(y, x));
                gear.matches.add(n);
                gearMap.put(getLocation(y, x), gear);
            }
        }

        // Bottom
        if (starty+1 < arr.size()) {
            y = starty + 1;
            hasGearLR(arr, startx, endx, y, n, gearMap);
        }

    }

    private void hasGearLR(List<List<Character>> arr, int startx, int endx, int y, int n, Map<String, Gear> gearMap) {
        int x;

        if (startx > 0) {
            x = startx - 1;

            if (arr.get(y).get(x) == '*') {
                Gear gear = gearMap.getOrDefault(getLocation(y, x), new Gear(y, x));
                gear.matches.add(n);
                gearMap.put(getLocation(y, x), gear);
            }
        }

        x = startx-1;
        while (x <= endx && x+1 < arr.get(0).size()) {
            x ++;

            if (arr.get(y).get(x) == '*') {
                Gear gear = gearMap.getOrDefault(getLocation(y, x), new Gear(y, x));
                gear.matches.add(n);
                gearMap.put(getLocation(y, x), gear);
            }
        }
    }

    class Gear {
        int x;
        int y;

        List<Integer> matches;

        public Gear(int y, int x) {
            this.y = y;
            this.x = x;
            matches = new ArrayList<>();
        }
    }
    public String getLocation(int y, int x) {
        return String.format("y=%d,x=%d", y, x);
    }
}
