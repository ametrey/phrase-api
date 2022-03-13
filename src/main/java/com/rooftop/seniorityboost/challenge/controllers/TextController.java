package com.rooftop.seniorityboost.challenge.controllers;

import com.rooftop.seniorityboost.challenge.entities.Text;
import com.rooftop.seniorityboost.challenge.models.exceptions.BadRequestException;
import com.rooftop.seniorityboost.challenge.models.exceptions.NoTextFoundException;
import com.rooftop.seniorityboost.challenge.models.pagefilter.TextPage;
import com.rooftop.seniorityboost.challenge.models.request.TextRequest;
import com.rooftop.seniorityboost.challenge.models.response.TextResponse;
import com.rooftop.seniorityboost.challenge.services.TextService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class TextController {

    @Autowired
    private TextService textService;

    @GetMapping("/text")
    public ResponseEntity<?> findTexts(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer rpp,
            @RequestParam(required = false) Integer chars) {

        TextPage textPage = new TextPage();
        textPage.setPage(textPage.validatePageNumber(page));
        textPage.setRpp(textPage.validateRppNumber(rpp));

        if (chars == null) {
            return new ResponseEntity<>(textService.getAllTextsPaged(textPage), HttpStatus.OK);
        }

        textPage.setChars(textPage.validateChars(chars));
        return new ResponseEntity<>(textService.getTextsByChars(textPage), HttpStatus.OK);

    }

    @GetMapping("/text/{id}")
    public ResponseEntity<Text> getTextById(@PathVariable Integer id) {

        if (textService.exists(id)) {
            return new ResponseEntity<>(textService.getTextById(id), HttpStatus.CREATED);
        }
        
        throw new NoTextFoundException();

    }

    @PostMapping("/text")
    public ResponseEntity<TextResponse> createText(@RequestBody TextRequest textReq) {

        TextResponse response = new TextResponse();

        if (textReq.text != null && !textReq.text.trim().isEmpty()) {
            Text text = textService.createText(textReq.text, textReq.validateCharsNumber(textReq.text, textReq.chars));
            response.id = text.getId();
            response.url = text.getUrl();
            return ResponseEntity.ok(response);
        }

        throw new BadRequestException();
    }

    @DeleteMapping("/text/{id}")
    public ResponseEntity<?> deleteText(@PathVariable Integer id) {

        if (textService.exists(id)) {
            textService.remove(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        throw new NoTextFoundException();
    }
}
