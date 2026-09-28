package io.github.theusdev.imageliteapi.domain.service;

import io.github.theusdev.imageliteapi.domain.AccessToken;
import io.github.theusdev.imageliteapi.domain.entity.User;

public interface UserService {
    User getByEmail(String email);
    User save(User user);
    AccessToken authenticate(String email, String password);

}
