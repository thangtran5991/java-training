public class FileList {
    private String fileName;
    private Boolean isRead;

    public FileList(String fileName, Boolean isRead) {
        this.fileName = fileName;
        this.isRead = isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public String getFileName() {
        return fileName;
    }

    public Boolean getIsRead() {
        return isRead;
    }
}
