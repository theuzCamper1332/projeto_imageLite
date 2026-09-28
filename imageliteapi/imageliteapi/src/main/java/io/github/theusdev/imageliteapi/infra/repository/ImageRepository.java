package io.github.theusdev.imageliteapi.infra.repository;

import io.github.theusdev.imageliteapi.domain.entity.Image;
import io.github.theusdev.imageliteapi.domain.enums.ImageExtension;
import io.github.theusdev.imageliteapi.infra.repository.specs.GenericSpecs;
import io.github.theusdev.imageliteapi.infra.repository.specs.ImageSpecs;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.util.StringUtils;

import java.util.List;

import static io.github.theusdev.imageliteapi.infra.repository.specs.GenericSpecs.conjunction;
import static io.github.theusdev.imageliteapi.infra.repository.specs.ImageSpecs.*;
import static org.springframework.data.jpa.domain.Specification.anyOf;

public interface ImageRepository extends JpaRepository<Image,String>, JpaSpecificationExecutor<Image> {

    default List<Image> findByExtensionAndNameOrTagsLike(ImageExtension imageExtension, String query) {
        Specification<Image> spec = Specification.where(conjunction());

        if(imageExtension != null){
            spec = spec.and(extensionEqual(imageExtension));
        }

        if(StringUtils.hasText(query)){
            spec = spec.and(anyOf(nameLike(query), tagsLike(query)));
        }

        return findAll(spec);
    }
}
