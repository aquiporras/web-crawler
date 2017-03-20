package ltd.jaimeporraslopez.webcrawler.crawl

import ltd.jaimeporraslopez.webcrawler.domain.Page
import spock.lang.Specification

import java.util.function.Predicate
import java.util.stream.Stream

/**
 * Created by Jaime on 19/03/2017.
 */
class JSoupPageResolverIT extends Specification {

    def "try to resolve a page"() {
        given:
        def page = new Page(new URL("http://wiprodigital.com"))

        when:
        def more = new JSoupPageResolver().resolve(page, { it -> true } as Predicate<Page>)

        then:
        more
        page.children

    }
}
