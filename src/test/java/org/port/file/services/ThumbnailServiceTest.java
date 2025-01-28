package org.port.file.services;

import org.junit.jupiter.api.Test;
import org.port.file.controllers.RequestThumb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ThumbnailServiceTest {
    @Autowired
    private ThumbnailService service;

    @Test
    void thumbPathTest() {
        RequestThumb form = new RequestThumb();
        /*
        form.setSeq(1055L);
        form.setWidth(100);
        form.setHeight(100);
        String path = service.getThumbPath(1155L, null, 100,100);
        path = service.create(form);
        System.out.println(path);
        */

        form.setSeq(null);
        form.setUrl("https://s.pstatic.net/static/www/mobile/edit/20241206_1095/upload_1733469926768BwCz2.jpg");
        form.setWidth(100);
        form.setHeight(100);
        String path2 = service.getThumbPath(0L, "https://s.pstatic.net/static/www/mobile/edit/20241206_1095/upload_1733469926768BwCz2.jpg", 100, 100);
        path2 = service.create(form);
        System.out.println(path2);
    }
}
