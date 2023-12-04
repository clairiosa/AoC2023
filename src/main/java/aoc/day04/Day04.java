package aoc.day04;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import aoc.Day;

public class Day04 implements Day {

    @Override
    public String part1(List<String> input) {
        int total = 0;
        for (String line: input) {
            String card = line.split(":")[1];
            String winning = card.split("\\s\\|\\s")[0];
            String yours = card.split("\\s\\|\\s")[1];

            List<Integer> winningNumbers = new ArrayList<>();
            for (String num:winning.split("\\s")) {
                if (num.isEmpty()) {
                    continue;
                }
                winningNumbers.add(Integer.valueOf(num));
            }

            List<Integer> yourNumbers = new ArrayList<>();
            for (String num:yours.split("\\s")) {
                if (num.isEmpty()) {
                    continue;
                }
                num = num.trim();
                yourNumbers.add(Integer.valueOf(num));
            }


            int points = 0;
            for (int num : yourNumbers) {
                if (winningNumbers.contains(num)) {
                    if (points == 0) {
                        points = 1;
                    } else {
                        points *= 2;
                    }
                }
            }
            total += points;
        }
        return String.format("%d\n", total);
    }

    @Override
    public String part2(List<String> input) {
        int total = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i<=input.size();i++) {
            map.put(i, 1);
        }

        int cardNumber = 0;
        for (String line: input) {
            cardNumber++;
            String card = line.split(":")[1];
            String winning = card.split("\\s\\|\\s")[0];
            String yours = card.split("\\s\\|\\s")[1];

            List<Integer> winningNumbers = new ArrayList<>();
            for (String num:winning.split("\\s")) {
                if (num.isEmpty()) {
                    continue;
                }
                winningNumbers.add(Integer.valueOf(num));
            }

            List<Integer> yourNumbers = new ArrayList<>();
            for (String num:yours.split("\\s")) {
                if (num.isEmpty()) {
                    continue;
                }
                num = num.trim();
                yourNumbers.add(Integer.valueOf(num));
            }


            int points = 1;
            for (int num : yourNumbers) {
                if (winningNumbers.contains(num)) {
                    map.put(cardNumber+points, map.get(cardNumber+points)+(map.get(cardNumber)));
                    points++;
                }
            }
        }
        for (int value: map.values()) {
            total += value;
        }
        return String.format("%d\n", total);
    }

}
