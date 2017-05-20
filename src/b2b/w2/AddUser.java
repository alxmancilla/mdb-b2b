package b2b.w2;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class AddUser {

	public static void main(String[] args) {
		MongoClient client = new MongoClient("localhost", 27017);
		
		MongoDatabase blog = client.getDatabase("blog");
		
		MongoCollection<Document> users = blog.getCollection("users");
		
		Document user  = new Document()
					.append("name", "amancilla")
					.append("password", "top secret")
					.append("lang", "ES");
			users.insertOne(user);

		
		client.close();
		
	}
	
}
