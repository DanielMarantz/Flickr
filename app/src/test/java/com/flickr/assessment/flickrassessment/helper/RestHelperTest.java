package com.flickr.assessment.flickrassessment.helper;

import com.flickr.assessment.flickrassessment.api.RestClient;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

public class RestHelperTest {

    private RestHelper mRestHelper;
    private RestClient mRestClient;

    @Before
    public void setUp() {
        mRestHelper = mock(RestHelper.class);
        mRestClient = mock(RestClient.class);
    }

    @After
    public void tearDown() {
        mRestHelper = null;
        mRestClient = null;
    }

    @Test
    public void restHelperInstance() {
        assertNotNull(mRestHelper);
    }

    @Test
    public void restClientInstance() {
        assertNotNull(mRestClient);
    }
}
