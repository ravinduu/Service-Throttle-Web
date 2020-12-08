package com.servicethrottle.servicethrottlebackend.services;

import com.servicethrottle.servicethrottlebackend.models.UserCredentials;
import com.servicethrottle.servicethrottlebackend.repositories.UserCredentialsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    @Test
    @DisplayName("get all users")
    void getAllUsersTest(){
        UserService userService = new UserService(new UserCredentialsRepository() {
            @Override
            public Optional<UserCredentials> findOneByUsername(String username) {
                return Optional.empty();
            }

            @Override
            public List<UserCredentials> findAll() {
                return null;
            }

            @Override
            public List<UserCredentials> findAll(Sort sort) {
                return null;
            }

            @Override
            public List<UserCredentials> findAllById(Iterable<Long> iterable) {
                return null;
            }

            @Override
            public <S extends UserCredentials> List<S> saveAll(Iterable<S> iterable) {
                return null;
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends UserCredentials> S saveAndFlush(S s) {
                return null;
            }

            @Override
            public void deleteInBatch(Iterable<UserCredentials> iterable) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public UserCredentials getOne(Long aLong) {
                return null;
            }

            @Override
            public <S extends UserCredentials> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends UserCredentials> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public Page<UserCredentials> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends UserCredentials> S save(S s) {
                return null;
            }

            @Override
            public Optional<UserCredentials> findById(Long aLong) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(Long aLong) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Long aLong) {

            }

            @Override
            public void delete(UserCredentials userCredentials) {

            }

            @Override
            public void deleteAll(Iterable<? extends UserCredentials> iterable) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends UserCredentials> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends UserCredentials> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends UserCredentials> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends UserCredentials> boolean exists(Example<S> example) {
                return false;
            }
        });
        Assertions.assertEquals(null,userService.getAllUsers());
    }

}