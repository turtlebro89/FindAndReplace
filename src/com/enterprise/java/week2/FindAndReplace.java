package com.enterprise.java.week2;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Sample Find and Replace program to practice debugging, unit testing, and refactoring.
 *
 * The program will read in a file of words and their replacement values. The format of the file is:
 * Placeholder:ReplacementValue,Placeholder:ReplacementValue
 *
 * The program will read in a file that contains some placeholder "words" to be replaced,
 * will replace the words with the correct values and then write out a new file.
 *
 *@author paulawaite
 */
public class FindAndReplace {

    String inputFile;
    String outputFile;
    String findReplaceFile;
    Map<String, String> findReplaceValues = new HashMap<>();

    public FindAndReplace(String inputFile, String outputFile, String findReplaceFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        this.findReplaceFile = findReplaceFile;
    }

    public void run() {
        readFindReplaceFile();
        readInputFile();
    }

    public void readFindReplaceFile(){
        try (BufferedReader in = new BufferedReader(new FileReader(findReplaceFile))) {
            while(in.ready()){
                addReplaceValuesToMap(in.readLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addReplaceValuesToMap(String line){
        String[] words = line.split(",");

        for (int i = 1; i < words.length; i++) {
            String arr[] = words[i].split(":");
            findReplaceValues.put(arr[0], arr[1]);
        }

    }

    public void readInputFile() {
        String line = null;
        PrintWriter out = createOutputFile();

        try (BufferedReader in = new BufferedReader(new FileReader(inputFile))) {
            while (in.ready()) {
                replaceLineWithValue(in.readLine());
                out.print(line);
            }
            if(out != null){
                out.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void replaceLineWithValue(String line){
        for (Map.Entry<String, String> entry : findReplaceValues.entrySet()) {
            line = line.replaceAll(entry.getKey(), entry.getValue());
        }
    }

    public PrintWriter createOutputFile() {
        try {
           PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outputFile)));
                return out;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
