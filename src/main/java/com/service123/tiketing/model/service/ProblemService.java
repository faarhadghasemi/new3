package com.service123.tiketing.model.service;

import com.service123.tiketing.model.entity.Problem;
import com.service123.tiketing.model.repository.ProblemRepository;
import com.service123.tiketing.model.repository.UserRepository;
import com.service123.tiketing.model.service.impl.ServiceImpl;

import java.util.List;

public class ProblemService implements ServiceImpl<Problem> {
    private static ProblemService problemService = new ProblemService();

    public ProblemService() {
    }

    public static ProblemService getProblemService() {
        return problemService;
    }


    @Override
    public Problem save(Problem problem) throws Exception {
        try (ProblemRepository problemRepository = new ProblemRepository()) {
            return problemRepository.save(problem);
        }
    }


        @Override
        public Problem edit (Problem problem) throws Exception {
            try (ProblemRepository problemRepository = new ProblemRepository()) {
                return problemRepository.edit(problem);
            }
        }

        @Override
        public Problem remove ( long id) throws Exception {
            try (ProblemRepository problemRepository = new ProblemRepository()) {
                  return problemRepository.remove(id);
            }
        }

        @Override
        public List<Problem> findAll () throws Exception {
            try (ProblemRepository problemRepository = new ProblemRepository()) {
                return problemRepository.findAll();
            }
        }

        @Override
        public Problem findById ( long id) throws Exception {
            try (ProblemRepository problemRepository = new ProblemRepository()) {
                return problemRepository.findById(id);
        }
    }
}
