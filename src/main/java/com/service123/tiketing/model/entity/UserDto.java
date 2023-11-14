package com.service123.tiketing.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class UserDto {
    private String userName;
    private String password;


    @Override
    public String toString() {

        return new Gson().toJson(this);
    }
}
