package team404.mongodb.mongoTest.driver;

import com.mongodb.client.*;
import org.bson.Document;

import java.util.Random;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Updates.set;

public class RepositoryDriver {
    public static void main(String[] args) {
        MongoClient client = MongoClients.create();
        MongoDatabase database = client.getDatabase("aster");
        MongoCollection<Document> collection = database.getCollection("company");
        Random random = new Random();

        collection.insertOne(new Document("name", "Company" + random.nextInt(100)));

        collection.updateOne(eq("name", "Company1"), set("name", "HahaName"));

        collection.deleteOne(eq("name", "HahaName"));

        FindIterable<Document> resultDocuments = collection.find()
                .projection(fields(include("name"), excludeId()));

        for (Document document : resultDocuments) {
            System.out.println(document.toJson());
        }
    }
}
