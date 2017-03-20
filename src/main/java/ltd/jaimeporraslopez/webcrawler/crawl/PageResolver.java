package ltd.jaimeporraslopez.webcrawler.crawl;

import ltd.jaimeporraslopez.webcrawler.domain.Page;

import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by Jaime on 19/03/2017.
 */
public interface PageResolver {

    /**
     * Resolve a page children, returning a stream of new pages to visit depending on the filter
     *
     * @param page
     * @param filter
     * @return
     */
    Stream<Page> resolve(Page page, Predicate<Page> filter);
}
