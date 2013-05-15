package com.github.emmerich.merger;


import com.github.emmerich.context.ApplicationContext;
import com.github.emmerich.context.PlatformContext;
import com.github.emmerich.util.FileEditorFactory;
import com.github.emmerich.util.FileHelper;
import com.github.emmerich.util.XMLHelper;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

public interface PlatformMerger {
    public void merge(ApplicationContext applicationContext, PlatformContext context) throws MojoFailureException, MojoExecutionException;
    public void setFileHelper(FileHelper helper);
    public void setXMLHelper(XMLHelper helper);
    public void setFileEditorFactory(FileEditorFactory factory);
}
