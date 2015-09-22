package com.enterprise.java.week2;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by student on 9/21/2015.
 */
public class FindAndReplaceTest extends TestCase {

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Test
    public void testConstructor(){
        FindAndReplace testCreation = new FindAndReplace(null, null, null);
        assertNotNull(testCreation);
    }

    @Test
    public void testReadInputFile() throws Exception {
        FindAndReplace findAndReplace = new FindAndReplace("/tests/testDocuments/TestInput.txt",
                "/tests/testDocuments/TestFindReplace.txt", "/test/testDocuments/testOutput.txt");

        findAndReplace.readInputFile();

        try(BufferedReader br = new BufferedReader(new FileReader("/test/testDocuments/testOutput.txt"))){

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testReplaceLineWithValueAddsToMap() throws Exception {
        FindAndReplace findAndReplace = new FindAndReplace(null, null, null);
        findAndReplace.replaceLineWithValue("Hello:Hi There!,Boom:BadaBoom");

        assertTrue(findAndReplace.findReplaceValues != null);
    }

    @Test
    public void testOutputFileWasCreated() throws Exception {
        FindAndReplace testOuputFile = new FindAndReplace("hi", "there", "boom");
        assertNotNull(testOuputFile.createOutputFile());
    }

    @Test(expected = IOException.class)
    public void testOutputFileThrowsException() throws Exception {
        FindAndReplace testFileNFound = new FindAndReplace("Boom!!", "/java/home/bananas", null);
        testFileNFound.createOutputFile();
    }
}