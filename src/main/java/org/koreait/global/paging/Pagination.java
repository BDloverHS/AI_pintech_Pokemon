package org.koreait.global.paging;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Setter는 따로 필요하지 않으므로 @Data 대신 아래 두 어노테이션을 씀
@Getter
@ToString
public class Pagination {
    private int page;
    private int total;
    private int ranges;
    private int limit;
    private int totalPages;
    private int firstRangePage;
    private int lastRangePage;
    private int prevLastLastPage;
    private int nextRangeFirstPage;

    /**
     * @param page : 현재 페이지 번호
     * @param total : 총 레코드 갯수
     * @param ranges : 페이지 구간 갯수
     * @param limit : 한 페이지 당 출력될 레코드 갯수
     */
    public Pagination(int page, int total, int ranges, int limit, HttpServletRequest request) {
        // 페이징 기본값 처리
        page = Math.max(page, 1);
        total = Math.max(total, 0);
        ranges = ranges < 1 ? 10 : ranges;
        limit = limit < 1 ? 20 : limit;

        if (total == 0) {
            return;
        }

        // 전체 페이지 갯수
        int totalPages = (int)Math.ceil(total / (double)limit);

        // 구간 번호 - (1~5 페이지 - 0번 구간, 6~10 페이지 - 1번 구간, ...)
        int rangeCnt = (page - 1) / ranges;
        int firstRangePage = rangeCnt * ranges + 1; // 현재 구간의 시작 페이지 번호
        int lastRanges = firstRangePage + ranges - 1; // 현재 구간의 마지막 페이지 번호
        lastRanges = Math.min(lastRanges, totalPages);

        // 이전 구간 시작 페이지 번호, 다음 구간 시작 페이지 번호
        int prevRageFirstPage = 0, nextRangeFirstPage = 0;

        if (rangeCnt > 0) {
            prevRageFirstPage = (rangeCnt - 1) * ranges + ranges;
        }

        // 마지막 페이지 구간
        int lastRangeCnt = (totalPages - 1) / ranges;
        if (rangeCnt < lastRangeCnt) {
            nextRangeFirstPage = (rangeCnt + 1) * ranges + 1;
        }

        this.page = page;
        this.total = total;
        this.ranges = ranges;
        this.limit = limit;
        this.totalPages = totalPages;
        this.firstRangePage = firstRangePage;
        this.lastRangePage = lastRangeCnt;
        this.prevLastLastPage = prevRageFirstPage;
        this.nextRangeFirstPage = nextRangeFirstPage;
    }

    /**
     * String 배열의 0번째 - 페이지 번호 숫자, 1번째 - 페이지 URL
     * @return
     */
    public List<String[]> getPages() {
        if (total == 0) {
            return Collections.EMPTY_LIST;
        }

        List<String[]> pages = new ArrayList<>();
        for (int i = firstRangePage; i < lastRangePage; i++) {
            String url = "?pages=" + i;
            pages.add(new String[] {"" + i, url});
        }

        return pages;
    }
}
