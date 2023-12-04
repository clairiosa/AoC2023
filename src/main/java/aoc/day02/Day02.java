package aoc.day02;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aoc.Day;

public class Day02 implements Day {

    @Override
    public String part1(List<String> input) {
        int total = 0;

        for (String line: input) {
            String pulls = line.split(": ")[1];
            int game = Integer.valueOf(line.split(": ")[0].substring(5));

            boolean valid = true;

            for (String pull: pulls.split("; ")) {
                for (String cubes: pull.split(", ")) {
                    int value = Integer.valueOf(cubes.split(" ")[0]);
                    String colour = cubes.split(" ")[1];

                    if (colour.equals("red")) {
                        if (value > 12) {
                            valid = false;
                        }
                    } else if (colour.equals("blue")) {
                        if (value > 14) {
                            valid = false;
                        }

                    } else if (colour.equals("green")) {
                        if (value > 13) {
                            valid = false;
                        }

                    }
                }
            }

            if (valid) {
                total += game;
            }
        }
        return String.format("%d\n", total);
    }

    @Override
    public String part2(List<String> input) {
        int total = 0;

        for (String line: input) {
            String pulls = line.split(": ")[1];
            int game = Integer.valueOf(line.split(": ")[0].substring(5));


            int minRed = 0;
            int minGreen = 0;
            int minBlue = 0;

            for (String pull: pulls.split("; ")) {
                for (String cubes: pull.split(", ")) {
                    int value = Integer.valueOf(cubes.split(" ")[0]);
                    String colour = cubes.split(" ")[1];

                    if (colour.equals("red")) {
                        if (value > minRed) {
                            minRed = value;
                        }
                    } else if (colour.equals("blue")) {
                        if (value > minBlue) {
                            minBlue = value;
                        }

                    } else if (colour.equals("green")) {
                        if (value > minGreen) {
                            minGreen = value;
                        }

                    }
                }
            }

            total += (minRed * minGreen * minBlue);
        }
        return String.format("%d\n", total);
    }
}
