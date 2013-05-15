package com.github.emmerich.cordovabuild.util;

import org.apache.maven.plugin.MojoExecutionException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class SystemHelperImplTest {

    private SystemHelper systemHelper;

    @Before
    public void before() {
        systemHelper = mock(SystemHelperImpl.class, CALLS_REAL_METHODS);
    }

    @Test(expected = MojoExecutionException.class)
    public void an_exception_is_thrown_when_on_a_non_windows_or_unix_platform() throws MojoExecutionException {
        doReturn(false).when(systemHelper).isOsLinux();
        doReturn(false).when(systemHelper).isOsWindows();

        systemHelper.getExecutable("cp", "fileA", "fileB");
    }

    @Test
    public void linux_executables_arguments_are_concatenated_to_the_executable() throws MojoExecutionException {
        doReturn(true).when(systemHelper).isOsLinux();
        doReturn(false).when(systemHelper).isOsWindows();

        String result = systemHelper.getExecutable("cp", "fileA", "fileB");

        assertTrue("cp fileA fileB".equals(result));
    }

    @Test
    public void windows_executables_arguments_are_concatenated_and_cmd_exe_called() throws MojoExecutionException {
        doReturn(true).when(systemHelper).isOsWindows();
        doReturn(false).when(systemHelper).isOsLinux();

        String result = systemHelper.getExecutable("cp", "fileA", "fileB");

        assertTrue("cmd.exe /c cp.bat fileA fileB".equals(result));
    }


}
