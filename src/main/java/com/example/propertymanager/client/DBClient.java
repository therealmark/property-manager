package com.example.propertymanager.client;

import com.example.propertymanager.model.Listing;
import com.example.propertymanager.model.User;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Data;
import org.bson.BsonValue;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 *
 **/
@Component
@Data
public class DBClient {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoClientSettings clientSettings;
    private CodecRegistry pojoCodecRegistry;
    private CodecRegistry codecRegistry;
    @Value("${mongodb.uri}")
    private String uri;
    @Value("${mongodb.database}")
    private String databaseName;

    @PostConstruct
    public void init() {
        pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        clientSettings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(uri))
                .codecRegistry(codecRegistry)
                .build();
        mongoClient = MongoClients.create(clientSettings);
        database = mongoClient.getDatabase(databaseName);

    }

    @PreDestroy
    public void shutdown() {
        mongoClient.close();
    }

    public MongoCollection<User> getUserCollection() {
        return database.getCollection("users", User.class);
    }

    public User insertUser(User user) {
        BsonValue insertedId = getUserCollection().insertOne(user).getInsertedId();
        return getUserCollection().find(eq("_id", insertedId)).first();
    }

    public User findUserById(String id) {
        return getUserCollection().find(eq(new ObjectId(id))).first();
    }
}
