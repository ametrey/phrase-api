package com.rooftop.seniorityboost.challenge.services;

import java.util.*;

import com.rooftop.seniorityboost.challenge.entities.Text;
import com.rooftop.seniorityboost.challenge.models.pagefilter.TextPage;
import com.rooftop.seniorityboost.challenge.repositories.TextRepo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.*;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class TextService {

    @Autowired
    private TextRepo textRepo;

    public List<Text> getAllTextsPaged(TextPage textPage) {

        Pageable pageable = PageRequest.of(textPage.getPage(), textPage.getRpp());

        Page<Text> pagedResult = textRepo.findAll(pageable);

        return pagedResult.getContent();

    }

    public Page<Text> getTextsByChars(TextPage textPage) {

        Pageable pageable = PageRequest.of(textPage.getPage(), textPage.getRpp());

        return textRepo.findByChars(textPage.getChars(), pageable);
    }

    public Text getTextById(Integer id) {
        return textRepo.getById(id);
    }

    public Text createText(String str, Integer chars) {

        Text text = new Text();
        text.setHash(toHash(str, chars));

        if (notExist(text)) {

            text.setText(str);
            text.setChars(chars);
            text.setResults(analyze(str, chars));
            textRepo.save(text);
            generateUrl(text);

            return text;
        }

        return textRepo.findByHash(text.getHash());
    }

   

    public void generateUrl(Text text) {

        String url = "text/{}";

        text.setUrl(url.replaceAll("\\{.*?\\}", text.getId().toString()));

        textRepo.save(text);
    }

    public Map<String, Integer> analyze(String str, Integer chars) {

        String subStr = new String();
        Map<String, Integer> map = new LinkedHashMap<>();

        for (int i = 0; i < str.length() - chars + 1; i++) {

            Integer counter = 0;
            subStr = str.substring(i, i + chars);

            for (int j = 0; j < str.length() - chars + 1; j++) {
                if (subStr.equals(str.substring(j, j + chars))) {
                    counter++;
                }
            }

            map.put(subStr, counter);
        }

        return map;
    }

    private String toHash(String str, Integer chars) {
        String concatenated = str + chars;

        return DigestUtils.md5DigestAsHex(concatenated.getBytes());
    }

    public boolean notExist(Text text) {
        if (!textRepo.existsTextByHash(text.getHash())) {
            return true;
        }
        return false;
    }

    public void remove(Integer id) {
        textRepo.deleteById(id);
    }

    public boolean exists(Integer id) {
        if (textRepo.existsById(id)) {
            return true;
        }
        return false;
    }
}
