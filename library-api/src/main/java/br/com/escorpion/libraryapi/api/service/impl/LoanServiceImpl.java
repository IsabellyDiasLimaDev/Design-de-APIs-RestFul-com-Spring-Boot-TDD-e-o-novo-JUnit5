package br.com.escorpion.libraryapi.api.service.impl;

import br.com.escorpion.libraryapi.api.dto.LoanFilterDTO;
import br.com.escorpion.libraryapi.api.model.entity.Loan;
import br.com.escorpion.libraryapi.api.repository.LoanRepository;
import br.com.escorpion.libraryapi.api.service.LoanService;
import br.com.escorpion.libraryapi.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@AllArgsConstructor
@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository repository;

    @Override
    public Loan save(Loan loan) {
        if (repository.existsByBookAndNotReturned(loan.getBook())) {
            throw new BusinessException("Book already loaned");
        }
        return repository.save(loan);
    }

    @Override
    public Optional<Loan> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Loan update(Loan loan) {
        return repository.save(loan);
    }

    @Override
    public Page<Loan> find(LoanFilterDTO loan, Pageable page) {
        return null;
    }
}
