package io.github.theusdev.imageliteapi.application.images;

import io.github.theusdev.imageliteapi.domain.entity.Image;
import io.github.theusdev.imageliteapi.domain.enums.ImageExtension;
import io.github.theusdev.imageliteapi.domain.service.ImageService;
import io.github.theusdev.imageliteapi.infra.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ImageServiceEmpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    @Transactional
    public Image save(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public Optional<Image> findById(String id) {
        return imageRepository.findById(id);
    }

    @Override
    public List<Image> search(ImageExtension extension, String query) {
        return imageRepository.findByExtensionAndNameOrTagsLike(extension, query);
    }
}
