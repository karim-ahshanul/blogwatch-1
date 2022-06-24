package com.baeldung.common;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class UtilsTest {
    private static final String POST_URL = "http://staging8.baeldung.com/httpclient4";

    @Test
    public void should_find_code_section_in_the_post() throws IOException {
        Document jSoupDocument = Utils.getJSoupDocument(POST_URL);

        Elements elements = jSoupDocument.getElementsByClass(Utils.LANGUAGE_JAVA_CLASS_NAME);

        Assertions.assertFalse(elements.isEmpty());

        Element firstElement = elements.iterator().next();

        Assertions.assertFalse(firstElement.getElementsByTag(Utils.CODE_TAG).isEmpty());
    }

    @Test
    public void should_find_that_all_code_section_are_not_empty() throws IOException {
        List<Executable> testCodeAreNotEmpty = new ArrayList<>();

        Document jSoupDocument = Utils.getJSoupDocument(POST_URL);

        Elements elements = jSoupDocument.getElementsByClass(Utils.LANGUAGE_JAVA_CLASS_NAME);

        for (Element element : elements) {
            testCodeAreNotEmpty.add(() -> assertTrue(!element.getElementsByTag(Utils.CODE_TAG).isEmpty() &&
                !StringUtils.isBlank(element.getElementsByTag(Utils.CODE_TAG).html()), "The code section should not be Empty"));
        }

        Assertions.assertAll(testCodeAreNotEmpty);
    }

}