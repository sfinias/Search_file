package project1;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SearchFile {

    private String path;
    private FileReader file;
    private FileOutputStream resultFile;

    public SearchFile(String path, FileReader file, FileOutputStream resultFile){
        this.path = path;
        this.file = file;
        this.resultFile = resultFile;
    }

    public void wordCount(){
        System.out.println(getTime()+": Total word count started");
        Scanner scanner = new Scanner(file);
         int count = 0;
        while (scanner.hasNext()) {
            count++;
            scanner.next();
        }
        scanner.close();
        System.out.println(getTime() + ": Word count finished. Counted " + count + " words");
        writeFile(resultFile, path, count);
    }

    public void findWord(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the word you wish to search in the file: ");
        String word = scanner.next().toLowerCase();
        System.out.println(getTime() + ": Counting occurrences of word " + word);
        scanner = new Scanner(file);
        int count = 0;
        StringBuilder fileWord = new StringBuilder();
        while (scanner.hasNext()){
            //Appends the word in an empty StringBuilder
            fileWord.append(scanner.next().toLowerCase());
            while (fileWord.indexOf(word) != -1){
                count++;
                //Deletes the instance of the searched word from the read word
                fileWord.delete(0, fileWord.indexOf(word) + word.length());
            }
            //Empties the StringBuilder
            fileWord.setLength(0);
        }
        scanner.close();
        System.out.println(getTime() + ": Count of word " + word + " finished. Occurrences found: " + count);
        writeFile(resultFile, path, word, count);
    }

    public String getTime(){
        ZonedDateTime time = ZonedDateTime.now();
        return DateTimeFormatter.ofPattern("kk:mm:ss").format(time);
    }

    public String getDate(){
        ZonedDateTime date = ZonedDateTime.now();
        return DateTimeFormatter.ofPattern("dd/MM/uuuu").format(date);
    }

    //Overloaded method for writing if we counted the words in the file
    public void writeFile(FileOutputStream file, String path, int result){
        PrintWriter fileWriter = new PrintWriter(file);
        String message = getDate()+"\t"+getTime()+"\t"+"wc"+"\t"+path+"\t"+result;
        fileWriter.println(message);
        fileWriter.close();
    }

    //Overloaded method for writing if we searched for a word in the file
    public void writeFile(FileOutputStream file, String path, String commandWord, int result){
        PrintWriter fileWriter = new PrintWriter(file);
        String message = getDate()+"\t"+getTime()+"\t"+"find"+"\t"+path+"\t"+commandWord+":"+result;
        fileWriter.println(message);
        fileWriter.close();
    }
}
