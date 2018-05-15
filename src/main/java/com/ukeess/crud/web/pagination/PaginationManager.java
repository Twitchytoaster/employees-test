package com.ukeess.crud.web.pagination;

import com.ukeess.crud.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PaginationManager {

    private static final int PAGE_DIFFERENCE = 3;

    public boolean hasLeftDots(Page page) {
        return page.getNumber() - PAGE_DIFFERENCE > 0;
    }

    public boolean hasRightDots(Page page) {
        return page.getTotalPages() - page.getNumber() > PAGE_DIFFERENCE;
    }

    public List<Integer> createPages(Page<Employee> page) {
        int current = page.getNumber();
        int totalPages = page.getTotalPages();
        List<Integer> pages = new ArrayList<>();

        int leftPages = current - PAGE_DIFFERENCE;
        int rightPages = current + PAGE_DIFFERENCE;

        if (leftPages > 0) {
            for (int i = leftPages; i < current; i++) {
                pages.add(i);
            }
        } else if(Math.abs(leftPages) < PAGE_DIFFERENCE) {
            for (int i = leftPages; i < current; i++) {
                if(i >= 0) {
                    pages.add(i);
                }
            }
        }

        if (rightPages < totalPages) {
            for (int i = current; i <= rightPages; i++) {
                pages.add(i);
            }
        } else if(totalPages - rightPages < PAGE_DIFFERENCE) {
            for(int i = current; i <= rightPages; i++) {
                if(i < totalPages) {
                    pages.add(i);
                }
            }
        }

        return pages;
    }
}