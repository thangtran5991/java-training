/*
 * FileList
 *
 * Version 1.0
 *
 * 12/14/2020
 *
 * Copyright thang-tran
 */
package training.java.exercise.readfile;

public class FileInfo {
    private String fileName;
    private Boolean isRead;

    public FileInfo(String fileName, Boolean isRead) {
        this.fileName = fileName;
        this.isRead = isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public String getFileName() {
        return fileName;
    }

    public Boolean isRead() {
        return isRead;
    }
}
