package seedu.address.model.currentpath;

public class CurrentPath {

    private static CurrentPath currentPath;
    private String address;

    private CurrentPath() {
        this.address = System.getProperty("user.dir");
    }

    public static CurrentPath getInstance() {
        if (currentPath == null) {
            currentPath = new CurrentPath();
        }

        return currentPath;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
