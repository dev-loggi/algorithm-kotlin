package programmers.java;

import programmers.Programmers;

public abstract class JavaSolution implements Programmers.Solution {
    @Override
    public abstract void execute();

    protected void print(String s, Object ... args) {
        System.out.printf(s, args);
    }

    protected void println(String s, Object ... args) {
        System.out.printf(s + "\n", args);
    }
}
