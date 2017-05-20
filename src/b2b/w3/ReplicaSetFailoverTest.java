package b2b.w3;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Arrays;


public class ReplicaSetFailoverTest {
    public static void main(String[] args) throws InterruptedException {
        MongoClient client = new MongoClient(Arrays.asList(new ServerAddress("localhost", 27000),
                                                    new ServerAddress("localhost", 27001),
                                                    new ServerAddress("localhost", 27002)));

        MongoCollection<Document> collection = client.getDatabase("test").getCollection("replication");
        collection.drop();

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            try {
                collection.insertOne(new Document("_id", new ObjectId()).append("i", i));
                
                System.out.println("Inserted document: " + i);
            } catch (MongoException e) {
                System.out.println("Exception inserting document " + i + ": " + e.getMessage());
            }
            Thread.sleep(500);
        }
        
        client.close();
    }
}
