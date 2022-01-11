# Introduction
This application, given a regular expression, a directory and an output file, will read each file in the given 
directory (including in sub-directories) and return lines that match the given regular expression. Notable classes that
were used include the `Pattern` class for matching lines with regular expression as well as the `BufferedReader`, 
`BufferedWriter` and `nio.Files` class for reading and writing files. There is also a lambda implementation which 
focuses more on using lambda functions and the `Stream` class. The IntelliJ IDE was used to write this application
and Docker was used for deployment.

# Quick Start
If running from java `.class` file:
```
java JavaGrepImp [regular expression] [root directory] [output file]
```

If running from Docker container:
```
docker run gabrielchan1/grep [regular expression] [root directory] [output file]
```

# Implementation
## Pseudocode
- Iterate through each file in the given directory.
- If the file is readable, iterate through each line and retrieve the lines that match the given regular expression.
- If the file is a directory, recursively call this method on this directory.
- Return all lines that were retrieved.

## Performance Issue
Some files or directories could be very large and there may also be circumstances where we need to work with a 
restricted heap size. To avoid errors regarding memory limitations, each file would need to be read and processed one 
line at a time instead of the whole file at once. The `BufferedReader` class could be used to read files in this 
manner. Alternatively, we could create a `FileInputStream` using the given path and use the `Scanner` class to iterate
through the file.

# Test
We decided to test this application by creating sample text files. Three text files were created in the working 
directory with one being inside a nested directory. Each text file contained about eight lines of text. We used regular 
expressions for detecting names of JPEG files as well as IP addresses. The `process` method would be called on the
working directory.

# Deployment
The Docker image containing this program was built using a Dockerfile and then pushed to Docker Hub. It can be pulled
from Docker Hub with the command `docker pull gabrielchan1/grep`.

# Improvement
To make the application more flexible, I would make the output file argument optional. If no output file is given, the
result of the application is printed to the terminal instead. Allowing the application to be run this way could save 
the application from having to perform writing operations. This could potentially shorten run time and also make the
application usable under more circumstances (e.g. user may not have permission to write to files).
