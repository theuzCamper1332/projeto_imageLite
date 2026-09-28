package io.github.theusdev.imageliteapi.domain.service;

import io.github.theusdev.imageliteapi.domain.entity.Image;
import io.github.theusdev.imageliteapi.domain.enums.ImageExtension;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ImageService {
    Image save(Image image);

    Optional<Image> findById(String id);

    List<Image> search(ImageExtension extension, String query);
}
