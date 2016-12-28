package taskhelloback.Utils;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class CustomPageable implements Pageable {

    private int offset = 0;
    private int limit = 0;

    public CustomPageable(int offset, int limit) {
        if (limit < 0)
            throw new IllegalArgumentException("Skip must not be less than zero!");

        if (offset < 0)
            throw new IllegalArgumentException("Offset must not be less than zero!");

        this.offset = offset;
        this.limit = limit;
    }

    public int getPageNumber() {
        return 0;
    }

    public int getPageSize() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }

    public Sort getSort() {
        return null;
    }

    public Pageable next() {
        return null;
    }

    public Pageable previousOrFirst() {
        return this;
    }

    public Pageable first() {
        return this;
    }

    public boolean hasPrevious() {
        return false;
    }
}
