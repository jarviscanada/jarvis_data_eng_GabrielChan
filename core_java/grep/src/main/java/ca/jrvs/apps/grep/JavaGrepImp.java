package ca.jrvs.apps.grep;

import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.nio.file.Files;
import java.io.BufferedWriter;
import java.io.FileWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaGrepImp implements JavaGrep {
    protected final Logger logger = LoggerFactory.getLogger(JavaGrepImp.class);

    protected String regex;
    protected File rootPath;
    protected File outFile;

    /**
     * Creates a JavaGrepImp instance using the given regular expression, root directory and output file
     * @param regex             Input regular expression
     * @param rootPath          Input root directory
     * @param outFile           Input output file
     */
    public JavaGrepImp(String regex, String rootPath, String outFile) {
        setRegex(regex);
        setRootPath(rootPath);
        setOutFile(outFile);
    }

    public void process() throws IOException {
        ArrayList<String> matchedLines = new ArrayList<>();

        // For each file in the root directory
        for (File file : listFiles(rootPath)) {
            // For each line read from the current file
            for (String line : readLines(file)) {
                if (containsPattern(line)) {
                    matchedLines.add(line);
                }
            }
        }

        writeToFile(matchedLines);
    }

    public List<File> listFiles(File rootDir) {
        // Retrieve all files in 'rootDir'
        File[] files = rootDir.listFiles();
        ArrayList<File> allFiles = new ArrayList<>();

        // For each file
        for (File file : files) {
            if (file.isFile()) {
                allFiles.add(file);
            }
            else if (file.isDirectory()) {
                allFiles.addAll(listFiles(file));
            }
        }

        return allFiles;
    }

    public List<String> readLines(File inputFile) throws IOException, IllegalArgumentException {
        return Files.readAllLines(inputFile.toPath());
    }

    public boolean containsPattern(String line) {
        return Pattern.matches(regex, line);
    }

    public void writeToFile(List<String> lines) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));

        // For each line
        for (String line : lines) {
            writer.write(line + "\n");
        }
        writer.close();
    }

    public String getRootPath() {
        return rootPath.getName();
    }

    public void setRootPath(String rootPath) {
        this.rootPath = new File(rootPath);
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public String getOutFile() {
        return outFile.getName();
    }

    public void setOutFile(String outFile) {
        this.outFile = new File(outFile);
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 3) {
            JavaGrepImp jGrep = new JavaGrepImp(args[0], args[1], args[2]);
            jGrep.process();
        }
        else {
            System.out.println("Incorrect number of arguments");
        }
        /*
        final String PATH = "C:\\Users\\Gabriel\\Desktop\\Git\\jarvis_data_eng_GabrielChan\\core_java\\grep\\src\\main";        // The directory to be used when reading files
        String jpegRegex = ".+\\.jp(e)?g$";
        String IPRegex = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";

        JavaGrepImp jpegGrep = new JavaGrepImp(jpegRegex, PATH, "JpegResult.txt");
        JavaGrepImp IPGrep = new JavaGrepImp(IPRegex, PATH, "IPResult.txt");

        jpegGrep.process();
        IPGrep.process();
        */
    }
}
