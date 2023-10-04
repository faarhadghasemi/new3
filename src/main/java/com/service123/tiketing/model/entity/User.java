package com.service123.tiketing.model.entity;

import com.google.gson.Gson;
import com.service123.tiketing.model.entity.enums.UserRoles;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class User {
    private long id;
    private UserRoles userRoles;
    private String name;
    private String family;
    private String userName;
    private String password;
    private String description;
//    private boolean isDuplicated;
//    private String log;
    private boolean active;
    private boolean deleted;


    @Override
    public String toString() {

        return new Gson().toJson(this);
    }
}
