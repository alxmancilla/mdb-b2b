package b2b.w3;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.Arrays;


public class ReplicaSetTest {

    public static void main(String[] args) throws InterruptedException {
/**
 * 		MongoClientURI  uri = new MongoClientURI("mongodb://localhost:27000,localhost:27001,localhost:27002/?"
 *		+ "replicaSet=b2bRS");
 */

    	
    	MongoClient client = new MongoClient(Arrays.asList(new ServerAddress("localhost", 27000),
                                                    new ServerAddress("localhost", 27001),
                                                    new ServerAddress("localhost", 27002)),
                                             MongoClientOptions.builder().requiredReplicaSetName("b2bRS")
                                                               .build());

        MongoCollection<Document> collection = client.getDatabase("test").getCollection("replication");
        collection.drop();

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            collection.insertOne(new Document("_id", i));
            System.out.println("Inserted document: " + i);
            Thread.sleep(500);
        }
        
        client.close();
    }
}
