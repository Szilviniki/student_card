package hu.devoli.domain;


import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Serdeable
@Entity
@Table(name="cards")
public class Deck {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name="name", nullable = false, length = 50)
    @NotNull
    @Size(min = 2, max = 50)
    private String name;

    @Column(name="color", length=7)
    @Size(min = 7, max = 7)
    @NotNull
    private String color;


    @PrePersist
    public void prePersist() {
        if (color == null) {
            color = "#000000";
        }
    }

    @Serdeable.Deserializable
    @OneToMany(mappedBy = "deck")
    private List<Card> cards = new ArrayList<>();

    @Serdeable.Deserializable
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Deck() {
    }

    public Deck(String name, String color, @NotNull User user) {
        this.name = name;
        this.color = color;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        if (this.user != null) {
            this.user.getDecks().remove(this);
        }
        this.user = user;
        if (user != null && !user.getDecks().contains(this)) {
            user.getDecks().add(this);
        }
    }

    @Override
    public String toString() {
        return "Deck{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", userId=" + (user != null ? user.getId() : "null") +
                '}';
    }
}
