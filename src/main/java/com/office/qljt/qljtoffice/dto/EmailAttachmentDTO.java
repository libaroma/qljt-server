package com.office.qljt.qljtoffice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 续加仪
 * @date 2022/10/9
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailAttachmentDTO {

    /**
     * 附件名称
     */
    private String id;

    /**
     * 附件地址
     */
    private String url;
}
