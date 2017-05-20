package b2b.w2;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class AddUsers {

	public static void main(String[] args) {
		MongoClient client = new MongoClient("localhost", 27017);
		
		MongoDatabase blog = client.getDatabase("blog");
		
		MongoCollection<Document> users = blog.getCollection("users");
		
		for (int i = 0; i < 10000; i++) {
			int suffix = (int)Math.round(Math.random()*12345);
			Document user  = new Document()
					.append("name", "USER_"+suffix)
					.append("password", "pass"+ suffix)
					.append("lang", "ES")
					.append("karma", Integer.valueOf(suffix % 500));			
			users.insertOne(user);
		}		
		
		client.close();
		
	}
	
}
