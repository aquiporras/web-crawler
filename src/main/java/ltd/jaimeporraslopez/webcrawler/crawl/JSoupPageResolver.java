package ltd.jaimeporraslopez.webcrawler.crawl;

import ltd.jaimeporraslopez.webcrawler.domain.Page;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.Optional.empty;
import static java.util.Optional.of;

/**
 * Created by Jaime on 19/03/2017.
 */
public class JSoupPageResolver implements PageResolver {

    @Override
    public Stream<Page> resolve(Page page, Predicate<Page> filter) {
        Set<Page> newPages = new HashSet<>();
        Connection connection = Jsoup.connect(page.getAddress().toString());
        Document document = null;
        try {
            document = connection.get();
            Elements links = document.select("a[href]");
            for (Element element : links) {
                Optional<URL> maybeALink = parseSimpleLink(element);
                if (maybeALink.isPresent()) {
                    Page child = new Page(maybeALink.get());
                    if (filter.test(child)) {
                        newPages.add(child);
                    }
                    page.getChildren().add(child);
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException("Connection error trying to resolve: " + page);
        }
        return newPages.stream();
    }

    private Optional<URL> parseSimpleLink(Element element) {
        try {
            String newUrl = element.attr("abs:href");
            URL url = new URL(newUrl);
            return of(new URL(url.getProtocol(), url.getHost(), url.getPort(), url.getPath()));
        } catch (MalformedURLException e) {
            return empty();
        }
    }
}
