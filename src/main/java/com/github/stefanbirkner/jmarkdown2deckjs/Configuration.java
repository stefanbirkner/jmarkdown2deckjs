package com.github.stefanbirkner.jmarkdown2deckjs;

/**
 * Configuration for the {@link com.github.stefanbirkner.jmarkdown2deckjs.JMarkdown2DeckJs}
 * converter. Controls the converter by providing information about the external components.
 *
 * @since 0.1.0
 */
public class Configuration {
    private final String cssAndJavaScriptUrlPrefix;

    public Configuration(String cssAndJavaScriptUrlPrefix) {
        this.cssAndJavaScriptUrlPrefix = cssAndJavaScriptUrlPrefix;
    }

    public String getCssAndJavaScriptUrlPrefix() {
        return cssAndJavaScriptUrlPrefix;
    }
}
