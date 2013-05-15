package com.github.emmerich.platform;

import com.github.emmerich.platform.provider.PlatformProvider;
import org.apache.maven.plugin.MojoExecutionException;


public interface PlatformProviderFactory {
    public PlatformProvider getPlatformProvider(MobilePlatform platform) throws MojoExecutionException;
}
