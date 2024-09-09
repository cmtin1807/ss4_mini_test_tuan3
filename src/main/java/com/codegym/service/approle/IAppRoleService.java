package com.codegym.service.approle;

import com.codegym.model.AppRole;
import com.codegym.service.IGenerateService;

public interface IAppRoleService extends IGenerateService<AppRole> {
    AppRole findByName(String name);
}
