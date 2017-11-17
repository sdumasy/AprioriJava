import apriori4j.Transaction;
import de.mrapp.apriori.Item;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class CustomItem implements Item {

    /**
     * The name of the item.
     */
    private String name;

    public CustomItem(String name) {
        this.name = name;
    }

    public int compareTo(@NotNull Item o) {
        return toString().compareTo(o.toString());
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CustomItem other = (CustomItem) obj;
        return name.equals(other.name);
    }
}
