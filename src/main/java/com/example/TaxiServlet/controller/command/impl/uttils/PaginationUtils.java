package com.example.TaxiServlet.controller.command.impl.uttils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static java.util.Objects.nonNull;

public class PaginationUtils {
    public static final int DEFAULT_PAGE = 1;
    public static final int DEFAULT_RECORDS_PER_PAGE = 5;

    private int page = DEFAULT_PAGE;
    private int recordsPerPage = DEFAULT_RECORDS_PER_PAGE;
    private long rows;

    public void setAttributes(HttpServletRequest request, long rows) {
        init(request,rows);

        request.setAttribute("numberOfPages", getNumberOfPages());
        request.setAttribute("page", page);
        request.setAttribute("recordsPerPage", recordsPerPage);
        request.setAttribute("records", rows);
    }

    public void init(HttpServletRequest request, long rows){
        this.rows = rows;

        String pageParam;
        String recordsPerPageParam;

        if (nonNull(pageParam = request.getParameter("page"))) {
            page = Integer.parseInt(pageParam);
        }else{
            page = DEFAULT_PAGE;
        }

        if (nonNull(recordsPerPageParam = request.getParameter("recordsPerPage"))) {
            recordsPerPage = Integer.parseInt(recordsPerPageParam);
        }else{
            recordsPerPage = DEFAULT_RECORDS_PER_PAGE;
        }
    }

    public int getPage() {
        return page - 1;
    }

    public int getOffset() {
        return (page - 1) * recordsPerPage;
    }

    public int getRecordsPerPage() {
        return recordsPerPage;
    }

    public <E> Page<E> createPage(List<E> data){
        Page<E> page = new Page<>();
        page.setContent(data);
        page.setPageNumber(getPage());
        page.setPageSize(recordsPerPage);
        page.setNumberOfElements(data.size());
        page.setTotalElements(rows);
        page.setFirst(page.getPageNumber() <= 0);
        page.setTotalPages(getNumberOfPages());
        page.setLast(page.getPageNumber() >= page.getTotalPages() - 1);
        return page;
    };

    public long getNumberOfPages(){
        return (long)Math.ceil(rows * 1.0 / recordsPerPage);
    }
}
