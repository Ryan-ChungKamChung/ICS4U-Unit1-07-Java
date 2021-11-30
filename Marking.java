/*
* This is a program generates marks
* after reading in 2 text files.
*
* @author  Mr Coxall
* @version 1.0
* @since   2020-01-01
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
* This is the marks program.
*/
final class Marks {

    /**
    * Prevent instantiation
    * Throw an exception IllegalStateException.
    * if this ever is called
    *
    * @throws IllegalStateException
    *
    */
    private Marks() {
        throw new IllegalStateException("Cannot be instantiated");
    }

    /**
    * The generateMarks() function.
    *
    * @param arrayOfStudents the collection of students
    * @param arrayOfAssignments the collection of assignments
    * @return the generated marks
    */
    public static String[][] generateTable(
            final String[] students, final String[] assignments) {

        int numStudents = students.length;
        int numAssignments = assignments.length;
        Random random = new Random();

        String[][] markArray = new String[numStudents][numAssignments + 1];

        for (int loop1 = 0; loop1 < numStudents; loop1++) {

            markArray[loop1][0] = students[loop1];

            for (int loop2 = 0; loop2 < numAssignments; loop2++) {

                final int mark = (int) Math.floor(
                        random.nextGaussian() * 10 + 75);

                markArray[loop1][loop2 + 1] = String.valueOf(mark);
            }
        }

        return markArray;
    }

    /**
    * The starting main() function.
    *
    * @param args No args will be used
    */
    public static void main(final String[] args) {
        final ArrayList<String> listOfStudents = new ArrayList<String>();
        final ArrayList<String> listOfAssignments = new ArrayList<String>();
        final String[] arrayOfStudents;
        final String[] arrayOfAssignments;

        final Path studentFilePath = Paths.get("./", args[0]);
        final Path assignmentFilePath = Paths.get("./", args[1]);
        final Charset charset = Charset.forName("UTF-8");

        try (BufferedReader readerStudent = Files.newBufferedReader(
                                     studentFilePath, charset)) {
            String lineStudent = null;
            while ((lineStudent = readerStudent.readLine()) != null) {
                listOfStudents.add(lineStudent);
            }
        } catch (IOException errorCode) {
            System.err.println(errorCode);
        }

        try (BufferedReader readerAssignment = Files.newBufferedReader(
                                     assignmentFilePath, charset)) {
            String lineAssignment = null;
            while ((lineAssignment = readerAssignment.readLine()) != null) {
                listOfAssignments.add(lineAssignment);
            }
        } catch (IOException errorCode) {
            System.err.println(errorCode);
        }

        arrayOfStudents = listOfStudents.toArray(new String[0]);
        arrayOfAssignments = listOfAssignments.toArray(new String[0]);

        final String[][] marksArray = generateTable(
                arrayOfStudents, arrayOfAssignments);

        System.out.println(Arrays.deepToString(marksArray)
                .replace("], ", "\n")
                .replace("[", "")
                .replace("]", ""));

        try {

            FileWriter writer = new FileWriter("./marks.csv");

            for (String[] array : marksArray) {
                writer.append(Arrays.deepToString(array)
                        .replace("[", "")
                        .replace("]", ""));

                writer.append("\n");
            }

            writer.close();
        } catch (IOException exception) {
            System.out.println("Failed to output to out.csv");
        }

        System.out.println("\nDone.");
    }
}

