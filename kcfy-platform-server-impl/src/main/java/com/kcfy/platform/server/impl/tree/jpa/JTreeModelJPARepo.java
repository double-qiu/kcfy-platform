package com.kcfy.platform.server.impl.tree.jpa;

import com.kcfy.platform.server.impl.tree.repo.JTreeModelRepo;
import com.kcfy.platform.server.kernal.springjpa.JSpringJpaRepository;
import com.kcfy.platform.server.tree.model.JTreeModel;

public interface JTreeModelJPARepo extends JTreeModelRepo ,JSpringJpaRepository<JTreeModel, String> {

}
