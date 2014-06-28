package com.github.stefanbirkner.jmarkdown2deckjs;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class JMarkdown2DeckJsTest {
    @Test
    public void convertsThreeSlides() throws Exception {
        String markdown = getResource("threeSlides.md");
        String html = new JMarkdown2DeckJs().convert(markdown);
        assertThat(html, is(equalTo(getResource("threeSlides.html"))));
    }

    @Test
    public void convertsThreeSlidesWithUrlPrefix() throws Exception {
        Configuration configuration = new Configuration("http://dummy.domain/");
        String markdown = getResource("threeSlides.md");
        String html = new JMarkdown2DeckJs(configuration).convert(markdown);
        assertThat(html, is(equalTo(getResource("threeSlidesWithUrlPrefix.html"))));
    }

    private String getResource(String name) throws IOException {
        InputStream resource = getClass().getResourceAsStream(name);
        return IOUtils.toString(resource);
    }
}