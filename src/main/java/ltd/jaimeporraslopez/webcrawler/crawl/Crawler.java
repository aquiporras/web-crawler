package ltd.jaimeporraslopez.webcrawler.crawl;

import ltd.jaimeporraslopez.webcrawler.domain.Page;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Created by Jaime on 19/03/2017.
 */
public class Crawler {

    public Page crawl(Page page, PageResolver pageResolver, Predicate<Page> filter) {
        pageResolver.resolve(page, filter).forEach(newPage -> crawl(newPage, pageResolver, filter));
        return page;
    }

    public Predicate<Page> isUnique(Set<Page> visited) {
        return visited::add;
    }

    public Predicate<Page> isInternal(Page root) {
        return page -> page.getAddress().getHost().equals(root.getAddress().getHost());
    }

    public Predicate<Page> defaultFilter(Page root) {
        return isUnique(new HashSet<Page>() {{add(root);}}).and(isInternal(root));
    }
}
