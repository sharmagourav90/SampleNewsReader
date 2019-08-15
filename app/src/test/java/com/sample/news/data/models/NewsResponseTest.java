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

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class NewsResponseTest {
    private NewsResponse newsResponse;

    private final String SUCCESS_STATUS = "OK";

    private String testFile = "news_response_test_json.json";

    @Before
    public void setUp() throws Exception {
        GsonBuilder gsonBuilder = new GsonBuilder();
        NewsResponseParser parser = new NewsResponseParser();
        gsonBuilder.registerTypeAdapter(NewsResponse.class, parser);

        Gson gson = gsonBuilder.create();

        String jsonString = new ResponseGenerator(testFile).readAll();
        newsResponse = gson.fromJson(jsonString.trim(), NewsResponse.class);
    }

    @Test
    public void testResponseNotNull() throws Exception {
        List<NewsEntity> newsItems = newsResponse.getResults();
        assertNotNull(newsItems);
    }

    @Test
    public void testSetNewsResponse() {
        List<NewsEntity> newsItems = newsResponse.getResults();
        assertNotNull(newsItems);

        newsResponse.setResults(null);
        assertNull(newsResponse.getResults());

        newsResponse.setResults(newsItems);
        assertEquals(newsResponse.getResults(), newsItems);
    }

    @Test
    public void testValidStatus() {
        String msg = newsResponse.getStatus();
        assertEquals(msg, SUCCESS_STATUS);
    }

    @Test
    public void testItemsCount() {
        List<NewsEntity> newsItems = newsResponse.getResults();
        assertNotNull(newsItems);

        assertEquals((int) newsResponse.getNumResults(), newsItems.size());
    }

    @After
    public void tearDown() {
        newsResponse = null;
    }
}
