# Calì

Calì is a quick and dirty implementation of a **ca**ll **l**og **i**nstrumentator based on [javassist](https://www.javassist.org/) that modifies existing classes adds a *print* statement reporting the (fully qualified) *method name* and *actual parameters* of selected methods of a given class.

## Usage

Assuming that the *fat jar* of this tool is `build/libs/cali-0.1.0.jar` and that your classes have been compiled in `build/classes/java/test`, the invocation

    java -cp build/classes/java/test/:build/libs/cali-0.1.0.jar it.unimi.di.prog2.cali.Cali it.unimi.di.prog2.cali.Example twice

produces the output

    Instrumented method it.unimi.di.prog2.cali.Example.twice(int)
    Instrumented method it.unimi.di.prog2.cali.Example.twice(long)

meaning that all the methods called `twice` of the `Example` class have been instrumented; the modified code is written in `build/classes/java/instrumented` so that the following invocation

    java -cp build/classes/java/instrumented it.unimi.di.prog2.cali.Example

produces the output

    it.unimi.di.prog2.cali.Example.twice(3)
    6
    it.unimi.di.prog2.cali.Example.twice(1.5)
    3.0

in which odd lines are the call logging inserted by cali.