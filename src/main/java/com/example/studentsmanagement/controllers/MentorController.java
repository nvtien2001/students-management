package com.example.studentsmanagement.controllers;

import com.example.studentsmanagement.entity.Internship;
import com.example.studentsmanagement.entity.Mentor;
import com.example.studentsmanagement.entity.User;
import com.example.studentsmanagement.repositories.InternshipRepository;
import com.example.studentsmanagement.repositories.MentorRepository;
import com.example.studentsmanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MentorController {
    @Autowired
    private MentorRepository mentorRepository;
    @Autowired
    private InternshipRepository internRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/mentor")
    public ModelAndView getAllMentor() {
        Map<Long, Integer> map = new HashMap<>();
        for (Mentor mentor : mentorRepository.findAll()) {
            int count = 0;
            for (Internship item : internRepository.findAll()) {
                if (item.getMentor_id().equals(mentor.getId())) {
                    count++;
                }
            }
            map.put(mentor.getId(), count);
        }
        Map<Long, User> userMap = new HashMap<>();
        for (User item : userRepository.findAll()) {
            userMap.put(item.getId(), item);
        }
        ModelAndView mv = new ModelAndView("mentor");
        mv.addObject("infoMentor", mentorRepository.findAll());
        mv.addObject("map", map);
        mv.addObject("user", userMap);

        return mv;
    }

    @GetMapping("/infoMentor")
    public ModelAndView infoMentor(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView("mentor-internship");
        Mentor mentor = mentorRepository.findById(id).get();
        List<Internship> list = new ArrayList<>();
        for (Internship item : internRepository.findAll()) {
            if (item.getMentor_id().equals(id)){
                list.add(item);
            }
        }

        mv.addObject("mentor", mentor);
        mv.addObject("user", userRepository.findById(mentor.getUser_id()).get());
        mv.addObject("internship", list);
        return mv;
    }
}
