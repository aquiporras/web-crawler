package ltd.jaimeporraslopez.webcrawler.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jaime on 19/03/2017.
 */
public class Page {
    private final URL address;
    private final Set<Page> children;

    public Page(URL address) {
        this.address = address;
        this.children = new HashSet<>();
    }

    public URL getAddress() {
        return address;
    }

    public Set<Page> getChildren() {
        return children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Page page = (Page) o;

        return new EqualsBuilder()
                .append(address, page.address)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(address)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("address", address)
                .toString();
    }
}