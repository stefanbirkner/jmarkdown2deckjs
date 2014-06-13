package com.github.stefanbirkner.jmarkdown2deckjs;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class JMarkdown2DeckJsTest {
    private final JMarkdown2DeckJs jMarkdown2DeckJs = new JMarkdown2DeckJs();

    @Test
    public void convertsThreeSlides() throws Exception {
        String markdown = getResource("threeSlides.md");
        String html = jMarkdown2DeckJs.convert(markdown);
        assertThat(html, is(equalTo(getResource("threeSlides.html"))));
    }

    private String getResource(String name) throws IOException {
        InputStream resource = getClass().getResourceAsStream(name);
        return IOUtils.toString(resource);
    }
}