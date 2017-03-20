package ltd.jaimeporraslopez.webcrawler.print

import ltd.jaimeporraslopez.webcrawler.domain.Page
import spock.lang.Specification

/**
 * Created by Jaime on 20/03/2017.
 */
class PrettyPrinterIT extends Specification {

    def "test printer"() {
        given:
        def page = new Page(new URL("http://localhost"))
        (1..5).each { page.children.add(new Page(new URL("http://localhost/child" + it))) }

        when:
        new PrettyPrinter().print(page, new PrintWriter(System.out))

        then:
        noExceptionThrown()
    }
}
