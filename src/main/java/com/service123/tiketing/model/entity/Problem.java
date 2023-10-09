package com.service123.tiketing.model.entity;

import com.google.gson.Gson;
import com.service123.tiketing.model.entity.enums.ProblemStatus;
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
    private Long parentId;
    private String description;
    private LocalDate dateTime;
    private User sender;
    private User receiver;
    private String answer;
    private ProblemStatus status;
    private boolean deleted;


    @Override
    public String toString() {

        return new Gson().toJson(this);
    }


}
