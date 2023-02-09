package org.zerock.api01.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

    @Builder.Default
    private int page = 1;
    @Builder.Default
    private int size = 10;
    private String type; // 검색 종류 ex) t: title, c: content, cw: content + writer
    private String keyword;

    private LocalDate from;
    private LocalDate to;

    private Boolean completed;

    public String[] getTypes() {
        if(this.type == null || type.isEmpty()) return null;

        return type.split("");
    }

    public Pageable getPageable(String...props) {
        return PageRequest.of(this.page -1, this.size, Sort.by(props).descending());
    }

    private String link;

    public String getLink() {
        if(link == null) {
            StringBuilder builder = new StringBuilder();

            builder.append("page=" + this.page);
            builder.append("&size=" + this.size);

            if(this.type != null && this.type.length() > 0) {
                builder.append("&type=" + this.type);
            }

            if(this.keyword != null) {
                try {
                    builder.append("&keyword=" + URLEncoder.encode(this.keyword, "UTF-8"));
                }catch (UnsupportedEncodingException e) {
                }
            }

            this.link = builder.toString();
        }

        return this.link;
    }
}
