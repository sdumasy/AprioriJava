import apriori4j.AnalysisResult;
import apriori4j.AprioriAlgorithm;
import apriori4j.AprioriTimeoutException;
import de.mrapp.apriori.Apriori;
import de.mrapp.apriori.FrequentItemSets;
import de.mrapp.apriori.Output;
import de.mrapp.apriori.Transaction;

import java.util.*;

public class Main {

    private static Double minSupport = 0.05;
    private static Double minConfidence = 0.9;

    private static List<Map<String, Object>> map;

    private static HashMap<Integer, CustomTransaction> transactionHashMap = new HashMap<Integer, CustomTransaction>();

    private static HashMap<Integer, CustomTransaction2> transactionHashMap2 = new HashMap<Integer, CustomTransaction2>();

    private static String query1 = "SELECT * FROM items WHERE receipt IN " +
            "(SELECT ReceiptNumber FROM receipts WHERE Weekend = 'True');";


    public static void main(String[] args) {
        map = DatabaseConnect.excecuteSearchQuery(query1);

//        printFirstPriori();

        printSecondPriori();
    }

    /**
     *
     */
    public static void printFirstPriori() {
        for(Map<String, Object> m : map) {
            Integer receiptId = (Integer) m.get("Receipt");
            String item = m.get("Item").toString();
            if(transactionHashMap.get(receiptId) == null) {
                CustomTransaction t = new CustomTransaction(new TreeSet<String>(), receiptId);
                transactionHashMap.put(receiptId, t);
            }
            transactionHashMap.get(receiptId).addItem(item);
        }

        List<apriori4j.Transaction> transactions = new ArrayList<apriori4j.Transaction>();

        transactions.addAll(transactionHashMap.values());
        System.out.println(transactions);

        AprioriAlgorithm apriori = new AprioriAlgorithm(minSupport, minConfidence);
        try {
            AnalysisResult result = apriori.analyze(transactions);
            System.out.println(result.toString());
        } catch (AprioriTimeoutException e) {
            e.printStackTrace();
        }
    }

    /**
     * Print the support with the second library
     */
    public static void printSecondPriori() {

        for(Map<String, Object> m : map) {
            Integer receiptId = (Integer) m.get("Receipt");
            String item = m.get("Item").toString();
            if(transactionHashMap2.get(receiptId) == null) {
                CustomTransaction2 t = new CustomTransaction2(new TreeSet<CustomItem>(), receiptId);
                transactionHashMap2.put(receiptId, t);
            }
            transactionHashMap2.get(receiptId).addItem(item);
        }

        Apriori<CustomItem> apriorii = new Apriori.Builder<CustomItem>(minSupport).generateRules(minConfidence).create();
        Iterator<Transaction<CustomItem>> iterator = new CustomItemIterator(transactionHashMap2.values());
        Output<CustomItem> output = apriorii.execute(iterator);
        FrequentItemSets<CustomItem> frequentItemSets = output.getFrequentItemSets();
        System.out.println(frequentItemSets.toString());
        System.out.println(output.getRuleSet());
    }
}
