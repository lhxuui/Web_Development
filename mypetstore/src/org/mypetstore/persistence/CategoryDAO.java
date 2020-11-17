package org.mypetstore.persistence;

import org.mypetstore.domain.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> getCategoryList();

    Category getCategory(String categoryId);
}
