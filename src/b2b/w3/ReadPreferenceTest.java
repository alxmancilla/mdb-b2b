package b2b.w3;

import com.mongodb.MongoClient;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

public class ReadPreferenceTest {
    public static void main(String[] args) throws UnknownHostException, InterruptedException {
        MongoClient client = new MongoClient(Arrays.asList(new ServerAddress("localhost", 27000),
                                                    new ServerAddress("localhost", 27001),
                                                    new ServerAddress("localhost", 27002)));

        MongoDatabase database = client.getDatabase("test").withReadPreference(ReadPreference.secondary());
        MongoCollection<Document> collection = database.getCollection("replication");

        List<Document> documents = collection.withReadPreference(ReadPreference.primaryPreferred()).find().into(new ArrayList<Document>());

        for (Document doc : documents) {
            System.out.println(doc.toJson());
        }
    }
}
