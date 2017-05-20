package b2b.w3;

import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.net.UnknownHostException;
import java.util.Arrays;


public class WriteConcernTest {
    public static void main(String[] args) throws UnknownHostException, InterruptedException {
        MongoClient client = new MongoClient(Arrays.asList(new ServerAddress("localhost", 27000),
                                                    new ServerAddress("localhost", 27001),
                                                    new ServerAddress("localhost", 27002)),
                                             MongoClientOptions.builder().writeConcern(WriteConcern.JOURNALED)
                                                               .build());


        MongoDatabase database = client.getDatabase("test").withWriteConcern(WriteConcern.ACKNOWLEDGED);
        MongoCollection<Document> collection = database.getCollection("replication");

        collection.drop();

        Document doc = new Document("_id", 1);

        collection.insertOne(doc);

        try {
            collection.withWriteConcern(WriteConcern.UNACKNOWLEDGED).insertOne(doc);
        } catch (DuplicateKeyException e) {
            System.out.println(e.getMessage());
        }

    }
}
