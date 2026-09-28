package io.github.theusdev.imageliteapi.application.images;

import io.github.theusdev.imageliteapi.domain.entity.Image;
import io.github.theusdev.imageliteapi.domain.enums.ImageExtension;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
public class ImageMapper {

    public Image mapImageToImage(MultipartFile file, String name, List<String> tags) throws IOException {
        return Image.builder()
                .name(name)
                .size(file.getSize())
                .tags(String.join(",", tags))
                .extension(ImageExtension.valueOf(MediaType.valueOf(file.getContentType())))
                .file(file.getBytes())
                .build();
    }

    public ImageDTO imageToImageDTO(Image image, String url) {
        return ImageDTO.builder()
                .url(url)
                .extension(image.getExtension().name())
                .size(image.getSize())
                .name(image.getName())
                .uploadDate(image.getUploadDate().toLocalDate())
                .build();
    }
}
