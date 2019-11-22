package com.codegym.service.impl;

import com.codegym.model.Category;
import com.codegym.model.Phone;
import com.codegym.repository.PhoneRepository;
import com.codegym.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    @Override
    public Page<Phone> findAll(Pageable pageable) {
        return phoneRepository.findAll(pageable);
    }

    @Override
    public Phone findById(Long id) {
        return phoneRepository.findOne(id);
    }

    @Override
    public void save(Phone phone) {
        phoneRepository.save(phone);
    }

    @Override
    public void remove(Long id) {
        phoneRepository.delete(id);
    }

    @Override
    public Iterable<Phone> findAllByCategory(Category category) {
        return phoneRepository.findAllByCategory(category);
    }

    @Override
    public Page<Phone> findAllByPrice(Double price, Pageable pageable) {
        return phoneRepository.findAllByPrice(price, pageable);
    }

    @Override
    public Page<Phone> findAllByOrderByPriceDesc(Pageable pageable) {
        return phoneRepository.findAllByOrderByPriceDesc(pageable);
    }

    @Override
    public Page<Phone> findAllByOrderByPriceAsc(Pageable pageable) {
        return phoneRepository.findAllByOrderByPriceAsc(pageable);
    }

    @Override
    public Page<Phone> findAllByOrderByDateOfPurchaseAsc(Pageable pageable) {
        return phoneRepository.findAllByOrderByDateOfPurchaseAsc(pageable);
    }

    @Override
    public Page<Phone> findAllByOrderByDateOfPurchaseDesc(Pageable pageable) {
        return phoneRepository.findAllByOrderByDateOfPurchaseDesc(pageable);
    }
}
