package hu.devoli.springbackend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Column(length = 256)
    private String frontText;

    @NotNull
    @Column(length = 512)
    private String backText;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "deck_id", referencedColumnName = "id")
    private Deck deck;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFrontText() {
        return frontText;
    }

    public void setFrontText(String frontText) {
        this.frontText = frontText;
    }

    public String getBackText() {
        return backText;
    }

    public void setBackText(String backText) {
        this.backText = backText;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id='" + id + '\'' +
                ", frontText='" + frontText + '\'' +
                ", backText='" + backText + '\'' +
                ", deckId=" + deck.getId() +
                '}';
    }
}
