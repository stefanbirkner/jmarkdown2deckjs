# jmarkdown2deckjs

[![Build Status](https://travis-ci.org/stefanbirkner/jmarkdown2deckjs.svg?branch=master)](https://travis-ci.org/stefanbirkner/jmarkdown2deckjs)

jmarkdown2deckjs is a library that converts 
[Markdown](http://daringfireball.net/projects/markdown/) files to
[deck.js](http://imakewebthings.com/deck.js/) presentations.
jmarkdown2deckjs has been inspired by Ulf Börgenholz'
[markdown2deckjs](https://github.com/ulf/markdown2deckjs).

File Format
-----------

Use a standard Markdown file. jmarkdown2deckjs create a new slide
whenever it encounters an `h1` or `h2`. Here is an example file with
three slides.

    Title Slide
    ===========

    First Slide
    -----------

    content of first slide

    Second Slide
    ------------

    content of second slide

Get the Library
---------------

The library is available from Maven Central

    <dependency>
      <groupId>com.github.stefanbirkner</groupId>
      <artifactId>jmarkdown2deckjs</artifactId>
      <version>1.0.0</version>
    </dependency>

Create the Presentation
-----------------------

    import com.github.stefanbirkner.jmarkdown2deckjs.*;

    String markdown = readMarkdown(); //your code that reads the markdown
    String deckJsHtml = new JMarkdown2DeckJs().convert(markdown);
