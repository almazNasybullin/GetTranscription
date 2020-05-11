import URL.GetDataFromUrl;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
//        String a = "Ability";
//        String transcription = GetDataFromUrl.GetDataFromUrl(a);
//        System.out.println(transcription);
//        writeTxt(transcription);
//        readTxt();
        String trns;
        String str = readStr();
//        System.out.println(GetDataFromUrl.GetDataFromUrl(str));
        String[] subString = str.split("\\n"); // разбиваю текст на строки
        for (String subStr:subString) {
            if (subStr.length() > 2){
                String temp = "";
//                System.out.println(subStr);

                String[] word = subStr.split("\\s"); //разбиваю строку на слова
//                System.out.println(word.length);


//                System.out.print("[");
                temp = temp + "[";
                for (String subWord:word){
                    if (subWord.length() > 2){
//                        System.out.print(subWord);
                        trns = GetDataFromUrl.GetDataFromUrl(subWord);
                        temp = temp + trns + " ";
//                        System.out.print(trns + " ");
                    }

                }
                temp = temp.substring(0, temp.length() -1 );
//                StringUtils.chop(temp);
                temp = temp + "]\n";
//                System.out.println(temp);
//                System.out.println("]");
                writeTxt(temp);
            }
        }
    }

    private static String readStr() throws Exception {
        String fileName = "src/Words.txt";

        // читаем файл с помощью Scanner
        String contents;
//        contents = readUsingScanner(fileName);

        // считываем содержимое файла в String с помощью BufferedReader
        contents = readUsingBufferedReader(fileName);
//        System.out.println(contents);
//        System.out.println(contents.length());
        return contents;
    }

    // считываем содержимое файла в String с помощью BufferedReader
    private static String readUsingBufferedReader(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader( new FileReader (fileName));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");
        while( ( line = reader.readLine() ) != null ) {
            stringBuilder.append( line );
            stringBuilder.append( ls );
        }

        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return stringBuilder.toString();
    }

    // читаем файл с помощью Scanner
    private static String readUsingScanner(String fileName) throws IOException {
        Scanner scanner = new Scanner(Paths.get(fileName), StandardCharsets.UTF_8.name());
        //здесь мы можем использовать разделитель, например: "\\A", "\\Z" или "\\z"
        String data = scanner.useDelimiter("\\r").next();
        scanner.close();
        return data;
    }

    private static void writeTxt (String transcription){
        String filePath = "test.txt";

        try {
            Files.write(Paths.get(filePath), transcription.getBytes(), StandardOpenOption.APPEND);
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    private static void readTxt () {
        try(FileReader reader = new FileReader("src/Words.txt"))
        {
            char[] buf = new char[256];
            int c;
            while((c = reader.read(buf))>0){

                if(c < 256){
                    buf = Arrays.copyOf(buf, c);
                }
//                return (String) buf;
                System.out.print(buf);
//                String[] words = buf.split()
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }


//    private static void writeXml (String transcription){
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder builder;
//        try {
//            builder = factory.newDocumentBuilder();
//            // создаем пустой объект Document, в котором будем
//            // создавать наш xml-файл
//
//            Document doc = (Document) builder.newDocument();
//
//            Element rootElement =
//                    doc.createElementNS("https://javadevblog.com/language", "Languages");
//            // добавляем корневой элемент в объект Document
//            doc.appendChild(rootElement);
//
//            // добавляем первый дочерний элемент к корневому
//            rootElement.appendChild(getLanguage(doc, "1", "Java", "21"));
//
//            //добавляем второй дочерний элемент к корневому
//            rootElement.appendChild(getLanguage(doc, "2", "C", "44"));
//
//            //создаем объект TransformerFactory для печати в консоль
//            TransformerFactory transformerFactory = TransformerFactory.newInstance();
//            Transformer transformer = transformerFactory.newTransformer();
//            // для красивого вывода в консоль
//            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//            DOMSource source = new DOMSource(doc);
//
//            //печатаем в консоль или файл
//            StreamResult console = new StreamResult(System.out);
//            StreamResult file = new StreamResult(new File("/Users/prologistic/languages.xml"));
//
//            //записываем данные
//            transformer.transform(source, console);
//            transformer.transform(source, file);
//            System.out.println("Создание XML файла закончено");
//
//        }
//        catch (Exception ex){
//            System.out.println(ex.getMessage());
//        }
//    }
}
