import java.io.*;
import java.util.*;

class Justifier 
{
    public void justify(int width, String text)
    {
        StringTokenizer stknz = new StringTokenizer(text);
        int count = 0;
        List<String> wordList = new ArrayList<String>();
   
        while( stknz.hasMoreElements()  ) {

            String word = stknz.nextToken();
            int len = ( count == 0 ? word.length() : 1 + word.length() );

            if ( count + len > width ) {

                printOneLine( wordList, width, false);
                count = 0;
                len--;
                wordList.clear();
            }

            count += len;
            wordList.add(word);
        }
        printOneLine( wordList, width, true);
    }

    private String[] computeSpaces(List<String> words,
                                   int width,
                                   boolean singlespace
                                   )
    {
        String[] spaces = new String[words.size()];
        for(int n=0; n < spaces.length; n++) {
            spaces[n] = new String();
        }

        int sum=0;
        for(int n = 0; n < words.size(); n++) {
            sum += words.get(n).length();
        }
        int numSpaces = width - sum;
        for(int n = 1; n < spaces.length && (numSpaces > 0); n++) {
            if ( singlespace ) {
               spaces[n] = " ";
            } else {
               spaces[n] = spaces[n] + " ";
            }
            numSpaces--;
        }
        return spaces;
    }

    private void printOneLine(List<String> words, 
                                        int width,
                                    boolean singlespace)
    {
        String[] spaces = computeSpaces(words, width, singlespace);
        System.out.print("'");
        for(int n = 0; n < words.size(); n++) {
            System.out.print(spaces[n]);
            System.out.print(words.get(n));
        }
        System.out.println("'");
    }


    public static void main(String[] args)
    {
        StringBuilder sb = new StringBuilder();
        String str_Text = null;

        int lineWidth = Integer.parseInt(args[0]);

        String fileName = args[1];

        try {
            BufferedReader reader = new BufferedReader(
                                        new FileReader(
                                            new File(fileName)));
            String line;
            while( (line = reader.readLine() ) != null ) {
                sb.append(line); 
            } 
  
            str_Text = sb.toString();
            System.out.println("str_Text: '" + str_Text + "'");

            Justifier just = new Justifier();

            just.justify(lineWidth, str_Text);

        } catch (java.io.FileNotFoundException ex) {
                // This should not happen
                System.err.println("File: " + fileName + " not found");
                System.exit(-1);
        } catch (IOException ex) {
                System.err.println("IOException occurred: " + ex);
                System.exit(-1);
        }
    }
}
