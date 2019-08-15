package com.sample.news.data.models;

import com.sample.news.data.network.NewsResponseParser;
import com.sample.news.testutil.ResponseGenerator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class MultiMediaTest {
    private NewsResponse newsResponse;
    private NewsEntity newsItem;
    private MediaEntity multiMedia;

    private final String DUMMY = "dummy";

    private String testFile = "news_response_test_json.json";

    @Before
    public void setUp() throws Exception {
        GsonBuilder gsonBuilder = new GsonBuilder();
        NewsResponseParser parser = new NewsResponseParser();
        gsonBuilder.registerTypeAdapter(NewsResponse.class, parser);

        Gson gson = gsonBuilder.create();

        String jsonString = new ResponseGenerator(testFile).readAll();
        newsResponse = gson.fromJson(jsonString.trim(), NewsResponse.class);

        newsItem = newsResponse.getResults().get(0);

        multiMedia = newsItem.getMultimedia().get(0);
    }

    @Test
    public void testGetSetUrl() {
        String url = multiMedia.getUrl();
        assertNotNull(url);

        multiMedia.setUrl(DUMMY);
        assertEquals(DUMMY, multiMedia.getUrl());
    }

    @Test
    public void testGetSetFormat() {
        String url = multiMedia.getFormat();
        assertNotNull(url);

        multiMedia.setFormat(DUMMY);
        assertEquals(DUMMY, multiMedia.getFormat());
    }

    @After
    public void tearDown() {
        newsResponse = null;
        newsItem = null;
        multiMedia = null;
    }
}
