package com.github.stefanbirkner.jmarkdown2deckjs;

import com.github.rjeschke.txtmark.DefaultDecorator;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.github.rjeschke.txtmark.Processor.process;
import static java.util.Arrays.asList;

/**
 * Converts Markdown files to deck.js presentations.
 * <p>Use a standard Markdown file. jmarkdown2deckjs create a new slide
 * whenever it encounters an `h1` or `h2`. Here is an example file with
 * three slides.
 * <pre>
 * Title Slide
 * ===========
 *
 * First Slide
 * -----------
 *
 * content of first slide
 *
 * Second Slide
 * ------------
 *
 * content of second slide
 * </pre>
 * Such a file is converted with the following two lines.
 * <pre>
 * String markdown = readMarkdown(); //your code that reads the markdown
 * String deckJsHtml = new JMarkdown2DeckJs().convert(markdown);
 * </pre>
 * <p>JMarkdown2DeckJs uses relative URLs for the CSS and JavaScript files by
 * default. You can create HTML files with different URLs by providing a
 * configuration with a prefix for the URLs.
 * <pre>
 * Configuration configuration = new Configuration("http://your.domain/");
 * String markdown = readMarkdown(); //your code that reads the markdown
 * String deckJsHtml = new JMarkdown2DeckJs(configuration).convert(markdown);
 * </pre>
 *
 * @since 0.1.0
 */
public class JMarkdown2DeckJs {
    private static final String NO_URL_PREFIX = "";
    private static final Configuration DEFAULT_CONFIGURATION = new Configuration(NO_URL_PREFIX);
    private static final String SLIDE_END_TAG = "</section>\n";
    private static final String SLIDE_START_TAG = "<section class=\"slide\">\n";

    private static final String TEMPLATE = readTemplate();
    private final Configuration configuration;

    public JMarkdown2DeckJs() {
        this(DEFAULT_CONFIGURATION);
    }

    public JMarkdown2DeckJs(Configuration configuration) {
        this.configuration = configuration;
    }

    private static String readTemplate() {
        InputStream resource = JMarkdown2DeckJs.class.getResourceAsStream("boilerplate.html");
        try {
            return IOUtils.toString(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Converts Markdown files to deck.js presentations.
     *
     * @param markdown the slides written in markdown
     * @return an HTML file that can be used with deck.js JavaScript and CSS.
     */
    public String convert(String markdown) {
        String htmlContent = convertMarkdownToHtml(markdown);
        String templateWithFinalUrls = TEMPLATE.replace("{{urlprefix}}", configuration.getCssAndJavaScriptUrlPrefix());
        return templateWithFinalUrls.replace("{{slides}}", htmlContent);
    }

    private String convertMarkdownToHtml(String markdown) {
        return process(markdown, new DeckJsDecorator()) + SLIDE_END_TAG;
    }

    private static class DeckJsDecorator extends DefaultDecorator {
        private static final List<Integer> HEADLINE_LEVELS_THAT_START_A_NEW_SLIDE = asList(1, 2);
        private boolean firstSlideStarted = false;

        @Override
        public void openHeadline(StringBuilder out, int level) {
            if (HEADLINE_LEVELS_THAT_START_A_NEW_SLIDE.contains(level))
                startNewSlide(out);
            super.openHeadline(out, level);
        }

        private void startNewSlide(StringBuilder out) {
            if (firstSlideStarted)
                out.append(SLIDE_END_TAG).append("\n");
            out.append(SLIDE_START_TAG);
            firstSlideStarted = true;
        }
    }
}
