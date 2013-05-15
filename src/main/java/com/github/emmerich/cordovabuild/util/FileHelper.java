package com.github.emmerich.cordovabuild.util;

import org.codehaus.plexus.util.FileUtils;
import org.codehaus.plexus.util.io.InputStreamFacade;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public interface FileHelper {
    public File getFile(String root, String... args);
    public File getFile(File parent, String... args);
    public String stripFileExtension(String fileName);

    // Methods extracted from plexus FileUtils class:

    public String[] getDefaultExcludes();

    public List<String> getDefaultExcludesAsList();

    public String getDefaultExcludesAsString();

    public String byteCountToDisplaySize(int size);

    public String dirname(String filename);

    public String filename(String filename);

    public String basename(String filename);

    public String basename(String filename, String suffix);

    public String extension(String filename);

    public boolean fileExists(String fileName);

    public String fileRead(String file) throws IOException;

    public String fileRead(String file, String encoding) throws IOException;

    public String fileRead(File file) throws IOException;

    public String fileRead(File file, String encoding) throws IOException;

    public void fileAppend(String fileName, String data) throws IOException;

    public void fileAppend(String fileName, String encoding, String data) throws IOException;

    public void fileWrite(String fileName, String data) throws IOException;

    public void fileWrite(String fileName, String encoding, String data) throws IOException;

    public void fileWrite(File file, String data) throws IOException;

    public void fileWrite(File file, String encoding, String data) throws IOException;

    public void fileDelete(String fileName);

    public boolean waitFor(String fileName, int seconds);

    public boolean waitFor(File file, int seconds);

    public File getFile(String fileName);

    public String[] getFilesFromExtension(String directory, String[] extensions);

    public void mkdir(String dir);

    public boolean contentEquals(File file1, File file2) throws IOException;

    public File toFile(URL url);

    public URL[] toURLs(File[] files) throws IOException;

    public String removeExtension(String filename);

    public String getExtension(String filename);

    public String removePath(String filepath);

    public String removePath(String filepath, char fileSeparatorChar);

    public String getPath(String filepath);

    public String getPath(String filepath, char fileSeparatorChar);

    public void copyFileToDirectory(String source, String destinationDirectory) throws IOException;

    public void copyFileToDirectoryIfModified(String source, String destinationDirectory) throws IOException;

    public void copyFileToDirectory(File source, File destinationDirectory) throws IOException;

    public void copyFileToDirectoryIfModified(File source, File destinationDirectory) throws IOException;

    public void copyFile(File source, File destination) throws IOException;

    public boolean copyFileIfModified(File source, File destination) throws IOException;

    public void copyURLToFile(URL source, File destination) throws IOException;

    public void copyStreamToFile(InputStreamFacade source, File destination) throws IOException;

    public String normalize(String path);

    public String catPath(String lookupPath, String path);

    public File resolveFile(File baseFile, String filename);

    public void forceDelete(String file) throws IOException;

    public void forceDelete(File file) throws IOException;

    public void forceDeleteOnExit(File file) throws IOException;

    public void forceMkdir(File file) throws IOException;

    public void deleteDirectory(String directory) throws IOException;

    public void deleteDirectory(File directory) throws IOException;

    public void cleanDirectory(String directory) throws IOException;

    public void cleanDirectory(File directory) throws IOException;

    public long sizeOfDirectory(String directory);

    public long sizeOfDirectory(File directory);

    public List<File> getFiles(File directory, String includes, String excludes) throws IOException;

    public List<File> getFiles(File directory, String includes, String excludes, boolean includeBasedir) throws IOException;

    public List<String> getFileNames(File directory, String includes, String excludes, boolean includeBasedir) throws IOException;

    public List<String> getFileNames(File directory, String includes, String excludes, boolean includeBasedir, boolean isCaseSensitive) throws IOException;

    public List<String> getDirectoryNames(File directory, String includes, String excludes, boolean includeBasedir) throws IOException;

    public List<String> getDirectoryNames(File directory, String includes, String excludes, boolean includeBasedir, boolean isCaseSensitive) throws IOException;

    public List<String> getFileAndDirectoryNames(File directory, String includes, String excludes, boolean includeBasedir, boolean isCaseSensitive, boolean getFiles, boolean getDirectories) throws IOException;

    public void copyDirectory(File sourceDirectory, File destinationDirectory) throws IOException;

    public void copyDirectory(File sourceDirectory, File destinationDirectory, String includes, String excludes) throws IOException;

    public void copyDirectoryLayout(File sourceDirectory, File destinationDirectory, String[] includes, String[] excludes) throws IOException;

    public void copyDirectoryStructure(File sourceDirectory, File destinationDirectory) throws IOException;

    public void copyDirectoryStructureIfModified(File sourceDirectory, File destinationDirectory) throws IOException;

    public void rename(File from, File to) throws IOException;

    public File createTempFile(String prefix, String suffix, File parentDir);

    public void copyFile(File from, File to, String encoding, FileUtils.FilterWrapper[] wrappers) throws IOException;

    public void copyFile(File from, File to, String encoding, FileUtils.FilterWrapper[] wrappers, boolean overwrite) throws IOException;

    public List<String> loadFile(File file) throws IOException;

    public boolean isValidWindowsFileName(File f);
}
