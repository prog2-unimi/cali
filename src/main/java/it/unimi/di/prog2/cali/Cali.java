package it.unimi.di.prog2.cali;

import java.util.List;
import java.util.Set;

public class Cali {
  public static void main(String[] args) {
    if (args.length < 1) {
      System.err.println("Specify at least a fully qualified class name");
      return;
    }
    List<String> argList = List.of(args);
    String fq = argList.get(0);
    Set<String> methods = Set.copyOf(argList.subList(1, argList.size()));
    int idx = fq.lastIndexOf('.');
    if (idx == -1) {
      System.err.println("The first argument must be a fully qualified class name (containing at least a dot)");
      return;
    }
    Instrumentator instr = new Instrumentator(fq.substring(0, idx), "build/classes/java/instrumented");
    instr.addLog(fq.substring(idx + 1), methods.size() == 0 ? null : methods);
  }
}
