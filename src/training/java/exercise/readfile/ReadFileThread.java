/*
 * ReadFile
 *
 * Version 1.0
 *
 * 12/14/2020
 *
 * Copyright thang-tran
 */
package training.java.exercise.readfile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadFileThread implements Callable<List<String>> {
    FileInfo fileInfo;

    public ReadFileThread(FileInfo fileInfo) {
        this.fileInfo = fileInfo;
    }

    @Override
    public List<String> call() {
        String fileName = fileInfo.getFileName();
        List<String> list = new ArrayList<>();

        // Synchronized block code to read multifile by muti thread
        synchronized (fileInfo) {
            if (fileInfo.isRead() == false) {
                fileName = fileInfo.getFileName();
                fileInfo.setIsRead(false);
            }
        }
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            list = stream.skip(1).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}
