package b2b.w2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class AddArticle {

	static DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"
	        , Locale.ENGLISH);

	public static void main(String[] args) {

        MongoClient client = new MongoClient();
        MongoDatabase blog = client.getDatabase("blog");
        MongoCollection<Document> articulos = blog.getCollection("articles");
        
        String myName = "amancilla";

        Document articulo = new Document("title","My article")
                .append("author", myName)
                .append("text", "Lorem ipsum dolor sit amet […]")
                .append("tags", Arrays.asList("demo", "Java", "MongoDB"))
                .append("date", new Date());
        
        articulos.insertOne(articulo);
	
  	client.close();
    }
}
