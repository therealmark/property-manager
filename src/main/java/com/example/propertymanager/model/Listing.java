package com.example.propertymanager.model;

import lombok.*;
import org.bson.types.ObjectId;

/**
 *
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Listing {
    private String name;
    private String description;
}
