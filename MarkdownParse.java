//https://howtodoinjava.com/java/io/java-read-file-to-string-examples/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // Find the next [, then find the ], then find the (, then read link 
        // upto next )
        int newLinkIndex = 0;
        // Check each index of the Markdown file until it reaches the end
        int openBracket = markdown.indexOf("[", newLinkIndex);
        if (openBracket < 0) {
            toReturn.add("There is no parenthesis");
        }

        while(newLinkIndex < markdown.length()) {
            // Assigns the index of the [, returns -1 if doesn't exist
            openBracket = markdown.indexOf("[", newLinkIndex);
            
            // Stores index preceding [ for later check
            int indexBeforeOpenBracket = openBracket - 1;
            if (indexBeforeOpenBracket < 0) {
                // Assigns that index to 0 openBracket = 0
                indexBeforeOpenBracket = 0;
            }

            // Terminate if [ doesn't exist because implies links don't exist
            if(openBracket < 0){
                break;
            }

            // Checks for ] proceeds the [
            int closeBracket = markdown.indexOf("]", openBracket);

            // If ] immediately follows [, that implies no link, so continue
            if (closeBracket == openBracket + 1) {
                newLinkIndex = closeBracket + 1;
                continue;
            }

            // If ] doesn't exist, then terminate
            if(closeBracket < 0){
                break;
            }

            // Search for ( proceeding ]
            int openParen = markdown.indexOf("(", closeBracket);

            // If ] doesn't exist, terminate
            if(openParen < 0){
                break;
            }

            // If ( doesn't immediately close the gap between ](, then link is not paired with [], then restart the search for a link using the index of ]
            if(openParen != closeBracket+1){
                newLinkIndex = closeBracket;
                continue;
            }

            // Seek for ) following (
            int closeParen = markdown.indexOf(")", openParen);

            // If doesn't exist, then termiante
            if(closeParen < 0){
                break;
            }

            // Check to see if char at index preceding [ was a !, because then that is an image link and not url
            if (markdown.charAt(indexBeforeOpenBracket) != '!') {
                // Only add if link was url and not image
                toReturn.add(markdown.substring(openParen + 1, closeParen));
            }

            // Start search from next index
            newLinkIndex = closeParen + 1;

        }

        return toReturn;
    }



    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
        String content = Files.readString(fileName);
        ArrayList<String> links = getLinks(content);
	    System.out.println(links);
    }
}