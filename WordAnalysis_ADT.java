import java.io.*;
public class WordAnalysis_ADT{
    LinkedList<WordInformation> [] arrayOfDifferentLengths;
    static WordInformation [] sortedArray;
    int n, m, k;

    public WordAnalysis_ADT(){ } // default constructor
    public WordAnalysis_ADT(String s){
        String fileInfo= fileInfo(s);
        arrayOfDifferentLengths =  new LinkedList[k];
        sortedArray= new WordInformation[1000];
        readFileAndAnalyse(s);
    }

    //--------------------------------------------

    public void readFileAndAnalyse(String filename){

        String fData = fileInfo(filename);
        sortedArray = new WordInformation [1000];

        arrayOfDifferentLengths =  new LinkedList [k];
        for (int i= 0; i<arrayOfDifferentLengths.length; i++)
            arrayOfDifferentLengths[i]= new LinkedList<WordInformation> ();
        n =0;
        m =0;

        String[] linesArray = fData.split(System.lineSeparator());
        String[] s;
        int index = 0;
        while (index < linesArray.length) {
            int position =0;
            s = linesArray[index].split("[;:. ,]");

            for (int i = 0; i < s.length; i++) {
                String word = s[i].trim();
                if (!word.equalsIgnoreCase("")){
                    position++;
                    n++;
                    boolean flag = false;

                    if (arrayOfDifferentLengths[word.length()].empty())
                        arrayOfDifferentLengths[word.length()].insert(new WordInformation (word, index+1, position));
                    else {
                        arrayOfDifferentLengths[word.length()].findFirst();
                        while (!flag &&  !arrayOfDifferentLengths[word.length()].last()) {
                            WordInformation data = arrayOfDifferentLengths[word.length()].retrieve();

                            if (data.getWord().equalsIgnoreCase(word)) {
                                data.getOccList().insert(new WordOccurrence(index+1, position ));
                                data.size++;
                                arrayOfDifferentLengths[word.length()].update(data);
                                flag = true;
                            }
                            else
                                arrayOfDifferentLengths[word.length()].findNext();
                        }
                        if (!flag) {
                            WordInformation data = arrayOfDifferentLengths[word.length()].retrieve();
                            if (data.getWord().equalsIgnoreCase(word)) {
                                data.getOccList().insert(new WordOccurrence(index+1, position));
                                data.size++;
                                arrayOfDifferentLengths[word.length()].update(data);
                                flag = true;
                            }
                        }

                        if (!flag)
                            arrayOfDifferentLengths[word.length()].insert(new WordInformation (word, index+1, position));
                    }

                    if (!flag) {
                        sortedArray[m] = new WordInformation (word, index+1, position);
                        m++;
                    }
                    else
                        for ( int x = 0 ; x < m ; x++)
                            if (sortedArray[x] != null && sortedArray[x].getWord().equalsIgnoreCase(word))
                                sortedArray[x].size ++ ;
                }

            }
            index++;
        }
        mergesort(0, m-1 );
    }

    //--------------------------------------------
    private String fileInfo(String f){
        String lines = "";
        k = 0;
        try{
            BufferedReader bfr = new BufferedReader(new FileReader(f));
            String line = bfr.readLine();
            while (line != null) {

                while (line.contains("\\n"))
                    line = line.replace("\\n" ,  System.lineSeparator());

                line = line.replaceAll("[?\"“”!]", "");

                String [] str = line.split("[;, .]");
                for (int i = 0; i < str.length; i++)
                    if (k < str[i].trim().length())
                        k = str[i].trim().length();

                lines = lines + line + System.lineSeparator() ;
                line = bfr.readLine();
            }
            k++;
            bfr.close();
        }
        catch (Exception e) {
            System.out.println("an exception was found while reading the file.");
        }
        System.out.println(lines);
        return lines;
        }

    //--------------------------------------------

    public int documentLength(){ //The method will return the length of document
        return n;
    }

    //--------------------------------------------

    public int uniqueWords(){
        return m;
    }

    //--------------------------------------------

    public int totalWord (String s){
        int total = 0 ;

        if ( arrayOfDifferentLengths[s.length()].getsize() >= 1) {
            arrayOfDifferentLengths[s.length()].findFirst();

            while (!arrayOfDifferentLengths[s.length()].last()) {
                if (arrayOfDifferentLengths[s.length()].retrieve().getWord().equalsIgnoreCase(s))
                    total = arrayOfDifferentLengths[s.length()].retrieve().size;
                arrayOfDifferentLengths[s.length()].findNext();
            }

            if (arrayOfDifferentLengths[s.length()].retrieve().getWord().equalsIgnoreCase(s))
                total = arrayOfDifferentLengths[s.length()].retrieve().size;
        }
        return total;
    }

    //--------------------------------------------

    public int totalWordsForLength(int l){ //The method will return the size of the word list for words with length l
        return arrayOfDifferentLengths[l].getsize();
    }

    //--------------------------------------------

    public void displayUniqueWords(){ //The method will display the unique words in the file sorted by the total occurrences of each word
        for (int i = 0; i < m; i++)
            System.out.println("(" + sortedArray[i].getWord() + ", " + sortedArray[i].size + ")");
    }

    //--------------------------------------------
    public LinkedList<WordOccurrence> occurrences(String w){

        LinkedList<WordOccurrence> temp = null;

        if ( arrayOfDifferentLengths[w.length()].getsize() > 0) {
            arrayOfDifferentLengths[w.length()].findFirst();

            while (!arrayOfDifferentLengths[w.length()].last()) {
                if (arrayOfDifferentLengths[w.length()].retrieve().getWord().equalsIgnoreCase(w))
                    temp = arrayOfDifferentLengths[w.length()].retrieve().getOccList();
                arrayOfDifferentLengths[w.length()].findNext();
            }
            if (arrayOfDifferentLengths[w.length()].retrieve().getWord().equalsIgnoreCase(w))
                temp = arrayOfDifferentLengths[w.length()].retrieve().getOccList();
        }
        return temp;
    }

    //--------------------------------------------

    public boolean checkAdjacent(String w1, String w2) {

        if (w1.equalsIgnoreCase(w2)){
            LinkedList<WordOccurrence> WordO = occurrences(w1);
            if (WordO != null  && !WordO.empty() ) {
                if (WordO.getsize() > 1) {
                    WordO.findFirst();
                    WordOccurrence word1Position = WordO.retrieve();
                    for (int i = 1; i < WordO.getsize(); i++) {
                        WordO.findNext();
                        WordOccurrence word2Position = WordO.retrieve();
                        int difference = word2Position.getPosition() - word1Position.getPosition();
                        if (word1Position.getLineNo() == word2Position.getLineNo() && (Math.abs(difference) == 1))
                            return true;
                        word1Position = word2Position;
                    }
                }
            }
            return false;
        }
        if ((arrayOfDifferentLengths[w2.length()].getsize() == 0 || arrayOfDifferentLengths[w1.length()].getsize() == 0))
            return false;

        LinkedList<WordOccurrence> word1 = occurrences (w1);
        LinkedList<WordOccurrence> word2 = occurrences (w2);

        if ( word1 != null && word2 != null ) {
            word1.findFirst();
            word2.findFirst();

            while (!word1.last() && !word2.last()) {
                int word1Line = word1.retrieve().getLineNo();
                int word2Line = word2.retrieve().getLineNo();

                if (word1Line == word2Line) {
                    int word1Position = word1.retrieve().getPosition();
                    int word2Position = word2.retrieve().getPosition();

                    int difference = word2Position-word1Position;

                    if (Math.abs(difference) == 1)
                        return true;

                    if (difference > 1)
                        word1.findNext();

                    else
                        word2.findNext();
                }
                else if (word1Line > word2Line)
                    word2.findNext();

                else
                    word1.findNext();
            }
            while (!word1.last() && word2.last()) {
                int word1Line = word1.retrieve().getLineNo();
                int word2Line = word2.retrieve().getLineNo();

                if (word1Line == word2Line) {
                    int word1Position = word1.retrieve().getPosition();
                    int word2Position = word2.retrieve().getPosition();

                    int difference = word2Position - word1Position;

                    if (Math.abs(difference) == 1)
                        return true;
                }
                word1.findNext();
            }
            while (word1.last() && !word2.last()) {
                int word1Line = word1.retrieve().getLineNo();
                int word2Line = word2.retrieve().getLineNo();

                if (word1Line == word2Line) {
                    int word1Position = word1.retrieve().getPosition();
                    int word2Position = word2.retrieve().getPosition();

                    int difference = word2Position - word1Position;

                    if (Math.abs(difference) == 1)
                        return true;
                }
                word2.findNext();
            }

            if (word1.last() && word2.last()) {
                int word1Line = word1.retrieve().getLineNo();
                int word2Line = word2.retrieve().getLineNo();

                if ( word1Line == word2Line) {
                    int word1Position = word1.retrieve().getPosition();
                    int word2Position = word2.retrieve().getPosition();

                    int difference = word2Position - word1Position;

                    return Math.abs(difference) == 1;
                }
            }
        }
        return false;
    }

    //--------------------------------------------
    private void mergesort (int l, int r){
        if (l >= r)
            return;
        int m= (l + r) / 2;
        mergesort (l , m) ;
        mergesort (m + 1 , r ) ;
        merge (l, m, r) ;
    }

    //--------------------------------------------
    private static void merge(int l, int m, int r){
        WordInformation [] B = new WordInformation [r-l+1];
        int i= l , j= m + 1 , k= 0;

        while ( i <= m && j <= r )
            if (sortedArray[i].size >= sortedArray[j].size)
                B [k++] = sortedArray[i++];
            else
                B [k++] = sortedArray[j++];

        if ( i > m )
            while ( j <= r )
                B[k++] = sortedArray[j++];
        else
            while ( i <= m )
                B[k++] = sortedArray[i++];

        for (k = 0; k < B.length; k++)
            sortedArray[k+l] = B[k];
    }
}
