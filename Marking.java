/*
* This is a program generates marks
* after reading in 2 text files.
*
* @author  Ryan Chung
* @version 1.0
* @since   2020-11-30
*/

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
* This is the marks program.
*/
final class Marking {

    /**
    * Prevent instantiation
    * Throw an exception IllegalStateException.
    * if this ever is called
    *
    * @throws IllegalStateException
    *
    */
    private Marking() {
        throw new IllegalStateException("Cannot be instantiated");
    }

    /**
    * The generateTable() function.
    *
    * @param students the array of students
    * @param assignments the array of assignments
    * @return the generated marks
    */
    public static String[][] generateTable(
            final String[] students, final String[] assignments) {

        final int numStudents = students.length;
        final int numAssignments = assignments.length;
        final Random random = new Random();

        final String[][] markArray =
            new String[numStudents][numAssignments + 1];

        for (int column = 0; column < numStudents; column++) {

            markArray[column][0] = students[column];

            for (int row = 0; row < numAssignments; row++) {

                final int mark = (int) Math.floor(
                        random.nextGaussian() * 10 + 75);

                markArray[column][row + 1] = String.valueOf(mark);
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

        final String frontSquareBrace = "[";
        final String backSquareBrace = "]";
        final String sameDirectory = "./";
        final String newLine = "\n";

        final ArrayList<String> listOfStudents = new ArrayList<String>();
        final ArrayList<String> listOfAssignments = new ArrayList<String>();
        final String[] arrayOfStudents;
        final String[] arrayOfAssignments;

        final Path studentFilePath = Paths.get(sameDirectory, args[0]);
        final Path assignmentFilePath = Paths.get(sameDirectory, args[1]);
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

        try {

            final FileWriter writer = new FileWriter("./marks.csv");

            writer.append(", " + Arrays.toString(arrayOfAssignments)
                    .replace(frontSquareBrace, "")
                    .replace(backSquareBrace, "") + newLine);

            for (String[] array : marksArray) {
                writer.append(Arrays.deepToString(array)
                        .replace(frontSquareBrace, "")
                        .replace(backSquareBrace, "") + newLine);
            }

            writer.close();
        } catch (IOException exception) {
            System.out.println("Failed to output to out.csv");
        }

        System.out.println("\nDone.");
    }
}

