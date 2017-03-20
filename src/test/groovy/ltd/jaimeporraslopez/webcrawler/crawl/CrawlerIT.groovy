package ltd.jaimeporraslopez.webcrawler.crawl

import ltd.jaimeporraslopez.webcrawler.domain.Page
import ltd.jaimeporraslopez.webcrawler.print.PrettyPrinter
import spock.lang.Specification

/**
 * Created by Jaime on 20/03/2017.
 */
class CrawlerIT extends Specification {

    def "crawl all"() {
        given:
        def page = new Page(new URL("http://wiprodigital.com"))
        def crawler = new Crawler()

        when:
        crawler.crawl(page, new JSoupPageResolver(), crawler.defaultFilter(page))

        then:
        page.children.any { it.children }

        when:
        new PrettyPrinter().print(page, new PrintWriter(System.out))

        then:
        noExceptionThrown()
    }
}
