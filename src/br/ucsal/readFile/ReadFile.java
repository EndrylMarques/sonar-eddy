package br.ucsal.readFile;

import java.io.File;

public class ReadFile {

    public static void main(String[] args) {
        getFile("C:\\leitor");
    }



    private static void getFile(String dirPath) {
        int count = 0;
        File f = new File(dirPath);
        File[] files = f.listFiles();

        if (files != null)
            for (int i = 0; i < files.length; i++) {
                count++;
                File file = files[i];

                if (file.isDirectory()) {
                    getFile(file.getAbsolutePath());
                }
                System.out.println(file.getName());
            }
    }
}
