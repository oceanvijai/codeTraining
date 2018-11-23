public class FileSystem {
    /**
     * Design an in-memory file system to simulate the following functions:
     * 
     * ls: Given a path in string format. If it is a file path, return a list that
     * only contains this file's name. If it is a directory path, return the list of
     * file and directory names in this directory. Your output (file and directory
     * names together) should in lexicographic order.
     * 
     * mkdir: Given a directory path that does not exist, you should make a new
     * directory according to the path. If the middle directories in the path don't
     * exist either, you should create them as well. This function has void return
     * type.
     * 
     * addContentToFile: Given a file path and file content in string format. If the
     * file doesn't exist, you need to create that file containing given content. If
     * the file already exists, you need to append given content to original
     * content. This function has void return type.
     * 
     * readContentFromFile: Given a file path, return its content in string format.
     */

    Dir root;

    public FileSystem() {
        root = new Dir();
    }

    public List<String> ls(String path) {
        Dir current = root;
        List<String> ans = new ArrayList<>();

        if (!path.equals("/")) {
            String[] d = path.split("/");
            for (int i = 1; i < d.length - 1; i++) {
                current = current.directories.get(d[i]);
            }

            // If the end is a file
            if (current.files.containsKey(d[d.length - 1])) {
                ans.add(d[d.length - 1]);
                return ans;
            } else {
                current = current.directories.get(d[d.length - 1]);
            }
        }

        ans.addAll(new ArrayList<>(current.directories.keySet()));
        ans.addAll(new ArrayList<>(current.files.keySet()));
        Collections.sort(ans);
        return ans;

    }

    public void mkdir(String path) {
        Dir current = root;
        String[] d = path.split("/");
        for (int i = 1; i < d.length; i++) {
            if (!current.directories.containsKey(d[i]))
                current.directories.put(d[i], new Dir());
            current = current.directories.get(d[i]);
        }
    }

    public void addContentToFile(String filePath, String content) {
        Dir current = root;
        String[] d = filePath.split("/");
        for (int i = 1; i < d.length - 1; i++) {
            current = current.directories.get(d[i]);
        }
        current.files.put(d[d.length - 1], current.files.getOrDefault(d[d.length - 1], "") + content);
    }

    public String readContentFromFile(String filePath) {
        Dir current = root;
        String[] d = filePath.split("/");
        for (int i = 1; i < d.length - 1; i++) {
            current = current.directories.get(d[i]);
        }
        return current.files.get(d[d.length - 1]);
    }

    private class Dir {
        Map<String, Dir> directories;
        Map<String, String> files;

        public Dir() {
            directories = new HashMap<>();
            files = new HashMap<>();
        }
    }
}