package ru;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class MainTest {

    @Test
    public void test(){
        assertThat(true).isTrue();
        log.info("Finish");
        log.warn("Finish");
        log.error("Finish");
    }
}
