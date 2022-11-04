package com.menu.pubganalyzer.utile;

import java.io.*;

public class FileHandler {
    // constructor  ( String path )
    private String path;
    public FileHandler(String path) {
        this.path = path;
    }
    // Read a file and return String type.
    // function param ( String filename )
    public String readToString(String filename) throws FileNotFoundException{
        StringBuilder toReturn = new StringBuilder();
        try(
                FileReader fr = new FileReader(this.path + "\\" + filename);
                BufferedReader br = new BufferedReader(fr);
        ) {
            String readLine = null;
            while ((readLine = br.readLine()) != null) {
                toReturn.append(readLine);
            }
        } catch (FileNotFoundException e){
            System.out.println("파일없음");
            throw e;
        } catch (IOException e) {
            System.out.println("버퍼리더 에러");
            e.printStackTrace();
        }
        return toReturn.toString();
    }
    // Recive String type and write a file.
    public void save(String filename, String text) {
        File file = new File(this.path);
        if (!file.exists()) {
            file.mkdir();
        }
        try(
                FileWriter fw = new FileWriter(this.path + "\\" + filename);
                BufferedWriter bw = new BufferedWriter(fw);
        ) {
            bw.write(text);
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isFile(String filename) {
        File file = new File(this.path + "\\" + filename);
        return file.isFile();
    }
}
