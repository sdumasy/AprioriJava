import de.mrapp.apriori.Transaction;

import java.util.*;

public class CustomItemIterator implements Iterator<Transaction<CustomItem>> {

    private static Iterator<CustomTransaction2> it;

    public CustomItemIterator(Collection<CustomTransaction2> list) {
        it = list.iterator();
    }

    public boolean hasNext() {
        return it.hasNext();
    }

    public Transaction<CustomItem> next() {
        if(hasNext()) {
            return it.next();
        }
        return null;
    }

    public void remove() {

    }
}
