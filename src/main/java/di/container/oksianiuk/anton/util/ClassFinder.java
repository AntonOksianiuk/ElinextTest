package di.container.oksianiuk.anton.util;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClassFinder {

    private static final String packageToScan = "di.container.oksianiuk.anton";

    private static final char PKG_SEPARATOR = '.';

    private static final char DIR_SEPARATOR = '/';

    private static final String CLASS_FILE_SUFFIX = ".class";

    private static final String BAD_PACKAGE_ERROR = "Unable to get resources from path '%s'. Are you sure the package '%s' exists?";

    private static List<Class<?>> find(String scannedPackage) {
        String scannedPath = scannedPackage.replace(PKG_SEPARATOR, DIR_SEPARATOR);
        URL scannedUrl = Thread.currentThread().getContextClassLoader().getResource(scannedPath);

        if (scannedUrl == null) {
            throw new IllegalArgumentException(String.format(BAD_PACKAGE_ERROR, scannedPath, scannedPackage));
        }

        File scannedDir = new File(scannedUrl.getFile());
        List<Class<?>> classes = new ArrayList<>();

        for (File file : scannedDir.listFiles()) {
            classes.addAll(find(file, scannedPackage));
        }
        return classes;
    }

    private static List<Class<?>> find(File file, String scannedPackage) {
        List<Class<?>> classes = new ArrayList<>();
        String resource = scannedPackage + PKG_SEPARATOR + file.getName();
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                classes.addAll(find(child, resource));
            }
        } else if (resource.endsWith(CLASS_FILE_SUFFIX)) {
            int endIndex = resource.length() - CLASS_FILE_SUFFIX.length();
            String className = resource.substring(0, endIndex);
            try {
                classes.add(Class.forName(className));
            } catch (ClassNotFoundException ignore) {
            }
        }
        return classes;
    }


    //A function that returns implementations
    public static <T> Set<Class<? extends T>> getSubTypesOf(Class<T> interfaceClass) {
        List<Class<?>> allTheClass = ClassFinder.find(packageToScan);

        Set<Class<? extends T>> set = new HashSet<>();

        //Bulkhead of all classes

        for (Class claz : allTheClass) {
            if (claz.getInterfaces().length > 0) {

                //Checking interfaces in a class
                for (Class classs : claz.getInterfaces()) {
                    if (interfaceClass.equals(classs)) {
                        set.add(claz);
                    }
                }

            }
        }

        return set;
    }
}
