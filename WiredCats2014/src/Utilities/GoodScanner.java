package Utilities;

import java.io.InputStream;
import javax.microedition.io.InputConnection;
import javax.microedition.io.Connector;
import java.io.IOException;

/**
 * Homebrew Scanner class that can read from a character stream.
 *
 * @author BruceCrane
 */
public class GoodScanner {

    public static final char END_CHARACTER = '#';
    private boolean endOfFile = false;
    InputStream input;

    public GoodScanner(String fileName) {
        try {
            InputConnection connection = (InputConnection) Connector.open("file:///" + fileName, Connector.READ);
            input = connection.openDataInputStream();
        } catch (IOException e) {
            System.out.println("Hi, this is Sam Crane with the error report:");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /* 
     * Returns the next word in the stream of data. 
     * A quirk to this method is that if it finds the specified "halt" value, '#'
     */
    public String next() {
        String s = "";

        try {
            char read = (char) input.read();
            while (read == ' ' || read == '\n') {
                read = (char) input.read();
                if (read == END_CHARACTER) {
                    endOfFile = true;
                    return s; //if the final character is found, then return this.
                }
            }
            while (read != ' ' && read != ',' && read != '\n') {
                s += read;
                read = (char) input.read();
                if (read == END_CHARACTER) {
                    endOfFile = true;
                    return s; //if the final character is found, then return this.
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("IOException: the file is not found");
        }
        return s;
    }
    
    /*
     * Since EOF and hasNext are logical opposites, we can define it as such
     * The last piece of information will end in '#' to notify end of file
     */
    public boolean hasNext() { return !endOfFile; }

    /**
     * Always close your file when you're done using it. This is important.
     * Don't exactly know why, just go with it though.
     */
    public void close() {
        try {
            input.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("IOException: It couldn't close the file, which I personally do not understand ever happening.");
        }
    }
}
