package org.atumar4031.inputOutputs;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ReadingTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void readProductFromExcel() throws IOException {
        assertTrue(Reading.readProductFromExcel().length > 0);
    }
}