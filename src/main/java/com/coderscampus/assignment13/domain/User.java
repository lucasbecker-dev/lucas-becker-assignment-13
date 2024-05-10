package com.coderscampus.assignment13.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity // Class name = User, DB Table name = user
@Table(name = "users")
public class User {
    private Long userId;
    private String username;
    private String password;
    private String name;
    private LocalDate createdDate;
    private Set<Account> accounts = new HashSet<>();
    private Address address;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_account",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id"))
    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        // If the address passed to the method is null, it checks if the current User object (this) already has an
        // Address associated with it. If it does, it disassociates the User from the Address by setting the User of
        // the Address to null. This is done to ensure data consistency when you want to remove the Address from a User
        if (address == null) {
            if (this.address != null) {
                this.address.setUser(null);
            }
        } else {
            // If the address passed to the method is not null, it associates the User with the Address by calling
            // address.setUser(this). This sets the User of the Address to the current User object (this).
            address.setUser(this);
        }
        // Finally, it sets the Address of the User to the address passed to the method. This could be a new Address
        // object or null, depending on what was passed to the method.
        this.address = address;
    }


    @Override
    public String toString() {
        return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", name=" + name
                + ", accounts=" + accounts + ", address=" + address + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }
}