package hu.devoli.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Serdeable
@Entity
@Table(name="cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "front_text", nullable = true, length = 256)
    @Size(max = 256)
    private String frontText = "front";

    @Column(name = "back_text", nullable = true, length = 512)
    @Size(max = 512)
    private String backText = "back";

    @Serdeable.Deserializable
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Serdeable.Deserializable
    @ManyToOne(optional = false)
    @JoinColumn(name = "deck_id", nullable = false)
    private Deck deck;

    public Card() {
    }

    public Card(String frontText, String backText, @NotNull User user) {
        this.frontText = frontText;
        this.backText = backText;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public User getUser() {
        return user;
    }

    public void setUser(@NotNull User user) {
        if (this.user != null) {
            this.user.getCards().remove(this);
        }
        this.user = user;
        if (user != null && !user.getCards().contains(this)) {
            user.getCards().add(this);
        }
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        if (this.deck != null) {
            this.deck.getCards().remove(this);
        }
        this.deck = deck;
        if (deck != null && !deck.getCards().contains(this)) {
            deck.getCards().add(this);
        }
    }

    @Override
    public String toString() {
        return String.format(
                "Card{id=%s, frontText='%s', backText='%s', userId=%s, deckId=%s}",
                id, frontText, backText, (user != null ? user.getId() : "null"), (deck != null ? deck.getId() : "null")
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return id != null && id.equals(card.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
