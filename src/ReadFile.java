import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadFile implements Callable<List<String>> {
    FileList fileLists;

    public ReadFile(FileList fileList) {
        this.fileLists = fileList;
    }

    @Override
    public List<String> call() throws Exception {
        String fileName = fileLists.getFileName();
        List<String> list = new ArrayList<>();
        synchronized (fileLists) {
            if (fileLists.getIsRead() == false) {
                fileName = fileLists.getFileName();
                fileLists.setIsRead(false);
            }
        }
        try(Stream<String> stream = Files.lines(Paths.get(fileName))) {
            list = stream.skip(1).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}
