package com.github.emmerich.platform.provider;

import com.github.emmerich.builder.PlatformBuilder;
import com.github.emmerich.merger.PlatformMerger;
import com.github.emmerich.platform.MobilePlatform;
import com.github.emmerich.platform.permission.PlatformPermissionMap;
import com.github.emmerich.prepare.PlatformPreparer;
import org.apache.maven.plugin.MojoExecutionException;

public class AbstractPlatformProvider implements PlatformProvider {

    private PlatformPreparer preparer;
    private PlatformMerger merger;
    private PlatformBuilder builder;
    private PlatformPermissionMap permissionMap;
    private String cordovaArtifact;

    public AbstractPlatformProvider(PlatformPreparer preparer,
                                    PlatformMerger merger,
                                    PlatformBuilder builder,
                                    PlatformPermissionMap permissionMap,
                                    String cordovaArtifact) {
        this.preparer = preparer;
        this.merger = merger;
        this.builder = builder;
        this.permissionMap = permissionMap;
        this.cordovaArtifact = cordovaArtifact;
    }

    @Override
    public PlatformMerger getMerger(MobilePlatform platform) throws MojoExecutionException {
        return merger;
    }

    @Override
    public PlatformPreparer getPreparer(MobilePlatform platform) throws MojoExecutionException {
        return preparer;
    }

    @Override
    public PlatformBuilder getBuilder(MobilePlatform platform) throws MojoExecutionException {
        return builder;
    }

    @Override
    public PlatformPermissionMap getPermissionMap(MobilePlatform platform) throws MojoExecutionException {
        return permissionMap;
    }

    @Override
    public String getCordovaArtifactId(MobilePlatform platform) throws MojoExecutionException {
        return cordovaArtifact;
    }
}
