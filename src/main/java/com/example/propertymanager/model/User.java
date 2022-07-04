package com.example.propertymanager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.List;

/**
 *
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;
    @JsonProperty(value = "first_name")
    @BsonProperty(value = "first_name")
    private String firstName;
    @JsonProperty(value = "last_name")
    @BsonProperty(value = "last_name")
    private String lastName;
    private List<Listing> listings;
}
