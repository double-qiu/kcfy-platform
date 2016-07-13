package com.kcfy.platform.server.impl.htmlMenu.jpa;

import com.kcfy.platform.server.htmlMenu.model.MenuModel;
import com.kcfy.platform.server.impl.htmlMenu.repo.MenuModelRepo;
import com.kcfy.platform.server.kernal.springjpa.JSpringJpaRepository;

public interface MenuModelJPASRepo  extends MenuModelRepo ,JSpringJpaRepository<MenuModel, String>{

}
