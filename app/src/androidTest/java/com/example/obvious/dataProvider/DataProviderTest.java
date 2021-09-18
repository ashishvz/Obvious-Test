package com.example.obvious.dataProvider;

import androidx.test.platform.app.InstrumentationRegistry;

import junit.framework.TestCase;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class DataProviderTest extends TestCase {
    @Test
    public void testFile() throws IOException {
        InputStream inputStream = InstrumentationRegistry.getInstrumentation().getContext().getResources().getAssets().open("data.json");
        assertNotNull(inputStream);
    }

}