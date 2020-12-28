package com.github.aybici;

public class ArgsCounter {
    public static int[] countArgs(String args) {
        int normal = 0;
        int possible = 0;

        for (char c : args.toCharArray()) {
            if (c == '<') {
                normal++;
            } else if (c == '[') {
                possible++;
            }
        }

        int[] allPosibilities = new int[possible + 1];

        for (int num = normal, i = 0; i <= possible; num++, i++)
            allPosibilities[i] = num;

        return allPosibilities;
    }
}
