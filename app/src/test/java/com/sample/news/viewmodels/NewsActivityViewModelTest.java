package com.sample.news.ui;

import com.sample.news.data.models.NewsEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class NewsActivityViewModelTest {
    private NewsActivityViewModel viewModel = null;

    @Before
    public void setUp() {
        viewModel = new NewsActivityViewModel();
    }

    @Test
    public void testGetSetTablet() {
        boolean isTablet = viewModel.isTablet();
        assertFalse(isTablet);

        viewModel.setTablet(true);
        assertTrue(viewModel.isTablet());
    }

    @Test
    public void testGetSetSelectedItem() {
        NewsEntity newsItem = viewModel.getNewsDetail();
        assertNull(newsItem);

        viewModel.setSelectedNewsItem(new NewsEntity());
        assertNotNull(viewModel.getNewsDetail());
    }

    @After
    public void tearDown() {
        viewModel = null;
    }
}
