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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(JUnit4.class)
public class NewsItemTest {
    private NewsResponse newsResponse;
    private NewsEntity newsItem;

    private String testFile = "news_response_test_json.json";

    private final String DUMMY = "dummy";

    @Before
    public void setUp() throws Exception {
        GsonBuilder gsonBuilder = new GsonBuilder();
        NewsResponseParser parser = new NewsResponseParser();
        gsonBuilder.registerTypeAdapter(NewsResponse.class, parser);

        Gson gson = gsonBuilder.create();

        String jsonString = new ResponseGenerator(testFile).readAll();
        newsResponse = gson.fromJson(jsonString.trim(), NewsResponse.class);

        newsItem = newsResponse.getResults().get(0);
    }

    @Test
    public void testGetSetTitle() {
        String title = newsItem.getTitle();
        assertNotNull(title);

        newsItem.setTitle(DUMMY);
        assertEquals(DUMMY, newsItem.getTitle());
    }

    @Test
    public void testGetSetUrl() {
        String url = newsItem.getUrl();
        assertNotNull(url);

        newsItem.setUrl(DUMMY);
        assertEquals(DUMMY, newsItem.getUrl());
    }

    @Test
    public void testGetSetAbstract() {
        String abstract_ = newsItem.getAbstract();
        assertNotNull(abstract_);

        newsItem.setAbstract(DUMMY);
        assertEquals(DUMMY, newsItem.getAbstract());
    }

    @Test
    public void testGetSetMultiMedia() {
        List<MediaEntity> multiMedia = newsItem.getMultimedia();
        assertNotNull(multiMedia);

        newsItem.setMultimedia(null);
        assertNull(newsItem.getMultimedia());
    }

    @After
    public void tearDown() {
        newsResponse = null;
        newsItem = null;
    }

}
