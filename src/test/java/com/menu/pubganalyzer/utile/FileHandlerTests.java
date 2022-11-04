package com.menu.pubganalyzer.utile;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class FileHandlerTests {
    @Test
    public void getFileToString() {
        String path = "D:\\java\\wdtmk\\src\\test\\java\\com\\menu\\wdtmk";
        FileHandler file = new FileHandler(path);
        try {
            assertThat(file.readToString("filereadtest.txt")).isEqualTo("File Read.To String.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void fileNotFound() {
        FileHandler file = new FileHandler("D:\\java\\wdtmk\\src\\test\\java\\com\\menu\\wdtmk");
        assertThrows(FileNotFoundException.class, () -> {
            file.readToString("NotFound.txt");
        });
    }
    @Test
    public void writeFile() {
        String path = "D:\\java\\wdtmk\\src\\test\\java\\com\\menu\\wdtmk";
        String filename = "filewritetest.txt";
        String text = "File Write.";

        FileHandler file = new FileHandler(path);
        file.save(filename, text);

        try {
            assertThat(file.isFile(filename)).isEqualTo(true);
            assertThat(file.readToString(filename)).isEqualTo(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
