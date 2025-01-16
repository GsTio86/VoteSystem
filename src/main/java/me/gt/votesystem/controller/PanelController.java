package me.gt.votesystem.controller;

import me.gt.votesystem.service.VoteItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@CrossOrigin
@RestController
@RequestMapping()
public class PanelController {

    @Autowired
    private VoteItemsService voteItemsService;

    @GetMapping("/panel")
    public ModelAndView getVotePanel() {
        ModelAndView model = new ModelAndView("vote-panel");
        model.addObject("voteItems", voteItemsService.getAllVoteItems());
        return model;
    }
}
