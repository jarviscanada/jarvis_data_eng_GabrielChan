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
    final Logger logger = LoggerFactory.getLogger(JavaGrepImp.class);

    private String regex;
    private File rootPath;
    private File outFile;

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

    /**
     * Top level search workflow
     * @throws IOException
     */
    public void process() throws IOException {
        ArrayList<String> matchedLines = new ArrayList<>();

        for (File file : listFiles(rootPath)) {
            for (String line : readLines(file)) {
                if (containsPattern(line)) {
                    matchedLines.add(line);
                }
            }
        }

        writeToFile(matchedLines);
    }

    /**
     * Traverse a given directory and return all files
     * @param rootDir       Input directory
     * @return              Files under 'rootDir'
     */
    public List<File> listFiles(File rootDir) {
        File[] files = rootDir.listFiles();
        ArrayList<File> allFiles = new ArrayList<>();
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

    /**
     * Read a file and return all the lines
     * @param inputFile                     File to be read
     * @return                              All the lines from the file
     * @throws IllegalArgumentException     If a given 'inputFile' is not a file
     * @throws IOException                  If the file could not be read
     */
    public List<String> readLines(File inputFile) throws IOException, IllegalArgumentException {
        return Files.readAllLines(inputFile.toPath());
    }

    /**
     * Check if a line contains the given regex pattern
     * @param line          Input string
     * @return              True if there is a match; false otherwise
     */
    public boolean containsPattern(String line) {
        return Pattern.matches(regex, line);
    }

    /**
     * Write a given list of lines to a file
     * @param lines             Lines to be written
     * @throws IOException      If write failed
     */
    public void writeToFile(List<String> lines) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));

        for (String line : lines) {
            writer.write(line + "\n");
        }
        writer.close();
    }

    /**
     * Retrieve the root path associated with this instance
     * @return              This instance's root path
     */
    public String getRootPath() {
        return rootPath.getName();
    }

    /**
     * Set the root path of this instance to be 'rootPath'
     * @param rootPath      Input root path
     */
    public void setRootPath(String rootPath) {
        this.rootPath = new File(rootPath);
    }

    /**
     * Retrieve the regular expression associated with this instance
     * @return              This instance's regular expression
     */
    public String getRegex() {
        return regex;
    }

    /**
     * Set the regular expression of this instance to be 'regex'
     * @param regex         Input regular expression
     */
    public void setRegex(String regex) {
        this.regex = regex;
    }

    /**
     * Retrieve the output file associated with this instance
     * @return              This instance's output file
     */
    public String getOutFile() {
        return outFile.getName();
    }

    /**
     * Set the output file of this instance to be 'outFile'
     * @param outFile         Input output file
     */
    public void setOutFile(String outFile) {
        this.outFile = new File(outFile);
    }

    public static void main(String[] args) throws IOException {
        final String PATH = "C:\\Users\\Gabriel\\Desktop\\Git\\jarvis_data_eng_GabrielChan\\core_java\\grep\\src\\main";        // The directory to be used when reading files
        String jpegRegex = ".+\\.jp(e)?g$";
        String IPRegex = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";

        JavaGrepImp jpegGrep = new JavaGrepImp(jpegRegex, PATH, "JpegResult.txt");
        JavaGrepImp IPGrep = new JavaGrepImp(IPRegex, PATH, "IPResult.txt");

        jpegGrep.process();
        IPGrep.process();
    }
}
