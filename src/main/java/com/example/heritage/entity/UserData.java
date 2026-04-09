package com.example.heritage.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_data")
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    // 🔹 Journey
    private String place;
    private String rating;
    private String note;

    // 🔹 Favorites
    @Column(length = 500)
    private String favoritePlace;

    @Column(length = 1000)
    private String favoriteImage;
    
    //Wishlist
    
    @Column(length = 500)
    private String wishlist;

    public UserData() {}

    public UserData(Long id, String email, String place, String rating, String note,
                    String favoritePlace, String favoriteImage) {
        this.id = id;
        this.email = email;
        this.place = place;
        this.rating = rating;
        this.note = note;
        this.favoritePlace = favoritePlace;
        this.favoriteImage = favoriteImage;
    }

    // GETTERS & SETTERS

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPlace() { return place; }
    public void setPlace(String place) { this.place = place; }

    public String getRating() { return rating; }
    public void setRating(String rating) { this.rating = rating; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public String getFavoritePlace() { return favoritePlace; }
    public void setFavoritePlace(String favoritePlace) { this.favoritePlace = favoritePlace; }

    public String getFavoriteImage() { return favoriteImage; }
    public void setFavoriteImage(String favoriteImage) { this.favoriteImage = favoriteImage; }
    
    public String getWishlist() { return wishlist; }
    public void setWishlist(String wishlist) { this.wishlist = wishlist; }
}