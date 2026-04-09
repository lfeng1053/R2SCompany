package services;

import dao.BrandDAO;
import dto.BrandDTO;
import entity.Brand;
import exception.DAOException;
import mapper.BrandMapper;

import java.util.ArrayList;
import java.util.List;

public class BrandService {
    private final BrandDAO brandDAO;

    public BrandService(BrandDAO brandDAO) {
        this.brandDAO = brandDAO;
    }

    public List<BrandDTO> findAll() throws DAOException {
        List<BrandDTO> out = new ArrayList<>();
        for (Brand b : brandDAO.findAll()) {
            out.add(BrandMapper.toDto(b));
        }
        return out;
    }

    public BrandDTO findById(int brandId) throws DAOException {
        return BrandMapper.toDto(brandDAO.findById(brandId));
    }

    public void insert(BrandDTO dto) throws DAOException {
        brandDAO.insert(BrandMapper.toEntity(dto));
    }

    public void update(BrandDTO dto) throws DAOException {
        brandDAO.update(BrandMapper.toEntity(dto));
    }

    public void delete(int brandId) throws DAOException {
        brandDAO.delete(brandId);
    }
}