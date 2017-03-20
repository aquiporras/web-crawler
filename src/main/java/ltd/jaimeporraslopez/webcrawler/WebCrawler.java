package ltd.jaimeporraslopez.webcrawler;

import ltd.jaimeporraslopez.webcrawler.crawl.Crawler;
import ltd.jaimeporraslopez.webcrawler.crawl.JSoupPageResolver;
import ltd.jaimeporraslopez.webcrawler.domain.Page;
import ltd.jaimeporraslopez.webcrawler.print.PrettyPrinter;
import org.apache.commons.cli.*;

import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Jaime on 19/03/2017.
 */
public class WebCrawler {

    public static void main(String[] args) {
        try {
            Page root = rootPage(args);
            Crawler crawler = new Crawler();
            PrettyPrinter prettyPrinter = new PrettyPrinter();
            prettyPrinter.print(crawler.crawl(root, new JSoupPageResolver(), crawler.defaultFilter(root)),
                    new PrintWriter(System.out));
        } catch (RuntimeException e) {
            System.err.println("Error.  Reason: " + e.getMessage());
            System.exit(-1);
        }
    }

    private static Page rootPage(String[] args) {
        Options options = buildOptions();
        try {
            CommandLine line = new DefaultParser().parse(options, args);
            return new Page(new URL(line.getOptionValue("page")));
        } catch (ParseException | MalformedURLException e) {
            System.err.println("Parsing failed.  Reason: " + e.getMessage());
            new HelpFormatter().printHelp("crawler -p <page>", options);
            throw new IllegalArgumentException(e);
        }
    }

    private static Options buildOptions() {
        return new Options()
                .addRequiredOption("p", "page", true, "Root page url to be crawled. Must be a valid URL");

    }
}
