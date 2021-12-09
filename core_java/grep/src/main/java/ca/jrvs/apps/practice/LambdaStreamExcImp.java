package ca.jrvs.apps.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.Arrays;

public class LambdaStreamExcImp implements LambdaStreamExc{
    /**
     * Create a String stream from array
     * <p>
     * note: arbitrary number of value will be stored in an array
     *
     * @param strings
     * @return
     */
    public Stream<String> createStrStream(String... strings) {
        return Arrays.stream(strings);
    }

    /**
     * Convert all strings to uppercase
     * please use createStrStream
     *
     * @param strings
     * @return
     */
    public Stream<String> toUpperCase(String... strings) {
        return createStrStream(strings).map(s -> s.toUpperCase());
    }

    /**
     * Filter out strings that contains the pattern
     * e.g.
     * filter(stringStream, "a") will return another stream which no element contains a
     *
     * @param stringStream
     * @param pattern
     * @return
     */
    public Stream<String> filter(Stream<String> stringStream, String pattern) {
        return stringStream.filter(s -> !s.contains(pattern));
    }

    /**
     * Create a intStream from a arr[]
     *
     * @param arr
     * @return
     */
    public IntStream createIntStream(int[] arr) {
        return Arrays.stream(arr);
    }

    /**
     * Convert a stream to list
     *
     * @param stream
     * @return
     */
    public <E> List<E> toList(Stream<E> stream) {
        return Arrays.asList((E[]) stream.toArray());
    }

    /**
     * Convert a intStream to list
     *
     * @param intStream
     * @return
     */
    public List<Integer> toList(IntStream intStream) {
        List<Integer> list = new ArrayList<>();
        intStream.forEach(i -> list.add(i));
        return list;
    }

    /**
     * Create a IntStream range from start to end inclusive
     *
     * @param start
     * @param end
     * @return
     */
    public IntStream createIntStream(int start, int end) {
        return IntStream.range(start, end);
    }

    /**
     * Convert a intStream to a doubleStream
     * and compute square root of each element
     *
     * @param intStream
     * @return
     */
    public DoubleStream squareRootIntStream(IntStream intStream) {
        return intStream.asDoubleStream().map(d -> d = java.lang.Math.sqrt(d));
    }

    /**
     * Filter all even number and return odd numbers from a intStream
     *
     * @param intStream
     * @return
     */
    public IntStream getOdd(IntStream intStream) {
        return intStream.filter(i -> i % 2 == 1);
    }

    /**
     * Return a lambda function that print a message with a prefix and suffix
     * This lambda can be useful to format logs
     * <p>
     * You will learn:
     * - functional interface http://bit.ly/2pTXRwM & http://bit.ly/33onFig
     * - lambda syntax
     * <p>
     * e.g.
     * LambdaStreamExc lse = new LambdaStreamImp();
     * Consumer<String> printer = lse.getLambdaPrinter("start>", "<end");
     * printer.accept("Message body");
     * <p>
     * sout:
     * start>Message body<end
     *
     * @param prefix prefix str
     * @param suffix suffix str
     * @return
     */
    public Consumer<String> getLambdaPrinter(String prefix, String suffix) {
        return s -> System.out.println(prefix + s + suffix);
    }

    /**
     * Print each message with a given printer
     * Please use `getLambdaPrinter` method
     * <p>
     * e.g.
     * String[] messages = {"a","b", "c"};
     * lse.printMessages(messages, lse.getLambdaPrinter("msg:", "!") );
     * <p>
     * sout:
     * msg:a!
     * msg:b!
     * msg:c!
     *
     * @param messages
     * @param printer
     */
    public void printMessages(String[] messages, Consumer<String> printer) {
        Arrays.asList(messages).forEach(printer);
    }

    /**
     * Print all odd number from a intStream.
     * Please use `createIntStream` and `getLambdaPrinter` methods
     * <p>
     * e.g.
     * lse.printOdd(lse.createIntStream(0, 5), lse.getLambdaPrinter("odd number:", "!"));
     * <p>
     * sout:
     * odd number:1!
     * odd number:3!
     * odd number:5!
     *
     * @param intStream
     * @param printer
     */
    public void printOdd(IntStream intStream, Consumer<String> printer) {
        getOdd(intStream).mapToObj(String::valueOf).forEach(printer);
    }

    /**
     * Square each number from the input.
     * Please write two solutions and compare difference
     * - using flatMap
     *
     * @param ints
     * @return
     */
    public Stream<Integer> flatNestedInt(Stream<List<Integer>> ints) {
        // Imperative implementation
        /*
        ArrayList<Integer> list = new ArrayList<>();
        ints.forEach(l -> list.addAll(l));
        list.forEach(i -> i = i * i);
        return Stream.of(list.toArray(new Integer[0]));
        */
        // Declarative implementation
        return ints.flatMap(l -> {
            l.forEach(i -> i = i * i);
            return l.stream();
        });
    }

    public static void main(String[] args) {
        final String[] strings = {"8.1.3.2", "www.randomsite.com", "jrvs", "111.222.333.44j", "Who knew creating test cases was so much work?",
                "anotherpicture.jpeg", "notapicture.jpog", "notemptyspace"};
        final int[] ints = {2, 5, 9, 16, 89, 1276, 4382, 97};
        LambdaStreamExcImp lsei = new LambdaStreamExcImp();

        System.out.print("Testing createStrStream: ");
        Stream<String> stringStream = lsei.createStrStream(strings);
        stringStream.forEach(s -> System.out.print(s + ", "));
        System.out.println();

        System.out.print("Testing toUpperCase: ");
        Stream<String> upperStringStream = lsei.toUpperCase(strings);
        upperStringStream.forEach(s -> System.out.print(s + ", "));
        System.out.println();

        System.out.print("Testing filter: ");
        Stream<String> filteredStream = lsei.filter(lsei.createStrStream(strings), ".+\\.jp(e)?g$");
        filteredStream.forEach(s -> System.out.print(s + ", "));
        System.out.println();

        System.out.print("Testing createIntStream: ");
        IntStream intStream = lsei.createIntStream(ints);
        intStream.forEach(i -> System.out.print(i + ", "));
        System.out.println();

        List<String> stringList = lsei.toList(lsei.createStrStream(strings));
        System.out.println("Testing toList (E): " + stringList);

        List<Integer> intList = lsei.toList(lsei.createIntStream(ints));
        System.out.println("Testing toList (Integer): " + intList);

        System.out.print("Testing createIntStream (range): ");
        IntStream secondIntStream = lsei.createIntStream(0, 10);
        secondIntStream.forEach(i -> System.out.print(i + ", "));
        System.out.println();

        System.out.print("Testing squareRootIntStream: ");
        DoubleStream doubleStream = lsei.squareRootIntStream(lsei.createIntStream(ints));
        doubleStream.forEach(d -> System.out.print(d + ", "));
        System.out.println();

        System.out.print("Testing getOdd: ");
        IntStream oddIntStream = lsei.getOdd(lsei.createIntStream(ints));
        oddIntStream.forEach(i -> System.out.print(i + ", "));
        System.out.println();

        System.out.print("Testing printMessages: ");
        Consumer<String> printer = lsei.getLambdaPrinter("pre", "suff");
        lsei.printMessages(strings, printer);

        System.out.print("Testing printOdd: ");
        lsei.printOdd(lsei.createIntStream(ints), printer);
    }
}
