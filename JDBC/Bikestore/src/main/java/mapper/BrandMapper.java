package mapper;

import dto.BrandDTO;
import entity.Brand;

public final class BrandMapper {
    private BrandMapper() {}

    public static BrandDTO toDto(Brand brand) {
        if (brand == null) return null;
        return new BrandDTO(brand.getBrandId(), brand.getBrandName());
    }

    public static Brand toEntity(BrandDTO dto) {
        if (dto == null) return null;
        return new Brand(dto.getBrandId(), dto.getBrandName());
    }
}