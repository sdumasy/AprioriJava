
import de.mrapp.apriori.Transaction;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Set;

public class CustomTransaction2 implements Transaction<CustomItem> {

    @NotNull
    public Iterator<CustomItem> iterator() {
        return getItems().iterator();
    }

    private int id;
    private Set<CustomItem> set;

    public CustomTransaction2(Set<CustomItem> set, int id) {
        this.id = id;
        this.set = set;
    }

    public void addItem(String item) {
        set.add(new CustomItem(item));
    }

    public Set<CustomItem> getItems() {
        return set;
    }

}
