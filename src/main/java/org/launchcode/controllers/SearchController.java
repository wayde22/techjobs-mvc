package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

import static org.launchcode.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "results")
    public String results (Model model, @RequestParam String searchType, String searchTerm) { //search in ALL columns
        ArrayList<HashMap<String, String>> jobsToPrint = new ArrayList<HashMap<String, String>>();
        if (searchType.equals("all")) {
            jobsToPrint = JobData.findByValue(searchTerm);

            model.addAttribute("title", searchTerm + " from all columns");
            model.addAttribute("listItems", jobsToPrint);
            model.addAttribute("columns", columnChoices);
        }
        else {
            jobsToPrint = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("title", searchTerm + " from "+ searchType+" columns");
            model.addAttribute("listItems", jobsToPrint);
            model.addAttribute("columns", columnChoices);
        }
        return "search";
    }


}