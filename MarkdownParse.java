//https://howtodoinjava.com/java/io/java-read-file-to-string-examples/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then read link upto next )
        int newLinkIndex = 0;
        while(newLinkIndex < markdown.length()) {
            int openBracket = markdown.indexOf("[", newLinkIndex);
            
            int indexBeforeOpenBracket = openBracket - 1;

            if(openBracket < 0){
                break;
            }

            int closeBracket = markdown.indexOf("]", openBracket);

            if (closeBracket == openBracket + 1) {
                newLinkIndex = closeBracket + 1;
                continue;
            }

            if(closeBracket < 0){
                break;
            }

            int openParen = markdown.indexOf("(", closeBracket);

            if(openParen < 0){
                break;
            }

            if(openParen != closeBracket+1){
                newLinkIndex = closeBracket;
                continue;
            }

            int closeParen = markdown.indexOf(")", openParen);
            
            if(closeParen < 0){
                break;
            }

            if (indexBeforeOpenBracket > 0  && markdown.charAt(indexBeforeOpenBracket) != '!') {
                toReturn.add(markdown.substring(openParen + 1, closeParen));
            }

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