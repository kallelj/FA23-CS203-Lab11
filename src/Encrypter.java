import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Encrypter {

    private int shift;
    private String encrypted;

    /**
     * Default Constructor
     */
    public Encrypter() {
        this.shift = 4;
        this.encrypted = "";
    }

    /**
     * Non-default Constructor
     * @param s - custom shift amount
     */
    public Encrypter(int s) {
       if (s >= 1 && s <= 10) {
       this.shift = s;
        this.encrypted = "";
       }
       else {
    	   this.shift = 1;
       }
    }


    /**
     * Encrypts the content of a file and writes the result to another file.
     *
     * @param inputFilePath      the path to the file containing the text to be encrypted
     * @param encryptedFilePath the path to the file where the encrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void encrypt(String inputFilePath, String encryptedFilePath) throws Exception {
    	String text = readFile(inputFilePath);
    	String encryptedText = caesarEncrypt(text, shift);
    	
    	writeFile(encryptedText, encryptedFilePath);
        //TODO: Call the read method, encrypt the file contents, and then write to new file
    }

    /**
     * Decrypts the content of an encrypted file and writes the result to another file.
     *
     * @param messageFilePath    the path to the file containing the encrypted text
     * @param decryptedFilePath the path to the file where the decrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void decrypt(String messageFilePath, String decryptedFilePath) throws Exception {
    	String text = readFile(messageFilePath);
    	String decryptedText = caesarDecrypt(text, shift);
    	
    	writeFile(decryptedText, decryptedFilePath);
        //TODO: Call the read method, decrypt the file contents, and then write to new file
    }

    /**
     * Reads the content of a file and returns it as a string.
     *
     * @param filePath the path to the file to be read
     * @return the content of the file as a string
     * @throws Exception if an error occurs while reading the file
     */
    private static String readFile(String filePath) throws IOException {
        File oldFile = new File(filePath);
        
    	StringBuilder message = new StringBuilder();
    	
    	try (Scanner scanner = new Scanner(oldFile)) {
    		while (scanner.hasNextLine()) {
    			message.append(scanner.nextLine()).append("\n");
    		}
    	}
        //TODO: Read file from filePath
        return message.toString();
    }

    /**
     * Writes data to a file.
     *
     * @param data     the data to be written to the file
     * @param filePath the path to the file where the data will be written
     */
    private static void writeFile(String data, String filePath) throws IOException {
        //TODO: Write to filePath
    	try (FileWriter newFile = new FileWriter(filePath)) {
    		newFile.write(data);
    	}
    }
    private static String caesarEncrypt(String text, int shift) {
        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);

            
            if (Character.isUpperCase(currentChar)) {
                char encryptedChar = (char) ((currentChar + shift - 'A') % 26 + 'A');
                encryptedText.append(encryptedChar);
            }
            
            else if (Character.isLowerCase(currentChar)) {
                char encryptedChar = (char) ((currentChar + shift - 'a') % 26 + 'a');
                encryptedText.append(encryptedChar);
            }
            
            else {
                encryptedText.append(currentChar);
            }
        }

        return encryptedText.toString();
    }

    private static String caesarDecrypt(String text, int shift) {
        StringBuilder decryptedText = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);

            
            if (Character.isUpperCase(currentChar)) {
                char decryptedChar = (char) ((currentChar - shift - 'A') % 26 + 'A');
                decryptedText.append(decryptedChar);
            }
            
            else if (Character.isLowerCase(currentChar)) {
                char decryptedChar = (char) ((currentChar - shift - 'a') % 26 + 'a');
                decryptedText.append(decryptedChar);
            }
            
            else {
                decryptedText.append(currentChar);
            }
        }

        return decryptedText.toString();
    }




    /**
     * Returns a string representation of the encrypted text.
     *
     * @return the encrypted text
     */
    @Override
    public String toString() {
        return encrypted;
    }
}
