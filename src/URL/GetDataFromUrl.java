package URL;
import java.net.*;
import java.io.*;

public class GetDataFromUrl {


    public static String  GetDataFromUrl(String word) throws Exception {
        try {

            URL wordHunt = new URL("https://wooordhunt.ru/word/" + word.toLowerCase());
            URLConnection yc = wordHunt.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine;
            int indexUK;
            while ((inputLine = in.readLine()) != null)
                if (inputLine.contains("|")) {
                    indexUK = inputLine.indexOf("британская транскрипция слова ");
                    if (indexUK != -1) {
                        inputLine = inputLine.substring(indexUK + word.length() + 55, indexUK + word.length() + 75);
                        int index1 = inputLine.indexOf("|");
                        int index2 = inputLine.lastIndexOf("|");
                        try {
                            if (inputLine.substring(index1 + 1, index2).length() > 2) {
//                            System.out.println(inputLine.substring(index1 + 1, index2));
//                            System.out.println(index1 + " - " + index2);
                                return inputLine.substring(index1 + 1, index2);
                            }
                        }
                        catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }


                    }
                }
            in.close();
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return "null";
    }
}
