package com.xylon.utils.worker;

import java.io.*;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

/**
 * @author: zhangning
 * @Date: Jun 23, 2011
 * @Time: 5:37:28 PM
 * @Project: worker
 */
public class SkucostWorker {

    public static final String CONF_FILE = "app.conf";
    public static final String PATH_COMMON = "common";
    public static final String PATH_APP = "lib";
    public static final String PATH_CLASSES = "classes";
    public static final String MAIN_CLASS = "app.mainclassdaterange";
    public static final String START_DATE = "app.startDate";
    public static final String END_DATE = "app.endDate";

    public static void main(String[] args) {
        try {
            Properties prop = new Properties();
            File confFile = new File(CONF_FILE);
            InputStream in = new FileInputStream(confFile);
            prop.load(in);
            try {
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            String mainCLass = prop.getProperty(MAIN_CLASS);
            if (mainCLass != null && !"".equals(mainCLass.trim())) {
            	args = new String[]{prop.getProperty(START_DATE),prop.getProperty(END_DATE)};
                runMainClass(mainCLass.trim(), args);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void runMainClass(String className, String[] args) throws Exception {
        ClassLoader appClassLoader = createAppClassLoader(new File("").getAbsoluteFile().getParent());
        Class cls = appClassLoader.loadClass(className);
        Method m = cls.getMethod("main", (new String[0]).getClass());
        m.invoke(cls, ((Object) args));
    }

    private static ClassLoader createAppClassLoader(String path) throws Exception {
        System.out.println("App path:" + path);

        List<URL> urls = new ArrayList<URL>();
        urls.add(new URL("file", null, "../" + PATH_CLASSES + "/"));
        urls.addAll(findPathJarUrls(new File(PATH_COMMON).getCanonicalPath()));
        urls.addAll(findPathJarUrls(new File(path, PATH_APP).getCanonicalPath()));

        URL[] us = urls.toArray(new URL[0]);
        System.out.println("App class path:");
        for (URL url : us) {
            System.out.println(url);
        }

        URLClassLoader loader = new URLClassLoader(us, Thread.currentThread().getContextClassLoader());
        Thread.currentThread().setContextClassLoader(loader);
        return loader;
    }

    private static List<URL> findPathJarUrls(String path) throws Exception {
        List<File> jarList = new ArrayList<File>();
        getClassPath(jarList, new JarFilter(), new File(path));

        List<URL> urlList = new ArrayList<URL>();
        for (int i = 0; i < jarList.size(); ++i) {
            urlList.add(new URL("file", null, jarList.get(i).getCanonicalPath()));
        }
        return urlList;
    }

    private static void getClassPath(List<File> list, JarFilter filter, File f) {
        if (f.exists() && f.isDirectory()) {
            File[] ss = f.listFiles(filter);
            for (int i = 0; i < ss.length; ++i) {
                if (ss[i].isFile()) {
                    list.add(ss[i]);
                } else if (ss[i].isDirectory()) {
                    getClassPath(list, filter, ss[i]);
                }
            }
        }
    }

    private static class JarFilter implements FilenameFilter {

        public boolean accept(File dir, String name) {
            File f = new File(dir, name);
            boolean isFile = f.isFile();
            boolean isJar = name.toLowerCase().endsWith(".jar");
            boolean isZip = name.toLowerCase().endsWith(".zip");
            return (isFile && (isJar || isZip));
        }
    }
}
