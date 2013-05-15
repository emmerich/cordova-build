package com.github.emmerich.cordovabuild.util;

import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.util.FileUtils;
import org.codehaus.plexus.util.io.InputStreamFacade;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@Component(role = FileHelper.class)
public class FileHelperImpl implements FileHelper {

    @Override
    public File getFile(String root, String... args) {
        StringBuilder builder = new StringBuilder();
        builder.append(root);

        for(String arg : args) {
            builder.append(File.separator);
            builder.append(arg);
        }

        return new File(builder.toString());
    }

    @Override
    public File getFile(File parent, String... args) {
        return this.getFile(parent.getAbsolutePath(), args);
    }

    @Override
    public String stripFileExtension(String fileName) {
        return fileName.substring(0, fileName.lastIndexOf("."));
    }

    @Override
    public String[] getDefaultExcludes() {
        return FileUtils.getDefaultExcludes();
    }

    @Override
    public List<String> getDefaultExcludesAsList() {
        return FileUtils.getDefaultExcludesAsList();
    }

    @Override
    public String getDefaultExcludesAsString() {
        return FileUtils.getDefaultExcludesAsString();
    }

    @Override
    public String byteCountToDisplaySize(int size) {
        return FileUtils.byteCountToDisplaySize(size);
    }

    @Override
    public String dirname(String filename) {
        return FileUtils.dirname(filename);
    }

    @Override
    public String filename(String filename) {
        return FileUtils.filename(filename);
    }

    @Override
    public String basename(String filename) {
        return FileUtils.basename(filename);
    }

    @Override
    public String basename(String filename, String suffix) {
        return FileUtils.basename(filename, suffix);
    }

    @Override
    public String extension(String filename) {
        return FileUtils.extension(filename);
    }

    @Override
    public boolean fileExists(String fileName) {
        return FileUtils.fileExists(fileName);
    }

    @Override
    public String fileRead(String file) throws IOException {
        return FileUtils.fileRead(file);
    }

    @Override
    public String fileRead(String file, String encoding) throws IOException {
        return FileUtils.fileRead(file, encoding);
    }

    @Override
    public String fileRead(File file) throws IOException {
        return FileUtils.fileRead(file);
    }

    @Override
    public String fileRead(File file, String encoding) throws IOException {
        return FileUtils.fileRead(file, encoding);
    }

    @Override
    public void fileAppend(String fileName, String data) throws IOException {
        FileUtils.fileAppend(fileName, data);
    }

    @Override
    public void fileAppend(String fileName, String encoding, String data) throws IOException {
        FileUtils.fileAppend(fileName, encoding, data);
    }

    @Override
    public void fileWrite(String fileName, String data) throws IOException {
        FileUtils.fileWrite(fileName, data);
    }

    @Override
    public void fileWrite(String fileName, String encoding, String data) throws IOException {
        FileUtils.fileWrite(fileName, encoding, data);
    }

    @Override
    public void fileWrite(File file, String data) throws IOException {
        FileUtils.fileWrite(file, data);
    }

    @Override
    public void fileWrite(File file, String encoding, String data) throws IOException {
        FileUtils.fileWrite(file, encoding, data);
    }

    @Override
    public void fileDelete(String fileName) {
        FileUtils.fileDelete(fileName);
    }

    @Override
    public boolean waitFor(String fileName, int seconds) {
        return FileUtils.waitFor(fileName, seconds);
    }

    @Override
    public boolean waitFor(File file, int seconds) {
        return FileUtils.waitFor(file, seconds);
    }

    @Override
    public File getFile(String fileName) {
        return FileUtils.getFile(fileName);
    }

    @Override
    public String[] getFilesFromExtension(String directory, String[] extensions) {
        return FileUtils.getFilesFromExtension(directory, extensions);
    }

    @Override
    public void mkdir(String dir) {
        FileUtils.mkdir(dir);
    }

    @Override
    public boolean contentEquals(File file1, File file2) throws IOException {
        return FileUtils.contentEquals(file1, file2);
    }

    @Override
    public File toFile(URL url) {
        return FileUtils.toFile(url);
    }

    @Override
    public URL[] toURLs(File[] files) throws IOException {
        return FileUtils.toURLs(files);
    }

    @Override
    public String removeExtension(String filename) {
        return FileUtils.removeExtension(filename);
    }

    @Override
    public String getExtension(String filename) {
        return FileUtils.getExtension(filename);
    }

    @Override
    public String removePath(String filepath) {
        return FileUtils.removePath(filepath);
    }

    @Override
    public String removePath(String filepath, char fileSeparatorChar) {
        return FileUtils.removePath(filepath, fileSeparatorChar);
    }

    @Override
    public String getPath(String filepath) {
        return FileUtils.getPath(filepath);
    }

    @Override
    public String getPath(String filepath, char fileSeparatorChar) {
        return FileUtils.getPath(filepath, fileSeparatorChar);
    }

    @Override
    public void copyFileToDirectory(String source, String destinationDirectory) throws IOException {
        FileUtils.copyFileToDirectory(source, destinationDirectory);
    }

    @Override
    public void copyFileToDirectoryIfModified(String source, String destinationDirectory) throws IOException {
        FileUtils.copyFileToDirectoryIfModified(source, destinationDirectory);
    }

    @Override
    public void copyFileToDirectory(File source, File destinationDirectory) throws IOException {
        FileUtils.copyFileToDirectory(source, destinationDirectory);
    }

    @Override
    public void copyFileToDirectoryIfModified(File source, File destinationDirectory) throws IOException {
        FileUtils.copyFileToDirectoryIfModified(source, destinationDirectory);
    }

    @Override
    public void copyFile(File source, File destination) throws IOException {
        FileUtils.copyFile(source, destination);
    }

    @Override
    public boolean copyFileIfModified(File source, File destination) throws IOException {
        return FileUtils.copyFileIfModified(source, destination);
    }

    @Override
    public void copyURLToFile(URL source, File destination) throws IOException {
        FileUtils.copyURLToFile(source, destination);
    }

    @Override
    public void copyStreamToFile(InputStreamFacade source, File destination) throws IOException {
        FileUtils.copyStreamToFile(source, destination);
    }

    @Override
    public String normalize(String path) {
        return FileUtils.normalize(path);
    }

    @Override
    public String catPath(String lookupPath, String path) {
        return FileUtils.catPath(lookupPath, path);
    }

    @Override
    public File resolveFile(File baseFile, String filename) {
        return FileUtils.resolveFile(baseFile, filename);
    }

    @Override
    public void forceDelete(String file) throws IOException {
        FileUtils.forceDelete(file);
    }

    @Override
    public void forceDelete(File file) throws IOException {
        FileUtils.forceDelete(file);
    }

    @Override
    public void forceDeleteOnExit(File file) throws IOException {
        FileUtils.forceDeleteOnExit(file);
    }

    @Override
    public void forceMkdir(File file) throws IOException {
        FileUtils.forceMkdir(file);
    }

    @Override
    public void deleteDirectory(String directory) throws IOException {
        FileUtils.deleteDirectory(directory);
    }

    @Override
    public void deleteDirectory(File directory) throws IOException {
        FileUtils.deleteDirectory(directory);
    }

    @Override
    public void cleanDirectory(String directory) throws IOException {
        FileUtils.cleanDirectory(directory);
    }

    @Override
    public void cleanDirectory(File directory) throws IOException {
        FileUtils.cleanDirectory(directory);
    }

    @Override
    public long sizeOfDirectory(String directory) {
        return FileUtils.sizeOfDirectory(directory);
    }

    @Override
    public long sizeOfDirectory(File directory) {
        return FileUtils.sizeOfDirectory(directory);
    }

    @Override
    public List<File> getFiles(File directory, String includes, String excludes) throws IOException {
        return FileUtils.getFiles(directory, includes, excludes);
    }

    @Override
    public List<File> getFiles(File directory, String includes, String excludes, boolean includeBasedir) throws IOException {
        return FileUtils.getFiles(directory, includes, excludes, includeBasedir);
    }

    @Override
    public List<String> getFileNames(File directory, String includes, String excludes, boolean includeBasedir) throws IOException {
        return FileUtils.getFileNames(directory, includes, excludes, includeBasedir);
    }

    @Override
    public List<String> getFileNames(File directory, String includes, String excludes, boolean includeBasedir, boolean isCaseSensitive) throws IOException {
        return FileUtils.getFileNames(directory, includes, excludes, includeBasedir, isCaseSensitive);
    }

    @Override
    public List<String> getDirectoryNames(File directory, String includes, String excludes, boolean includeBasedir) throws IOException {
        return FileUtils.getDirectoryNames(directory, includes, excludes, includeBasedir);
    }

    @Override
    public List<String> getDirectoryNames(File directory, String includes, String excludes, boolean includeBasedir, boolean isCaseSensitive) throws IOException {
        return FileUtils.getDirectoryNames(directory, includes, excludes, includeBasedir, isCaseSensitive);
    }

    @Override
    public List<String> getFileAndDirectoryNames(File directory, String includes, String excludes, boolean includeBasedir, boolean isCaseSensitive, boolean getFiles, boolean getDirectories) throws IOException {
        return FileUtils.getFileAndDirectoryNames(directory, includes, excludes, includeBasedir, isCaseSensitive, getFiles, getDirectories);
    }

    @Override
    public void copyDirectory(File sourceDirectory, File destinationDirectory) throws IOException {
        FileUtils.copyDirectory(sourceDirectory, destinationDirectory);
    }

    @Override
    public void copyDirectory(File sourceDirectory, File destinationDirectory, String includes, String excludes) throws IOException {
        FileUtils.copyDirectory(sourceDirectory, destinationDirectory, includes, excludes);
    }

    @Override
    public void copyDirectoryLayout(File sourceDirectory, File destinationDirectory, String[] includes, String[] excludes) throws IOException {
        FileUtils.copyDirectoryLayout(sourceDirectory, destinationDirectory, includes, excludes);
    }

    @Override
    public void copyDirectoryStructure(File sourceDirectory, File destinationDirectory) throws IOException {
        FileUtils.copyDirectoryStructure(sourceDirectory, destinationDirectory);
    }

    @Override
    public void copyDirectoryStructureIfModified(File sourceDirectory, File destinationDirectory) throws IOException {
        FileUtils.copyDirectoryStructureIfModified(sourceDirectory, destinationDirectory);
    }

    @Override
    public void rename(File from, File to) throws IOException {
        FileUtils.rename(from, to);
    }

    @Override
    public File createTempFile(String prefix, String suffix, File parentDir) {
        return FileUtils.createTempFile(prefix, suffix, parentDir);
    }

    @Override
    public void copyFile(File from, File to, String encoding, FileUtils.FilterWrapper[] wrappers) throws IOException {
        FileUtils.copyFile(from, to, encoding, wrappers);
    }

    @Override
    public void copyFile(File from, File to, String encoding, FileUtils.FilterWrapper[] wrappers, boolean overwrite) throws IOException {
        FileUtils.copyFile(from, to, encoding, wrappers, overwrite);
    }

    @Override
    public List<String> loadFile(File file) throws IOException {
        return FileUtils.loadFile(file);
    }

    @Override
    public boolean isValidWindowsFileName(File f) {
        return FileUtils.isValidWindowsFileName(f);
    }

}
