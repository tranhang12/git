package sample;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class DictionaryCommandLine {
    public DictionaryCommandLine() {}
    public static DictionaryManagement dim = new DictionaryManagement();

    public void showAllWords() {
        List<Word> list = dim.getWordList();
        System.out.println("|-------------------------------------------|");
        System.out.println("|     No     |    English   |   Vietnamese  |");
        System.out.println("|-------------------------------------------|");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(" " + (i + 1) + "        | " + list.get(i).getWord_target());
            for(int j = 0; j < 16 - list.get(i).getWord_target().length(); j++) {
                System.out.print(" ");
            }
            System.out.print("| " + list.get(i).getWord_explain());
            System.out.println();
        }
    }

    public void dictionaryAvanced() {
        dim.insertFromFile();
        showAllWords();
        //dim.dictionaryLookup();
    }

    public void dictionarySearcher() {
        System.out.println("Enter to search: ");
        Scanner sc = new Scanner(System.in);
        String word = sc.next();
        int length = word.length();
        int size = dim.wordList.size();
        for (int i = 0; i < size; i++) {
            if (!dim.wordList.get(i).getWord_target().startsWith(word.toLowerCase())) {
                continue;
            }
            System.out.println(dim.wordList.get(i).getWord_target());
        }
    }

    /*
    public static void main(String[] args) {
        DictionaryCommandLine dcl = new DictionaryCommandLine();
        dcl.dictionaryAvanced();
        dim.dictionaryUpdate();
        dcl.dictionarySearcher();
        dim.dictionaryExportToFile();
    }
     */
}