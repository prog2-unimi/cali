package it.unimi.di.prog2.cali;

import java.io.IOException;
import java.util.Set;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

public class Instrumentator {
  final String pkg;
  final String dest;

  public Instrumentator(String pkg, String dest) {
    this.pkg = pkg;
    this.dest = dest;
  }

  public void addLog(String cls, Set<String> methods) {
    ClassPool pool = ClassPool.getDefault();
    pool.importPackage("java.util.Arrays");
    CtClass cc;
    try {
      cc = pool.get(pkg + "." + cls);
    } catch (NotFoundException e1) {
      System.err.printf("Class %s not found in package %s\n", cls, pkg);
      return;
    }
    for (CtMethod m : cc.getDeclaredMethods()) {
      String name = m.getName();
      if (methods != null && !methods.contains(name)) continue;
      String code =
          String.format(
              "{System.out.print(\"%s.%s.%s(\");"
                  + "String args = Arrays.deepToString($args);"
                  + "System.out.print(args.substring(1, args.length() - 1));"
                  + "System.out.println(\")\");}",
              pkg, cls, name);
      try {
        m.insertBefore(code);
        System.out.println("Instrumented method " + m.getLongName());
      } catch (CannotCompileException e) {
        System.err.println("Failed to instrument method " + m.getLongName());
      }
    }
    try {
      cc.writeFile(dest);
    } catch (CannotCompileException | IOException e) {
      System.err.printf("Failed to write class %s in package %s\n", cls, pkg);
    }
  }
}
