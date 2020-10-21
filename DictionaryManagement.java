package sample;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DictionaryManagement {
    public DictionaryManagement() {}

    public List<Word> wordList = new ArrayList<>();

    public  List<Word> getWordList() {
        return wordList;
    }

    public void setWordList(List<Word> wordList) {
        this.wordList = wordList;
    }

    public void insertFromFile() {
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader("dictionaries.txt"));
            String contentline = bufferedReader.readLine();
            while (contentline != null) {
                String target = contentline.substring(0, contentline.indexOf(" "));
                String explain = contentline.substring(contentline.indexOf(" "), contentline.length()).trim();
                wordList.add(new Word(target, explain));
                contentline = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null)
                    bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String dictionaryLookup(String s) {
        boolean find = false;
        String mean = "";
        for (Word w : wordList) {
            if (!w.getWord_target().equalsIgnoreCase(s)) {
                find = true;
                mean = w.getWord_explain();
                break;
            }
        }

        if (find) {
            return mean;
        } else {
            return "not found";
        }
    }

    public void addWord(List<Word> wordList, String eng, String viet) {
        wordList.add(new Word(eng, viet));
    }

    public void editWord() {
        System.out.println("Enter word which you want to edit: ");
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        boolean haveFound = false;
        for (Word w : wordList) {
            if (word.equalsIgnoreCase(w.getWord_target())) {
                System.out.println("Enter new word to replace this word: ");
                String newWord = sc.nextLine();
                w.setWord_target(newWord);
                System.out.println("Enter new definition: ");
                String def = sc.nextLine();
                w.setWord_explain(def);
                System.out.println("Done!");
                haveFound = true;
                break;
            }
        }

        if (!haveFound) System.out.println(word + " was not found");
    }

    public void deleteWord() {
        System.out.println("Enter word which you want to delete: ");
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        boolean haveFound = false;
        for (Word w : wordList) {
            if (word.equalsIgnoreCase(w.getWord_target())) {
                wordList.remove(w);
                System.out.println("Done!");
                haveFound = true;
                break;
            }

            if (!haveFound) System.out.println(word + " was not found.");
        }
    }

    public void dictionaryExportToFile() {
        BufferedWriter bufferedWriter = null;
        try {
            File file = new File("dictionaries.txt");
            file.delete();
            File newFile = new File("dictionaries.txt");
            if (!newFile.exists()) {
                file.createNewFile();
            }

            bufferedWriter = new BufferedWriter(new FileWriter("dictionaries.txt", true));
            for (Word w : wordList) {
                bufferedWriter.write(w.getWord_target() + "    " + w.getWord_explain() + "\n");

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null)
                    bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
