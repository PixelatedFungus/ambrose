package lusaris;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Wordle {
    private final ArrayList<String> _fiveLetterWords = new ArrayList<>();
    private ArrayList<String> _filteredWords;

    public Wordle() {
        readFromLetters();
        _filteredWords = new ArrayList<>(_fiveLetterWords);
    }

    private void readFromLetters() {
        try {
            File myFile = new File("./fiveletters.txt");
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                _fiveLetterWords.add(myReader.nextLine());
            }
            Collections.sort(_fiveLetterWords);
        } catch (FileNotFoundException e) {
            System.out.println("An error has occured");
            e.printStackTrace();
        }
    }

    public void invalidLetter(char c) {
        _filteredWords.removeIf(listWord -> listWord.contains(Character.toString(c)));
    }

    public void characterAt(char c, int index) {
        _filteredWords.removeIf(listWord -> !(listWord.charAt(index) == c));
    }

    public void characterExists(char c, int index) {
        _filteredWords.removeIf(listWord -> !listWord.contains((Character.toString(c))) || (listWord.charAt(index) == c));
    }

    public ArrayList<String> get_fiveLetterWords() {
        return _fiveLetterWords;
    }

    public ArrayList<String> get_filteredWords() {
        return _filteredWords;
    }
}
