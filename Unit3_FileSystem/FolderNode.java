import java.nio.file.FileSystem;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a directory in the file system tree. A directory can contain other directories and
 * files as children.
 */
public class FolderNode extends FileSystemNode {

    private List<FileSystemNode> children;

    public FolderNode(String name, FolderNode parent) {
        super(name, parent);
        this.children = new ArrayList<>();
    }


    @Override
    public boolean isFolder() {
        return true;
    }

    /**
     * Returns a list view of the children contained directly inside this directory. Modifying the
     * returned list is not required to be supported.
     */
    public List<FileSystemNode> getChildren() {
        return children;
    }

    /**
     * Searches the children of this directory for a node whose name matches the input. Only direct
     * children are considered, not deeper descendants.
     */
    public FileSystemNode getChildByName(String childName) {
        FileSystemNode ret = null;
        for (int i = 0; i < children.size(); i++) {
            ret = children.get(i);
            if (ret.getName().equals(childName)) {
                return ret;
            }

        }
        return ret;
    }

    /**
     * Creates a new file directly inside this directory with the given name and size. If a child
     * with the same name already exists, no file is created and false is returned. Otherwise the
     * new file is added and true is returned.
     */
    public boolean addFile(String fileName, int size) {
        if (getChildByName(fileName) == null) {
            return false;
        }
        children.add(new FileNode(fileName, this, size));
        return true;
    }

    /**
     * Creates a new subdirectory directly inside this directory with the given name. If a child
     * with the same name already exists, no folder is created and false is returned. Otherwise the
     * new folder is added and true is returned.
     */
    public boolean addFolder(String folderName) {
        if (getChildByName(folderName) == null) {
            return false;
        }
        children.add(new FolderNode(folderName, this));
        return true;
    }

    /**
     * Searches this directory and all of its descendants for nodes whose name matches the input.
     * When a match is found, its full path can be printed by the caller using toString().
     */
    public boolean containsNameRecursive(String searchName) {
        for (int i = 0; i < children.size(); i++) {
            if (!children.get(i).isFolder()) {
                if (children.get(i).getName().equals(searchName)) {
                    return true;
                }
            } else {
                FolderNode temp = (FolderNode)(children.get(i));
                if (temp.containsNameRecursive(searchName)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int getHeight() {
        int temp = 0;
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i).isFolder()) {
                if (children.get(i).getHeight() >= temp) {
                    temp = children.get(i).getHeight();
                }
            }
        }
        return temp + 1;
    }

    @Override
    public int getSize() {
        int size = 0;
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i).isFolder()) {
                FolderNode temp = (FolderNode)(children.get(i));
                size += temp.getSize();
            } else {
                size += children.get(i).getSize();
            }
        }
        return size;

    }

    @Override
    public int getTotalNodeCount() {
        int count = 0;
        for (int i = 0; i < children.size(); i++) {
            if (!children.get(i).isFolder()) {
                count++;
            } else {
                FolderNode temp = (FolderNode)(children.get(i));
                count += temp.getTotalNodeCount();
            }
        }
        return count;
    }
}
