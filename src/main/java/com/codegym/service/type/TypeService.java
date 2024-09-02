package com.codegym.service.type;

import com.codegym.model.Type;
import com.codegym.repository.ITypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TypeService implements ITypeService {
    @Autowired
    private ITypeRepository typeRepository;

    @Override
    public Iterable<Type> findAll() {
        return typeRepository.findAll();
    }

    @Override
    public Optional<Type> findById(Long id) {
        return typeRepository.findById(id);
    }

    @Override
    public void save(Type type) {
        typeRepository.save(type);
    }

    @Override
    public void delete(Long id) {
        typeRepository.deleteById(id);
    }

    @Override
    public void deleteTypeById(Long id) {
        typeRepository.deleteType(id);
    }
}
