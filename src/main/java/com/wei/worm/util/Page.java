package com.wei.worm.util;

import java.util.List;

public class Page<T> {

        private int pageNumber;//当前页数
        private int pageSize;//每页记录数
        private int total;//总记录数
        private int totalPage;//总页数(总记录数/每页记录数)
        private int from;
        private int limit;

        private List<T> rows;//包含商品的集合

        public Page( int pageSize,int pageNumber) {
                if (pageNumber < 1) pageNumber = 1;
                if (pageSize < 0) pageSize=0;
                this.pageNumber = pageNumber;
                this.pageSize = pageSize;

                from = (pageNumber - 1) * pageSize;
                limit = pageNumber * pageSize;
        }

        public int getPageNumber() {
                return pageNumber;
        }

        public void setPageNumber(int pageNumber) {
                this.pageNumber = pageNumber;
        }

        public int getPageSize() {
                return pageSize;
        }

        public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
        }

        public int getTotal() {
                return total;
        }

        public void setTotal(int total) {
                this.total = total;
        }

        public int getTotalPage() {
                return totalPage;
        }

        public void setTotalPage(int totalPage) {
                this.totalPage = totalPage;
        }

        public List<T> getRows() {
                return rows;
        }

        public void setRows(List<T> rows) {
                this.rows = rows;
        }

        public int getFrom() {
                return from;
        }

        public void setFrom(int from) {
                this.from = from;
        }

        public int getLimit() {
                return limit;
        }

        public void setLimit(int limit) {
                this.limit = limit;
        }
}
