import apriori4j.Transaction;

import java.util.Set;

public class CustomTransaction extends Transaction {

    private int id;

    public CustomTransaction(Set<String> set, int id) {
        super(set);
        this.id = id;
    }

    public void addItem(String item) {
        getItems().add(item);
    }

}
