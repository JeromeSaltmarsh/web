package com.market.web;

import com.market.domain.Image;
import com.market.service.image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/images")
public class ImageController {

    @Autowired
    private ImageService service;

    @RequestMapping(method = RequestMethod.POST)
    public Resource<Image> upload(@RequestBody String base61Encoded) throws IOException {
        Image image = new Image(base61Encoded);
        return service.create(image);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Image select(@PathVariable Long id) throws IOException {
        return service.get(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/src/{id}")
    public String selectSrc(@PathVariable Long id) throws IOException {
        return service.get(id).getBase64();
    }
}
