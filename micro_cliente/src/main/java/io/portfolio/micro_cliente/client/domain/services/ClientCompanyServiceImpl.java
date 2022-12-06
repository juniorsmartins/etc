package io.portfolio.micro_cliente.client.domain.services;

import io.portfolio.micro_cliente.client.domain.client.ClientCompanyEntityImpl;
import io.portfolio.micro_cliente.client.domain.dtos.ClientCompanyDTORequestImpl;
import io.portfolio.micro_cliente.client.domain.dtos.ClientCompanyDTOResponseImpl;
import io.portfolio.micro_cliente.client.domain.filter.ClientCompanyFilterImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public non-sealed class ClientCompanyServiceImpl implements PolicyService<ClientCompanyDTORequestImpl,
        ClientCompanyFilterImpl, ClientCompanyDTOResponseImpl, ClientCompanyEntityImpl, Long> {

    @Override
    public ResponseEntity<ClientCompanyDTOResponseImpl> create(ClientCompanyDTORequestImpl dto) {
        return null;
    }

    @Override
    public ResponseEntity<ClientCompanyDTOResponseImpl> update(ClientCompanyDTORequestImpl dto) {
        return null;
    }

    @Override
    public ResponseEntity<ClientCompanyDTOResponseImpl> searchById(Long aLong) {
        return null;
    }

    @Override
    public ResponseEntity<Page<ClientCompanyDTOResponseImpl>> searchAll(ClientCompanyFilterImpl filter, Pageable pagination) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteById(Long aLong) {
        return null;
    }
}
