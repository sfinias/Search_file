package project1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import static java.lang.System.exit;

public class Main {

    final static String FINALFILE = "wordcount.txt";

    public static void main(String[] args) {
        if (args.length!=2){
            System.out.println("Please provide valid arguments");
            System.out.println("Argument 1: path to text file");
            System.out.println("Argument 2: command to apply <wc or find>");
            exit(0);
        }
        try{
            //Create the file if it doesn't exist and declare that you want to write at the end of it
            FileOutputStream resultFile = new FileOutputStream(FINALFILE, true);
            String path = args[0];
            String command =args[1].toLowerCase();
            FileReader file = new FileReader(path);
            SearchFile searchFile = new SearchFile(path, file, resultFile);
            System.out.println(searchFile.getTime()+": File " + path + " found!");
            if (!path.endsWith(".txt")) {
                System.out.println("File " + path + " is not a text file.");
                exit(0);
            }
            switch (command){
                case "wc":
                    searchFile.wordCount();
                    break;
                case "find":
                    searchFile.findWord();
                    break;
                default:
                    System.out.println("Argument " + command + " is invalid. Only wc and find are supported");
            }
        }
        catch (FileNotFoundException ex){
            System.err.println(args[0] + " is not a valid file path");
        }
    }
}