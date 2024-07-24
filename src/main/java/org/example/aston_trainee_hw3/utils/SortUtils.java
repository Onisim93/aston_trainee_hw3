package org.example.aston_trainee_hw3.utils;

import org.springframework.data.domain.Sort;

public class SortUtils {

    private SortUtils() {}

    public static Sort getSort(String sortBy) {
        if (sortBy == null || sortBy.isEmpty()) {
            return Sort.unsorted();
        }

        String[] sortParams = sortBy.split("::");
        boolean isDesc = sortParams.length > 1 && sortParams[1].equalsIgnoreCase("desc");
        Sort sort = Sort.by(sortParams[0]);

        return isDesc ? sort.descending() : sort.ascending();
    }
}
