package com.example.studentsmanagement.controllers;

import com.example.studentsmanagement.entity.*;
import com.example.studentsmanagement.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class InternshipController {
    @Autowired
    private InternshipRepository internshipRepository;
    @Autowired
    private MentorRepository mentorRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private UniversityRepository universityRepository;
    @Autowired
    private CompanyCardRepository companyCardRepository;

    @GetMapping("/internship/{page}")
    public ModelAndView getAllIntern(@PathVariable Integer page) {
        ModelAndView mv = new ModelAndView("internship");
        PageRequest pageRequest = PageRequest.of(page - 1, 3);
        Page<Internship> internPage = internshipRepository.findInterns(pageRequest);
        int totalPages = internPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            mv.addObject("pageNumbers", pageNumbers);
        }

        Map<Long, Position> positionMap = new HashMap<>();
        for (Position item : positionRepository.findAll()) {
            positionMap.put(item.getId(), item);
        }

        mv.addObject("infoIntern", internPage.getContent());
        mv.addObject("position", positionMap);
        return mv;
    }

    @GetMapping("/infoIntern")
    public ModelAndView infoIntern(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView("infoIntern");
        Internship intern = internshipRepository.findById(id).get();

        mv.addObject("intern", intern);
        mv.addObject("position", positionRepository.findById(intern.getPosition_id()).get());
        mv.addObject("university", universityRepository.findById(intern.getUniversity_id()).get());

        return mv;
    }

    @GetMapping("/addIntern")
    public ModelAndView addIntern() {
        ModelAndView mv = new ModelAndView("add-intern");

        Internship intern = new Internship();
        List<CompanyCard> card = new ArrayList<>();
        for (CompanyCard item : companyCardRepository.findAll()) {
            if (!item.isUsed()) {
                card.add(item);
            }
        }
        Map<Long, User> userMap = new HashMap<>();
        for (User item : userRepository.findAll()) {
            userMap.put(item.getId(), item);
        }

        mv.addObject("newIntern", intern);
        mv.addObject("card", card);
        mv.addObject("mentor", mentorRepository.findAll());
        mv.addObject("university", universityRepository.findAll());
        mv.addObject("position", positionRepository.findAll());
        mv.addObject("users", userMap);
        return mv;
    }

    @PostMapping(value = "/saveIntern")
    public String saveIntern(@ModelAttribute Internship intern) {
        internshipRepository.save(intern);
        return "redirect:/internship/1";
    }

    @GetMapping("/editIntern")
    public ModelAndView editIntern(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView("add-intern");
        Internship intern = internshipRepository.findById(id).get();
        Map<Long, User> userMap = new HashMap<>();
        for (User item : userRepository.findAll()) {
            userMap.put(item.getId(), item);
        }

        mv.addObject("newIntern", intern);
        mv.addObject("mentor", mentorRepository.findAll());
        mv.addObject("position", positionRepository.findAll());
        mv.addObject("university", universityRepository.findAll());
        mv.addObject("card", companyCardRepository.findAll());
        mv.addObject("users", userMap);
        return mv;
    }

    @GetMapping("/deleteIntern")
    public String deleteIntern(@RequestParam Long id) {
        Internship internship = internshipRepository.findById(id).get();
        internshipRepository.deleteById(id);
        CompanyCard card = companyCardRepository.findById(internship.getCompany_card_id()).get();
        card.setUsed(false);
        companyCardRepository.save(card);
        return "redirect:/internship/1";
    }
}
