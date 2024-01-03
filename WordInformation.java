public class WordInformation {
    private String word;
    private LinkedList<WordOccurrence> occList;
    public int size;

    public WordInformation() {
        word = "";
        occList = new LinkedList<> ();
        size =0;
    }

    public WordInformation(String word, int Line, int Position) {
        this.word = word;
        occList = new LinkedList<> ();
        occList.insert(new WordOccurrence(Line, Position ));
        size =1;
    }

    @Override
    public String toString() {

        String wordInfo =  "Word information:\n" + word + "\nLength: " + word.length() + "\nFrequency: " + size + "\nLocations: " ;
        occList.findFirst();
        while (!occList.last())
        {
            wordInfo = wordInfo + occList.retrieve().toString();
            occList.findNext();
        }
        wordInfo = wordInfo + occList.retrieve().toString();
        return wordInfo;
    }

    public LinkedList<WordOccurrence> getOccList() {
        return occList;
    }

    public String getWord() {
        return word;
    }

    public void setOccList(LinkedList<WordOccurrence> occList) {
        this.occList = occList;
    }

    public void setWord(String word) {
        this.word = word;
    }

}


