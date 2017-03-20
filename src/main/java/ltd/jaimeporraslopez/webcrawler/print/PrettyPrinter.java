package ltd.jaimeporraslopez.webcrawler.print;

import ltd.jaimeporraslopez.webcrawler.domain.Page;
import org.apache.commons.lang3.StringUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import static org.apache.commons.lang3.StringUtils.repeat;

/**
 * Created by Jaime on 20/03/2017.
 */
public class PrettyPrinter {

    public void print(Page page, PrintWriter writer) {
        print(page, writer, 0);
        writer.flush();
    }

    private void print(Page page, PrintWriter writer, int level) {
        writer.println(toString(page, level));
        for (Page child : page.getChildren()) {
            print(child, writer, level + 1);
        }
    }

    private String toString(Page page, int level) {
        return repeat("\t", level) + page.getAddress();
    }
}
