package ca.jrvs.apps.grep;

import java.io.IOException;
import java.io.File;
import java.util.List;

public interface JavaGrep {

    /**
     * Top level search workflow
     * @throws IOException
     */
    void process() throws IOException;

    /**
     * Traverse a given directory and return all files
     * @param rootDir       Input directory
     * @return              Files under 'rootDir'
     */
    List<File> listFiles(File rootDir);

    /**
     * Read a file and return all the lines
     * @param inputFile                     File to be read
     * @return                              All the lines from the file
     * @throws IllegalArgumentException     If a given 'inputFile' is not a file
     * @throws IOException                  If the file could not be read or written
     */
    List<String> readLines(File inputFile) throws IOException, IllegalArgumentException;

    /**
     * Check if a line contains the given regex pattern
     * @param line          Input string
     * @return              True if there is a match; false otherwise
     */
    boolean containsPattern(String line);

    /**
     * Write a given list of lines to a file
     * @param lines             Lines to be written
     * @throws IOException      If write failed
     */
    void writeToFile(List<String> lines) throws IOException;

    /**
     * Retrieve the root path associated with this instance
     * @return              This instance's root path
     */
    String getRootPath();

    /**
     * Set the root path of this instance to be 'rootPath'
     * @param rootPath      Input root path
     */
    void setRootPath(String rootPath);

    /**
     * Retrieve the regular expression associated with this instance
     * @return              This instance's regular expression
     */
    String getRegex();

    /**
     * Set the regular expression of this instance to be 'regex'
     * @param regex         Input regular expression
     */
    void setRegex(String regex);

    /**
     * Retrieve the output file associated with this instance
     * @return              This instance's output file
     */
    String getOutFile();

    /**
     * Set the output file of this instance to be 'outFile'
     * @param outFile         Input output file
     */
    void setOutFile(String outFile);
}
