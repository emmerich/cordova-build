package com.github.emmerich.platform.provider;

import com.github.emmerich.builder.PlatformBuilder;
import com.github.emmerich.merger.PlatformMerger;
import com.github.emmerich.platform.MobilePlatform;
import com.github.emmerich.platform.permission.PlatformPermissionMap;
import com.github.emmerich.prepare.PlatformPreparer;
import org.apache.maven.plugin.MojoExecutionException;

public interface PlatformProvider {
    public PlatformMerger getMerger(MobilePlatform platform) throws MojoExecutionException;
    public PlatformPreparer getPreparer(MobilePlatform platform) throws MojoExecutionException;
    public PlatformBuilder getBuilder(MobilePlatform platform) throws MojoExecutionException;
    public PlatformPermissionMap getPermissionMap(MobilePlatform platform) throws MojoExecutionException;
    public String getCordovaArtifactId(MobilePlatform platform) throws MojoExecutionException;
}
