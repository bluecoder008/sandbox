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

            if ( count + len <= width ) {
                count += len;
                wordList.add(word);
            } else {
                String[] spaces = computeSpaces(wordList, width);
                System.out.print("'");
                for(int n = 0; n < wordList.size(); n++) {
                    if ( n > 0 ) {
                        System.out.print(spaces[n-1]);
                    }
                    System.out.print(wordList.get(n));
                }
                System.out.println("'");
                count = word.length();
                wordList.clear();
                wordList.add(word);
            }
        }
     System.out.print("'");
     for(int n = 0; n < wordList.size(); n++) {
         if ( n > 0 ) {
             System.out.print(" ");
         }
         System.out.print(wordList.get(n));
      }
      System.out.println("'");
    }

    private String[] computeSpaces(List<String> words,
                                   int width)
    {
        String[] spaces = new String[words.size() - 1];

        if ( words.size() == 1 ) {
            return spaces;
        }

        for(int n=0; n < spaces.length; n++) {
            spaces[n] = new String();
        }

        int sum=0;
        for(int n = 0; n < words.size(); n++) {
            sum += words.get(n).length();
        }
        int numSpaces = width - sum;
        while( numSpaces > 0 ) {
            for(int n = 0; n < spaces.length && (numSpaces > 0); n++) {
                spaces[n] = spaces[n] + " ";
                numSpaces--;
            }
        }
        // for(String space : spaces) {
        //    System.out.println("space => '" + space + "'" );
        // }
        return spaces;
    }


    public static void main(String[] args)
    {
        StringBuilder sb = new StringBuilder();
        String str_Text = null;

        int num = Integer.parseInt(args[0]);

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

            just.justify(num, str_Text);

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
