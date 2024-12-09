package com.example.CatalogManagementSystem.dto.request;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FilterRequest {

    List<String> brandList;

    List<String> categoryList;

    List<Integer> idList;
}
