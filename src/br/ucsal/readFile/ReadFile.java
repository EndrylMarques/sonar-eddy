package br.ucsal.readFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {

    public static void main(String[] args) throws FileNotFoundException {
        getFile("");
    }



    private static void getFile(String dirPath) throws FileNotFoundException {
        BufferedReader codigo = new BufferedReader(new FileReader(dirPath));
        int lines = 0, methods = 0, classes = 0;

            try {
              while(codigo .ready()){
                    String line = codigo.readLine();
                    line = line.replaceAll("^\\s+", "");
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
