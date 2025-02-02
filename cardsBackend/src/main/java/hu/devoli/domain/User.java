package hu.devoli.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Serdeable
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    @Column(name = "first_name", nullable = false, length = 50)
    @Size(min = 2, max = 50)
    private String firstName;

    @NotNull
    @Column(name = "last_name", nullable = false, length = 50)
    @Size(min = 2, max = 50)
    private String lastName;

    @NotNull
    @Column(name = "email", unique = true, nullable = false, length = 50)
    @Size(min = 2, max = 50)
    @Email
    private String email;

    @NotNull
    @Column(name ="password", nullable = false)
    private String password;

    //TODO:Add relations
    @Serdeable.Deserializable
    @OneToMany(mappedBy = "user")
    private List<Card> cards = new ArrayList<>();

    @Serdeable.Deserializable
    @OneToMany(mappedBy = "user")
    private List<Deck> decks = new ArrayList<>();

    public User(){}

    public User(@NotNull String firstName, @NotNull String lastName, @NotNull String email, @NotNull String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
        cards.forEach(card -> card.setUser(this));
    }

    public void addCard(Card card){
        cards.add(card);
        card.setUser(this);
    }

    public void removeCard(Card card){
        cards.remove(card);
        card.setUser(null);
    }

    public List<Deck> getDecks() {
        return decks;
    }

    public void setDecks(List<Deck> decks) {
        this.decks = decks;
        decks.forEach(deck -> deck.setUser(this));
    }

    public void addDeck(Deck deck) {
        decks.add(deck);
        deck.setUser(this);
    }

    public void removeDeck(Deck deck) {
        decks.remove(deck);
        deck.setUser(null);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
