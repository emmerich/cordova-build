package com.github.emmerich.cordovabuild.platform;

import com.github.emmerich.cordovabuild.platform.provider.PlatformProvider;
import org.apache.maven.plugin.MojoExecutionException;


public interface PlatformProviderFactory {
    public PlatformProvider getPlatformProvider(MobilePlatform platform) throws MojoExecutionException;
}
