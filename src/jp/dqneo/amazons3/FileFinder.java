package jp.dqneo.amazons3;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class FileFinder {


    /**
    *  holds all files' absolute path
    */
    private static ArrayList<File> files = new ArrayList<File>();

    /**
    * find files recursively
    *
    * @param path directory
    * @return ArrayList<File> file list
    */
    public static  ArrayList<File> find(File dir) {
        _find(dir.getPath());
        return files;
    }

    /**
    * find files recursively
    *
    * @param path directory
    * @return bool isFile
    */
    private static boolean _find(String path) {
        File file = new File(path);

        File[] listFiles = file.listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                // exclude dot files
                if (name.startsWith(".")) {
                    return false;
                }

                String absolutePath = dir.getAbsolutePath()
                                        + File.separator + name;
                if (new File(absolutePath).isFile()) {
                    return true;
                }
                else {
                    // recursive call
                    return _find(absolutePath);
                }
            }
        });

        for (File f : listFiles) {
            if (f.isFile()) {
                files.add(f);
            }
        }
        return true;
    }
}