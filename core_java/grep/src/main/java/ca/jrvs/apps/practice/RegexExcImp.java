package ca.jrvs.apps.practice;

import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.nio.file.Files;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RegexExcImp implements RegexExc {

    /**
     * Return true if filename extension is jpg or jpeg (case-insensitive).
     * @param filename      File name to be matched
     * @return boolean      Boolean result determining if the file name matches
     */
    public boolean matchJpeg(String filename) {
        return Pattern.matches(".+\\.jp(e)?g$", filename);
    }

    /**
     * Return true if ip is valid. For the purposes of this method, IP addresses may range from
     * 0.0.0.0 to 999.999.999.999.
     * @param ip            IP address to be matched
     * @return boolean      Boolean result determining if the IP address matches
     */
    public boolean matchIp(String ip) {
        return Pattern.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}", ip);
    }

    /**
     * Return true if the given line is empty (e.g. empty, white space, tabs, etc..).
     * @param line          Line to be matched
     * @return boolean      Boolean result determining if the line matches
     */
    public boolean isEmptyLine(String line) {
        return Pattern.matches("\\s*", line);
    }

    /**
     * Performs the bash 'grep' operation using the given regular expression 'regex' and file 'readFile'
     * @param regex             The regular expression to match while reading
     * @param readFile          The file to be read
     * @return List<String>     All lines in the given file that match the given regular expression
     * @throws IOException      Exception that will be thrown if the file cannot be read
     */
    public List<String> jGrep(String regex, File readFile) throws IOException {
        // Retrieve all lines from the given file
        List<String> matchingLines = Files.readAllLines(readFile.toPath());

        // Remove the lines that do not match the given regular expression
        matchingLines.removeIf(s -> !Pattern.matches(regex, s));

        return matchingLines;
    }

    /**
     * Performs the bash 'grep' operation on every file in the given directory 'rootPath' using the given regular
     * expression 'regex' and writes the results to the given file 'writeFile'
     * @param regex             The regular expression to match while reading
     * @param rootPath          The directory to be navigated
     * @param writeFile         The results will be written to this file
     * @return List<String>     All lines in the files that were read that match the given regular expression
     * @throws IOException      Exception that will be thrown if 'writeFile' cannot be written to
     */
    public List<String> jRecGrep(String regex, File rootPath, File writeFile) throws IOException {
        // Retrieve all files in the given directory including files in subdirectories
        File[] allFiles = rootPath.listFiles();
        List<String> fileResult;

        // Apply grep operations to each file
        ArrayList<String> matchingLines = new ArrayList<>();
        for (File file : allFiles) {
            // If the current path is a file
            if (file.isFile()) {
                // Need to include a try-catch body in case some files throw an error when being read
                // We don't want to crash the entire program because of just one bad file
                try {
                    fileResult = jGrep(regex, file);
                    matchingLines.addAll(fileResult);
                }
                catch (IOException e) {
                    System.out.println("File " + file.getName() + " could not be read");
                }
            }
            // If the current path is not a file, we'll assume it's a directory
            else {
                matchingLines.addAll(jRecGrep(regex, file, writeFile));
            }
        }

        // Write results to new file
        BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile));
        for (String line : matchingLines) {
            writer.write(line + "\n");
        }
        writer.close();

        return matchingLines;
    }

    public static void main(String args[]) throws IOException {
        final String PATH = "C:\\Users\\Gabriel\\Desktop\\Git\\jarvis_data_eng_GabrielChan\\core_java\\grep\\src\\main";        // The directory to be used when reading files
        RegexExcImp r = new RegexExcImp();
        System.out.println("matchJpeg(abc.jpg): " + r.matchJpeg("abc.jpg"));
        System.out.println("matchJpeg(abc.jpeg): " + r.matchJpeg("abc.jpeg"));
        System.out.println("matchJpeg(.jpg): " + r.matchJpeg(".jpg"));
        System.out.println("matchJpeg(.jpeg): " + r.matchJpeg(".jpeg"));
        System.out.println("matchJpeg(abc.jpggx): " + r.matchJpeg("abc.jpggx"));
        System.out.println("matchJpeg(jpg): " + r.matchJpeg("jpg"));
        System.out.println("matchJpeg(jpeg): " + r.matchJpeg("jpeg") + "\n");
        System.out.println("matchIp(192.16.0.1): " + r.matchIp("192.16.0.1"));
        System.out.println("matchIp(182.168.100.100): " + r.matchIp("182.168.100.100"));
        System.out.println("matchIp(192.168): " + r.matchIp("192.168"));
        System.out.println("matchIp(192#168#0#1): " + r.matchIp("192#168#0#1") + "\n");
        System.out.println("isEmptyLine(): " + r.isEmptyLine(""));
        System.out.println("isEmptyLine( ): " + r.isEmptyLine(" "));
        System.out.println("isEmptyLine(    ): " + r.isEmptyLine("    "));
        System.out.println("isEmptyLine(a): " + r.isEmptyLine("a"));
        System.out.println("isEmptyLine(682): " + r.isEmptyLine("682"));
        System.out.println("isEmptyLine(fasvc& 8JD#@ 9): " + r.isEmptyLine("fasvc& 8JD#@ 9") + "\n");

        System.out.println("jGrep(test1, jpeg): " + r.jGrep(".+\\.jp(e)?g$", new File(PATH + "\\test1.txt")));
        System.out.println("jGrep(test1, IP): " + r.jGrep("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}", new File(PATH + "\\test1.txt")));
        System.out.println("jGrep(test2, IP): " + r.jGrep("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}", new File(PATH + "\\test2.txt")));

        System.out.println("jRecGrep(jpeg): " + r.jRecGrep(".+\\.jp(e)?g$", new File(PATH), new File("JpegGrepResult.txt")));
        System.out.println("jRecGrep(IP): " + r.jRecGrep("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}", new File(PATH), new File("IPGrepResult.txt")));
    }
}
