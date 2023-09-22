package com.service123.tiketing.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder

public class Problem {
    private long id;
    private String describtion;
    private LocalDate datetime;
    private boolean deleted;





    @Override
    public String toString() {

        return new Gson().toJson(this);
    }


}
