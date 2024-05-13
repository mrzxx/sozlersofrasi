package com.sozlersofrasi.api.service;

import com.sozlersofrasi.api.utilities.Result;

public interface UserService {

    Result login(String username,String password);
    Result register(String username,String email,String password);

}
