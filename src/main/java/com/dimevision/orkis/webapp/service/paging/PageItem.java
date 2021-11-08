package com.dimevision.orkis.webapp.service.paging;

import lombok.*;

/**
 * @author Dimevision
 * @version 0.1
 */

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageItem {

    private PageItemType pageItemType;

    private int index;

    private boolean active;
}
