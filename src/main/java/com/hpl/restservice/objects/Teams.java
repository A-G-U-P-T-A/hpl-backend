package com.hpl.restservice.objects;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data @Document(collection = "teams") public class Teams {

    @Id private String id;
    private String name;
    private Integer wins = 0;
    private Integer ties = 0;
    private Integer losses = 0;
    private Integer score = 0;

    public Teams() {}

    public Teams(String name, Integer wins, Integer ties, Integer losses, Integer score) {
        this.name = name;
        this.wins = wins;
        this.ties = ties;
        this.losses = losses;
        this.score = score;
   }

   @Override public String toString() {
       return id;
   }

}
