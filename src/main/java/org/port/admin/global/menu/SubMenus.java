package org.port.admin.global.menu;


import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

public interface SubMenus {
    String menuCode();

    @ModelAttribute("submenus")
    default List<MenuDetail> subMenus() {
            return Menus.getMenus(menuCode());
    }
}
