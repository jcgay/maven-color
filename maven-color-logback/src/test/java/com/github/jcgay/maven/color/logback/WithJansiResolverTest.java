package com.github.jcgay.maven.color.logback;

import com.github.jcgay.maven.color.env.Environment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WithJansiResolverTest {

    @Mock
    private Environment env;

    @InjectMocks
    private WithJansiResolver resolver;

    @Test
    public void should_return_false_when_os_is_not_windows() throws Exception {
        given_current_os_is("Mac OS X");

        assertThat(resolver.getPropertyValue()).isEqualTo("false");
    }

    @Test
    public void should_return_true_when_os_is_windows_with_non_unix_terminal() throws Exception {
        given_current_os_is("Windows Server 2012 R2");
        given_terminal_is_non_unix();

        assertThat(resolver.getPropertyValue()).isEqualTo("true");
    }

    @Test
    public void should_return_true_when_os_is_windows_with_unix_terminal() throws Exception {
        given_current_os_is("Windows Server 2012 R2");
        given_terminal_is_unix();

        assertThat(resolver.getPropertyValue()).isEqualTo("false");
    }

    private void given_terminal_is_non_unix() {
        terminal().thenReturn(null);
    }

    private void given_terminal_is_unix() {
        terminal().thenReturn("cygwin");
    }

    private OngoingStubbing<String> terminal() {
        return when(env.getValue("TERM"));
    }

    private static void given_current_os_is(String os) {
        System.setProperty("os.name", os);
    }
}