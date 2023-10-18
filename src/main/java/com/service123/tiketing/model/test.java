package com.service123.tiketing.model;

import com.service123.tiketing.model.entity.Problem;
import com.service123.tiketing.model.entity.User;
import com.service123.tiketing.model.entity.enums.ProblemStatus;
import com.service123.tiketing.model.entity.enums.UserRoles;
import com.service123.tiketing.model.repository.ProblemRepository;
import com.service123.tiketing.model.repository.UserRepository;
import com.service123.tiketing.model.service.ProblemService;
import com.service123.tiketing.model.service.UserService;

import java.time.LocalDate;

public class test {
    public static void main(String[] args) throws Exception {
        User user = User.builder()
                .userRoles(UserRoles.employee)
                .name("ava")
                .family("lo")
                .userName("ava345")
                .password("ava098")
                .build();

//        System.out.println(UserService.getService().save(user));
//        System.out.println(UserService.getService().remove(7));
//        System.out.println(UserService.getService().edit(user));
//        System.out.println(UserService.getService().findById(21));
        Problem problem = Problem
                .builder()
                .description("aya dorost save kard?")
                .dateTime(LocalDate.now())
                .sender(User.builder().userName("arman").build())
                .receiver(User.builder().userName("farhad").build())
                .status(ProblemStatus.UnSeen)
                .build();

        System.out.println(ProblemService.getProblemService().save(problem));
    }
}
