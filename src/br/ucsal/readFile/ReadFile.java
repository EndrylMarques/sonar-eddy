package br.ucsal.readFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ReadFile {

    public static void main(String[] args) throws FileNotFoundException {
        getFolder("");
    }

    public static void getFolder(String dirPath) {
        try(Stream<Path> paths = Files.walk(Paths.get(dirPath))){
           paths.filter(Files::isRegularFile)
                   .forEach(x -> {
                       try {
                           getFile(x.toFile());
                       } catch (FileNotFoundException e) {
                           e.printStackTrace();
                       }
                   });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getFile(File file) throws FileNotFoundException {
        System.out.println(file.getName());
        BufferedReader codigo = new BufferedReader(new FileReader(file));
        int lines = 0, methods = 0, classes = 0;

            try {
              while(codigo.ready()){
                    String line = codigo.readLine();
                    line = line.replaceAll("^\\s+", "");
                    line = line.replaceAll("\\s+", "");
                    lines++;
                    if (countClass(line))
                        classes++;
                    if(countMethod(line))
                        methods++;
                }

                System.out.println("Linhas: " + lines);
                System.out.println("Classes : " + classes);
                System.out.println("Metodos : " + methods);
                codigo .close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    public static boolean countClass(String line){
        if(line.startsWith("class") || line.startsWith("public class") || line.startsWith("protected class") || line.startsWith("private class")){
            return true;
        }
        return false;
    };

    public static boolean countMethod (String line){
        if((line.startsWith("public") || line.startsWith("protected") || line.startsWith("private")) && line.endsWith("){")){
            return true;
        }
        return false;
    }
}
