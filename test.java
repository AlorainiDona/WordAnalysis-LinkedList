import java.util.Scanner;
public class test {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("please enter the text file name:");
        String file = input.nextLine();
        WordAnalysis_ADT wodAnalysis = new WordAnalysis_ADT(file);

        System.out.println("please select an operation:");
        System.out.println("1: An operation to determine the total number of words in a text file.\n2: An operation to determine the total number of unique words in a text file.\n3: An operation to determine the total number of occurrences of a particular word.\n4: An operation to determine the total number of words with a particular length.\n5: An operation to display the unique words and their occurrences sorted by the total occurrences of each word (from the most frequent to the least).\n6: An operation to display the locations of the occurrences of a word starting from the top of the text file.\n7: An operation to examine if two words are occurring adjacent to each other in the file.");
        int choice = input.nextInt();
try {
    switch (choice) {
        case 1:
            System.out.println("The output of operation (1) would be " + wodAnalysis.documentLength());
            break;
        case 2:
            System.out.println("The output of operation (2) would be " + wodAnalysis.uniqueWords());
            break;
        case 3:
            System.out.println("enter the word you want to check it's occurrences:");
            String word = input.next();
            System.out.println("The output of operation (3) for the word ‘" + word +"' would be " + wodAnalysis.totalWord(word));
            break;
        case 4:
            System.out.println("enter the length you want to find the words that have the same length:");
            int length = input.nextInt();
            System.out.println("The output of operation (4) for word length " + length + " would be " + wodAnalysis.totalWordsForLength(length));
            break;
        case 5:
            System.out.println("The output of operation (5) would be ");
            wodAnalysis.displayUniqueWords();
            break;
        case 6:
            System.out.println("enter the word you want to view it's locations:");
            String wordLoc = input.next();
            System.out.print("The output of operation (6) for the word '" + wordLoc +"' would be: ");
            LinkedList<WordOccurrence> O = wodAnalysis.occurrences(wordLoc);
            if (!O.empty()) {
                O.findFirst();
                while (!O.last()) {
                    System.out.print(O.retrieve().toString() + "  ");
                    O.findNext();
                }
                System.out.print(O.retrieve().toString() + "  ");
            }
            break;
        case 7:
            System.out.println("enter two words two check if they're adjacent:");
            System.out.print("Word 1: ");
            String word1 = input.next();
            System.out.print("\nWord 2: ");
            String word2 = input.next();
            System.out.println("");
            System.out.println("The output of operation (7) for the two words '" + word1 + "' and '" + word2 + "' would be " + wodAnalysis.checkAdjacent(word1, word2));
            break;
        default:
            System.out.println("wrong input number.");
    }
}catch (Exception e){
    System.out.println(" can't be generated because you entered a wrong input.");
}

    }

}
