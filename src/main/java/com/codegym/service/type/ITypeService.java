package com.codegym.service.type;

import com.codegym.model.Type;
import com.codegym.service.IGenerateService;

public interface ITypeService extends IGenerateService<Type> {
    void deleteTypeById(Long id);

}
