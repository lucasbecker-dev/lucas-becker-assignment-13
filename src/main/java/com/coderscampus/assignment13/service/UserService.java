package com.coderscampus.assignment13.service;

import com.coderscampus.assignment13.domain.Address;
import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.repository.AccountRepository;
import com.coderscampus.assignment13.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo, AccountService accountService) {
        this.userRepo = userRepo;
    }

    public List<User> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public List<User> findByNameAndUsername(String name, String username) {
        return userRepo.findByNameAndUsername(name, username);
    }

    public List<User> findByCreatedDateBetween(LocalDate date1, LocalDate date2) {
        return userRepo.findByCreatedDateBetween(date1, date2);
    }

    public User findExactlyOneUserByUsername(String username) {
        List<User> users = userRepo.findExactlyOneUserByUsername(username);
        if (users.size() > 0)
            return users.get(0);
        else
            return new User();
    }

    public Set<User> findAll() {
        return userRepo.findAllUsersWithAccountsAndAddresses();
    }

    public User findById(Long userId) {
        Optional<User> userOpt = userRepo.findById(userId);
        return userOpt.orElse(new User());
    }

    public User saveUser(User user) {
        return userRepo.save(user);
    }

    public User updateUserAndAddress(User updatedUser) {
        User existingUser = userRepo.findById(updatedUser.getUserId()).orElse(new User());

        Address existingAddress = existingUser.getAddress();
        if (existingAddress == null) {
            existingAddress = new Address();
            existingUser.setAddress(existingAddress);
        }

        existingAddress.setAddressLine1(updatedUser.getAddress().getAddressLine1());
        existingAddress.setAddressLine2(updatedUser.getAddress().getAddressLine2());
        existingAddress.setCity(updatedUser.getAddress().getCity());
        existingAddress.setRegion(updatedUser.getAddress().getRegion());
        existingAddress.setCountry(updatedUser.getAddress().getCountry());
        existingAddress.setZipCode(updatedUser.getAddress().getZipCode());

        return userRepo.save(existingUser);
    }

    public void delete(Long userId) {
        userRepo.deleteById(userId);
    }
}