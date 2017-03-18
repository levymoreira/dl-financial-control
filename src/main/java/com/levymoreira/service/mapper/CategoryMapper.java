package com.levymoreira.service.mapper;

import com.levymoreira.domain.*;
import com.levymoreira.service.dto.CategoryDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Category and its DTO CategoryDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, })
public interface CategoryMapper {

    @Mapping(source = "owner.id", target = "ownerId")
    @Mapping(source = "user.id", target = "userId")
    CategoryDTO categoryToCategoryDTO(Category category);

    List<CategoryDTO> categoriesToCategoryDTOs(List<Category> categories);

    @Mapping(source = "ownerId", target = "owner")
    @Mapping(source = "userId", target = "user")
    Category categoryDTOToCategory(CategoryDTO categoryDTO);

    List<Category> categoryDTOsToCategories(List<CategoryDTO> categoryDTOs);

    default Category categoryFromId(Long id) {
        if (id == null) {
            return null;
        }
        Category category = new Category();
        category.setId(id);
        return category;
    }
}
