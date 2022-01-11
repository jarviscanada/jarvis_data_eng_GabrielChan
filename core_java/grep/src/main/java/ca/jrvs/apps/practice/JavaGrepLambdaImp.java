package ca.jrvs.apps.practice;

import ca.jrvs.apps.grep.JavaGrepImp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaGrepLambdaImp extends JavaGrepImp {

    public JavaGrepLambdaImp(String regex, String rootPath, String outFile) {
        super(regex, rootPath, outFile);
    }

    @Override
    public List<File> listFiles(File rootDir) {
        File[] files = rootDir.listFiles();
        ArrayList<File> allFiles = new ArrayList<>();

        // Retrieve files in the current directory
        allFiles.addAll(Stream.of(files).filter(file -> file.isFile()).collect(Collectors.toList()));

        // Retrieve files in nested directories
        Stream.of(files).filter(file -> file.isDirectory()).forEach(d -> allFiles.addAll(listFiles(d)));

        return allFiles;
    }

    @Override
    public List<String> readLines(File inputFile) throws IOException, IllegalArgumentException {
        return new BufferedReader(new FileReader(inputFile)).lines().collect(Collectors.toList());
    }

}
