package aoc.day05;

import aoc.Day;
import lombok.Builder;
import lombok.Getter;

import java.util.*;

public class Day05 implements Day {

    private List<Long> seeds;
    private Map<Long, Range> seedToSoil = new HashMap<>();
    private Map<Long, Range> soilToFertilizer = new HashMap<>();
    private Map<Long, Range> fertilizerToWater = new HashMap<>();
    private Map<Long, Range> waterToLight = new HashMap<>();
    private Map<Long, Range> lightToTemperature = new HashMap<>();
    private Map<Long, Range> temperatureToHumidity = new HashMap<>();
    private Map<Long, Range> humidityToLocation = new HashMap<>();

    @Override
    public String part1(List<String> input) {
        parseInput(input);
        Long lowest = 999999999L;
        //return seeds.toString() + "\n" + soilToFertilizer.toString() + "\n" + fertilizerToWater.toString() + "\n" + waterToLight.toString()  + "\n" + lightToTemperature.toString()  + "\n" + temperatureToHumidity  + "\n" + humidityToLocation.toString() .toString()  ;
        for (Long seed: seeds) {
            Long follow;
            follow = getDestination(seed, seedToSoil);
            follow = getDestination(follow, soilToFertilizer);
            follow = getDestination(follow, fertilizerToWater);
            follow = getDestination(follow, waterToLight);
            follow = getDestination(follow, lightToTemperature);
            follow = getDestination(follow, temperatureToHumidity);
            follow = getDestination(follow, humidityToLocation);
            if (follow < lowest) {
                lowest = follow;
            }
        }
        return "" + lowest;
    }

    @Override
    public String part2(List<String> input) {
        parseInput(input);
        Long lowest = 999999999L;

        for (int i = 0; i < seeds.size(); i+=2) {
            for (Long follow = seeds.get(i); follow < (seeds.get(i) + seeds.get(i+1)); follow++) {
                Long follow2 = getDestination(follow, seedToSoil);
                follow2 = getDestination(follow2, soilToFertilizer);
                follow2 = getDestination(follow2, fertilizerToWater);
                follow2 = getDestination(follow2, waterToLight);
                follow2 = getDestination(follow2, lightToTemperature);
                follow2 = getDestination(follow2, temperatureToHumidity);
                follow2 = getDestination(follow2, humidityToLocation);
                if (follow2 < lowest) {
                    lowest = follow2;
                }
            }
        }
        return "" + lowest;
    }

    private void parseInput(List<String> input) {
        seeds = new ArrayList<>();
        seeds.addAll(Arrays.stream(input.get(0).split("seeds: ")[1].split(" ")).map(Long::valueOf).toList());

        for (int line = 1; line < input.size(); line++) {
            if (input.get(line).equals("seed-to-soil map:")) {
                line++;
                while (!input.get(line).equals("")) {
                    Range range = new Range(input.get(line));
                    seedToSoil.put(range.sourceStart, range);
                    line++;
               }
            }
            if (input.get(line).equals("soil-to-fertilizer map:")) {
                line++;
                while (!input.get(line).equals("")) {
                    Range range = new Range(input.get(line));
                    soilToFertilizer.put(range.sourceStart, range);
                    line++;
                }
            }
            if (input.get(line).equals("fertilizer-to-water map:")) {
                line++;
                while (!input.get(line).equals("")) {
                    Range range = new Range(input.get(line));
                    fertilizerToWater.put(range.sourceStart, range);
                    line++;
                }
            }
            if (input.get(line).equals("water-to-light map:")) {
                line++;
                while (!input.get(line).equals("")) {
                    Range range = new Range(input.get(line));
                    waterToLight.put(range.sourceStart, range);
                    line++;
                }
            }
            if (input.get(line).equals("light-to-temperature map:")) {
                line++;
                while (!input.get(line).equals("")) {
                    Range range = new Range(input.get(line));
                    lightToTemperature.put(range.sourceStart, range);
                    line++;
                }
            }
            if (input.get(line).equals("temperature-to-humidity map:")) {
                line++;
                while (!input.get(line).equals("")) {
                    Range range = new Range(input.get(line));
                    temperatureToHumidity.put(range.sourceStart, range);
                    line++;
                }
            }
            if (input.get(line).equals("humidity-to-location map:")) {
                line++;
                while (line < input.size() && !input.get(line).equals("")) {
                    Range range = new Range(input.get(line));
                    humidityToLocation.put(range.sourceStart, range);
                    line++;
                }
            }
        }
    }

    private Long getDestination(Long seed, Map<Long, Range> rangeMap) {
        List<Long> sortedKeys = rangeMap.keySet().stream().sorted().toList();
        for (int i = 1; i < sortedKeys.size(); i++) {
            if (sortedKeys.get(i) > seed && sortedKeys.get(i-1) < seed) {
                Range range = rangeMap.get(sortedKeys.get(i-1));
                if (range.isInRange(seed)) {
                    return range.getDestination(seed);
                } else {
                    return seed;
                }
            }
        }
        return seed;
    }


    @Builder
    class Range {
        @Getter
        Long destinationStart;

        @Getter
        Long sourceStart;

        @Getter
        Long rangeLength;

        Range(String line) {
            destinationStart = Long.valueOf(line.split(" ")[0]);
            sourceStart = Long.valueOf(line.split(" ")[1]);
            rangeLength = Long.valueOf(line.split(" ")[2]);
        }

        public boolean isInRange(Long given) {
            return given >= sourceStart && given < sourceStart + rangeLength;
        }

        public Long getDestination(Long given){
            return given - sourceStart + destinationStart;
        }

        @Override
        public String toString() {
            return String.format("%s %s %s", destinationStart, sourceStart, rangeLength);
        }
    }
}


